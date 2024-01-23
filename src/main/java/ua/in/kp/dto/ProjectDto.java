package ua.in.kp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import lombok.Data;
import ua.in.kp.enumeration.ProjectState;

@Data
public class ProjectDto {

    private String projectId;

    private String title;

    private String summary;

    private String description;

    private Set<String> tags;

    private String logoBase64;

    private double latitude;
    private double longitude;

    private String createdAt;

    //mapstr. enum
    private String state;

    private int ownerSum;

    private int collectedSum;

    private int startSum;

    private String collectDeadline;

    private int goalSum;

    private String goalDeadline;

    private Map<String, String> networksLinks;
}
