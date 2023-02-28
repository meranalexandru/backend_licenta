package com.meran.backendlicenta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name="projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String name;
    private String description;
    private String projectCategory;

        @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
               )
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "projectId"
    )
    private List<User> users;


    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
           )
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "projectId"
    )
    private List<Issue> issues;


    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}