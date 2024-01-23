package ua.in.kp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import lombok.Data;

@Data
public class CreateProjectDto {

        @NotBlank
        @Max(30)
        private String title;

        @NotBlank
        @Max(150)
        private String summary;

        @NotBlank
        @Max(512)
        private String description;

        @NotEmpty
//        @Size(1) ?? - set
//        @Max(5)
        private Set<String> tags;

//        @Pattern() - ? service
        private String logoLink;

        //valid 0,1
        private double latitude;
        private double longitude;

        @PositiveOrZero
        private int ownerSum;

        @PositiveOrZero
        private int collectedSum;

        @PositiveOrZero
        private int startSum;

//        format ?
        private LocalDateTime collectDeadline;

        @PositiveOrZero
        private int goalSum;

        private LocalDateTime goalDeadline;

        @NotNull
        //Maks - enum
        private Map<String, String> networksLinks;

}
