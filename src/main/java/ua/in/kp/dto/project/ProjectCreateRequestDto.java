package ua.in.kp.dto.project;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import ua.in.kp.enumeration.SocialNetworkName;
import ua.in.kp.validator.CollectionLength;

@Data
@AllArgsConstructor
public class ProjectCreateRequestDto {

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(max = 150)
    private String summary;

    @NotBlank(message="{project.description.not.null}")
    @Size(max = 512, message="{project.description.max}")
    private String description;

    @NotEmpty(message="{project.tag.not.null}")
    @CollectionLength(min = 1, max = 5, message="{project.tag.not.null}")
    private Set<String> tags;

    private String logoImgUrl;

    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    @Digits(integer = 3, fraction = 1)
    private double latitude;

    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    @Digits(integer = 4, fraction = 1)
    private double longitude;

    @PositiveOrZero
    private int ownerSum;

    @PositiveOrZero
    private int collectedSum;

    @PositiveOrZero
    private int startSum;

    @NotNull(message = "{project.collectDeadline.not.null}")
    private String collectDeadline;

    @PositiveOrZero
    private int goalSum;

    @NotNull(message = "{project.goalDeadline.not.null}")
    private String goalDeadline;

    @NotNull
    private Map<SocialNetworkName, String> networksLinks;

}
