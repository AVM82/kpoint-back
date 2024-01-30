package ua.in.kp.dto.project;

import java.util.Set;
import lombok.Data;

@Data
public class GetAllProjectsDto {

    private String projectId;

    private String title;

    private String summary;

    private Set<String> tags;

    private String logoImgUrl;
}
