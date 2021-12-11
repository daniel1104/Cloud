package co.com.poli.managerProject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projecttask")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private int priority;

    @Column(name = "hours")
    private Double hours;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "project_identifier", nullable = false, unique = true, updatable = false)
    private String projectIdentifier;

    @JsonBackReference()
    @JoinColumn(name = "backlog_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Backlog backlog;
}
