package ua.in.kp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Map;
import java.util.Set;
import ua.in.kp.enumeration.SocialNetworkName;
import ua.in.kp.validator.CollectionLength;

public record UserCreateRequestDto(
        @Size(min = 2, message = "username should have at least 2 characters")
        @NotEmpty
        String username,
        @NotEmpty
        String password,
        @Email(message = "email should be a valid email format")
        @NotEmpty(message = "email should not be null or empty")
        String email,
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        String avatarImgUrl,
        @Size(min = 10, max = 60)
        String description,
        @CollectionLength(min = 1, max = 10, message = "tags should be from {min} to {max}")
        Set<String> tags,
        Map<SocialNetworkName, String> socialNetworks
) {
}
