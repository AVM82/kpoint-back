package ua.in.kp.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.user.UserLoginRequestDto;
import ua.in.kp.dto.user.UserLoginResponseDto;
import ua.in.kp.dto.user.UserRegisterRequestDto;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserResponseDto register(UserRegisterRequestDto requestDto) {
        if (userService.existsByEmail(requestDto.email())) {
            throw new EntityExistsException(
                    "User with email " + requestDto.email() + " already exist");
        }
        return userService.create(requestDto);
    }

    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
        return new UserLoginResponseDto(
                jwtUtil.generateToken(authentication.getName()),
                userService.getByEmailFetchTagsSocialsRoles(authentication.getName())
        );
    }
}
