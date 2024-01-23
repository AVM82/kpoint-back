package ua.in.kp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ua.in.kp.enumeration.ProjectState;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "project_id")
    private String projectId;

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User_entity owner;

    @Column(unique = true)
    private String title;

    private String summary;

    private String description;

    @ElementCollection
    private Set<String> tags;

    private String logoLink;

    private double latitude;
    private double longitude;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);

    @Enumerated(EnumType.STRING)
    private ProjectState state = ProjectState.NEW;

    @Column(name = "owner_sum")
    private int ownerSum;

    @Column(name = "collected_sum")
    private int collectedSum;

    @Column(name = "start_sum")
    private int startSum;

    @Column(name = "collect_deadline")
    private LocalDateTime collectDeadline;

    @Column(name = "goal_sum")
    private int goalSum;

    @Column(name = "goal_deadline")
    private LocalDateTime goalDeadline;

    @ElementCollection
    @Column(name = "networks-links")
    private Map<String, String> networksLinks;

}
