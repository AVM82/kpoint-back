package ua.in.kp.exception;

import jakarta.persistence.EntityNotFoundException;
import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.in.kp.locale.Translator;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String TIMESTAMP_PROPERTY_NAME = "timestamp";
    private static final String REJECTED_VALUE_MSG_CODE =
            "validation.constraint.rejected-value.message";
    private final Translator translator;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        ex.getBindingResult().getAllErrors().forEach(error -> setError(problemDetail, error));
        return ResponseEntity.of(problemDetail).build();
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {
        return createResponseEntity(statusCode, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = {
            EntityNotFoundException.class,
            UsernameNotFoundException.class,
    })
    protected ResponseEntity<Object> handleDataProcessingException(RuntimeException ex) {
        return createResponseEntity(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSqlException(SQLException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setType(
                URI.create(
                        "https://docs.intersystems.com/latest/csp/docbook/DocBook.UI.Page.cls?KEY=RERR_sql"));
        problemDetail.setProperty("SQL error code", ex.getErrorCode());
        return ResponseEntity.of(problemDetail).build();
    }

    private ResponseEntity<Object> createResponseEntity(HttpStatusCode statusCode) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(statusCode);
        problemDetail.setProperty(TIMESTAMP_PROPERTY_NAME,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.of(problemDetail).build();
    }

    private ResponseEntity<Object> createResponseEntity(HttpStatusCode statusCode, String details) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusCode, details);
        problemDetail.setProperty(TIMESTAMP_PROPERTY_NAME,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return ResponseEntity.of(problemDetail).build();
    }

    private void setError(ProblemDetail problemDetail, ObjectError error) {
        if (error instanceof FieldError fieldError) {
            String fieldName = fieldError.getField();
            List<String> errorMessages = addMessageToErrorList(problemDetail, fieldError);
            problemDetail.setProperty(fieldName, errorMessages);
        } else {
            problemDetail.setProperty(error.getObjectName(), error.getDefaultMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<String> addMessageToErrorList(ProblemDetail problemDetail, FieldError error) {
        List<String> errorMessages;
        Map<String, Object> detailProperties = problemDetail.getProperties();
        if (detailProperties != null && detailProperties.containsKey(error.getField())) {
            errorMessages = (List<String>) detailProperties.get(error.getField());
            errorMessages.add(errorMessages.size() - 2, error.getDefaultMessage());
        } else {
            errorMessages = new ArrayList<>();
            errorMessages.add(error.getDefaultMessage());
            errorMessages.add(translator.getLocaleMessage(REJECTED_VALUE_MSG_CODE)
                    + " = " + error.getRejectedValue());
        }
        return errorMessages;
    }
}