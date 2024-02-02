package ua.in.kp.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.user.UserLoginResponseDto;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.entity.ApplicantEntity;
import ua.in.kp.mapper.ApplicantMapper;
import ua.in.kp.repository.ApplicantRepository;
import ua.in.kp.service.UserService;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OAuth2GoogleServiceImpl implements OAuth2Service {

    private final UserService userService;
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final JwtUtil jwtUtils;

    @Value("${oauth2.google.client-id}")
    private String clientId;
    @Value("${oauth2.google.client-secret}")
    private String clientSecret;
    @Value("${oauth2.redirect-uri}")
    private String redirectUri;

    @Override
    public UserLoginResponseDto handleCode(String code) throws AuthenticationException {
        GoogleIdToken idToken = getGoogleIdToken(code);
        String userEmail = idToken.getPayload().getEmail();
        if (!idToken.getPayload().getEmailVerified()) {
            throw new AuthenticationException("${exception.email-not-verified}");
        }
        UserLoginResponseDto responseDto;
        if (userService.existsByEmail(userEmail)) {
            UserResponseDto userFromDb = userService.getByEmailFetchTagsSocialsRoles(userEmail);
            responseDto = new UserLoginResponseDto(
                    jwtUtils.generateToken(userFromDb.email()), userFromDb);
        } else {
            ApplicantEntity applicant = applicantRepository.findByEmail(userEmail)
                    .orElseGet(() -> applicantRepository.save(buildApplicant(idToken)));
            responseDto = new UserLoginResponseDto(
                    null, applicantMapper.toUserResponseDto(applicant));
        }
        return responseDto;
    }

    private GoogleIdToken getGoogleIdToken(String code) {
        try {
            return new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    redirectUri
            ).execute().parseIdToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ApplicantEntity buildApplicant(GoogleIdToken idToken) {
        return ApplicantEntity.builder()
                .email(idToken.getPayload().getEmail())
                .avatarImgUrl((String) idToken.getPayload().get("picture"))
                .username((String) idToken.getPayload().get("given_name"))
                .build();
    }
}
