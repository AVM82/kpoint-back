package ua.in.kp.dto.user;

import java.util.Map;
import java.util.Set;
import ua.in.kp.enumeration.SocialNetworkName;
import ua.in.kp.enumeration.UserRole;

public record UserResponseDto(
        String id,
        String username,
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
