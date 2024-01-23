package ua.in.kp.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import ua.in.kp.enumeration.SocialNetworkName;
import ua.in.kp.enumeration.UserRole;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String password;

    @Column(name = "email", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String lastName;

    @Column(name = "avatar_img_url",
            columnDefinition = "VARCHAR(100)")
    private String avatarImgUrl;

    @Column(name = "description", columnDefinition = "VARCHAR(512)")
    private String description;

    @Column(name = "tags", columnDefinition = "VARCHAR(10)[]", nullable = false)
    private Set<String> tags;

    @ElementCollection
    @CollectionTable(name = "user_socials", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyEnumerated
    @Column(name = "url")
    private Map<SocialNetworkName, String> socialNetworks = new EnumMap<>(SocialNetworkName.class);

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;
}
