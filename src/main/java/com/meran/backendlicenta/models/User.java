package com.meran.backendlicenta.models;

import jakarta.annotation.Nullable;
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

@Entity(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    @ManyToOne()
    @JoinColumn(name="project_id", nullable = true)
    private Project project;

    @Nullable
    @OneToMany(mappedBy = "user")
    Set<Comment> comments = new HashSet<>();

    @Nullable
    @ManyToMany(mappedBy = "users")
    Set<Issue> issues = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(name, user.name) && Objects.equals(avatarUrl, user.avatarUrl) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(project, user.project) && Objects.equals(comments, user.comments) && Objects.equals(issues, user.issues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatarUrl, createdAt, updatedAt, project, comments, issues);
    }
}
