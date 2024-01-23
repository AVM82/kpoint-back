package ua.in.kp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import ua.in.kp.enums.ProjectState;
import jakarta.validation.Constraint;
@Data
public class ProjectDto {

    @JsonIgnore
    private Long projectId;

    //    private User_entity owner;
    @NotBlank
    @Max(30)
    private String title;

    @NotBlank
    @Max(150)
    private String summary;

    @NotBlank
    @Max(512)
    private String description;

    @NotBlank
    @Min(1)
    @Max(5)
    private Set<String> tags;

    private String logoBase64;

    private double latitude;
    private double longitude;

    private LocalDateTime createdAt;

    private ProjectState state = ProjectState.NEW;

    private int ownerSum;

    private int collectedSum;

    private int startSum;

    private LocalDateTime collectDeadline;

    private int goalSum;

    private LocalDateTime goalDeadline;

    @NotBlank
    private Map<String, String> networksLinks;
}
