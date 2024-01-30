package ua.in.kp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.kp.dto.user.UserLoginRequestDto;
import ua.in.kp.dto.user.UserLoginResponseDto;
import ua.in.kp.dto.user.UserRegisterRequestDto;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
        return new ResponseEntity<>(authService.register(userRegisterRequestDto),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
