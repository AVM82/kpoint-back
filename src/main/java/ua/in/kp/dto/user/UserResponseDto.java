package ua.in.kp.dto.user;

import java.util.Map;
import java.util.Set;
import ua.in.kp.enumeration.SocialNetworkName;
import ua.in.kp.enumeration.UserRole;

public record UserResponseDto(
        Long id,
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String avatarImgUrl,
        String description,
        Set<String> tags,
        Map<SocialNetworkName, String> socialNetworks,
        Set<UserRole> roles
) {
}
