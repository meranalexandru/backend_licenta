package com.meran.backendlicenta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String avatarUrl;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    private List<Comment> comments;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_issues_map",
            joinColumns = @JoinColumn(
                    name="user_id",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "issue_id",
                    referencedColumnName = "issueId"
            )
    )
    private List<Issue> issues;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public void addIssue(Issue issue){
        if(issues == null){
            issues = new ArrayList<>();
        }
        issues.add(issue);
    }

}
