package com.meran.backendlicenta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long issueId;
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
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Integer reporterId;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "issue_id",
            referencedColumnName = "issueId"
    )
    private List<Comment> comments;

}