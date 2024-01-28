package ua.in.kp.dto.user;

import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @Size(min = 6, max = 100)
        String username,
        @Size(min = 6, max = 100)
        String password
) {
}
