package com.meran.backendlicenta.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="issues")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String type;
    private String priority;
    private Integer listPosition;
    private String description;
    private String descriptionText;
    private Integer estimate;
    private Integer timeSpent;
    private Integer timeRemaining;
    private String status;
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Integer reporterId;

    @ManyToMany
    @JoinTable(name = "user_issue", joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "issue")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name="project_id")
    private Project project;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Float.compare(issue.listPosition, listPosition) == 0 && id.equals(issue.id) && Objects.equals(title, issue.title) && Objects.equals(type, issue.type) && Objects.equals(priority, issue.priority) && Objects.equals(description, issue.description) && Objects.equals(descriptionText, issue.descriptionText) && Objects.equals(estimate, issue.estimate) && Objects.equals(timeSpent, issue.timeSpent) && Objects.equals(timeRemaining, issue.timeRemaining) && Objects.equals(status, issue.status) && Objects.equals(createdAt, issue.createdAt) && Objects.equals(updatedAt, issue.updatedAt) && Objects.equals(reporterId, issue.reporterId) && Objects.equals(users, issue.users) && Objects.equals(comments, issue.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, priority, listPosition, description, descriptionText, estimate, timeSpent, timeRemaining, status, createdAt, updatedAt, reporterId, users, comments);
    }
}
