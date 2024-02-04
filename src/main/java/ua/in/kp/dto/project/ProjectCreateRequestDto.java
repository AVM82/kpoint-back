package ua.in.kp.dto.project;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.kp.enumeration.SocialNetworkName;
import ua.in.kp.validator.CollectionLength;
import ua.in.kp.validator.FutureDate;

import java.util.Map;
import java.util.Set;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateRequestDto {

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(max = 150)
    private String summary;

    @NotBlank(message = "{project.description.not.null}")
    @Size(max = 512, message = "{project.description.max}")
    private String description;

    @NotEmpty(message = "{project.tag.not.null}")
    @CollectionLength(min = 1, max = 5, message = "{project.tag.not.null}")
    private Set<String> tags;

    private String logoBase64;

    @DecimalMin(value = "-90.0", message = "{project.latitude.size}")
    @DecimalMax(value = "90.0", message = "{project.latitude.size}")
    @Digits(integer = 3, fraction = 1)
    private Double latitude;

    @DecimalMin(value = "-180.0", message = "{project.longitude.size}")
    @DecimalMax(value = "180.0", message = "{project.longitude.size}")
    @Digits(integer = 4, fraction = 1)
    private Double longitude;

    @PositiveOrZero
    private int ownerSum;

    @PositiveOrZero
    private int collectedSum;

    @PositiveOrZero
    private int startSum;

    @NotNull(message = "{project.collectDeadline.not.null}")
    @FutureDate
    private String collectDeadline;

    @PositiveOrZero
    private int goalSum;

    @NotNull(message = "{project.goalDeadline.not.null}")
    @FutureDate
    private String goalDeadline;

    @NotEmpty
    private Map<SocialNetworkName, String> socialNetworks;
}
