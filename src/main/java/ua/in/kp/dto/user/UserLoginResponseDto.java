package ua.in.kp.dto.user;

public record UserLoginResponseDto(
        String token,

        UserResponseDto user
) {
}
