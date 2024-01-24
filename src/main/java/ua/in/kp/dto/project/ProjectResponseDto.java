package ua.in.kp.dto.project;

import java.util.Map;
import java.util.Set;
import lombok.Data;

@Data
public class ProjectResponseDto {

    private String projectId;

    private String title;

    private String summary;

    private String description;

    private Set<String> tags;

    private String logoImgUrl;

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
