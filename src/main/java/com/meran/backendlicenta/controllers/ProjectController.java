package com.meran.backendlicenta.controllers;


import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.models.Project;
import com.meran.backendlicenta.models.User;
import com.meran.backendlicenta.repositories.IssueRepository;
import com.meran.backendlicenta.repositories.ProjectRepository;
import com.meran.backendlicenta.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    ProjectRepository projectRepository;

    UserRepository userRepository;
    IssueRepository issueRepository;

    public ProjectController(ProjectRepository projectRepository, UserRepository userRepository, IssueRepository issueRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.issueRepository = issueRepository;
    }

        @PostMapping(value = "/insertProject")
    public ResponseEntity<Project> insertProject(@RequestBody Project project) {
        if(project == null){
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        project.setCreatedAt(LocalDate.now());
        project.setUpdatedAt(LocalDateTime.now());
        projectRepository.save(project);
        return ResponseEntity.ok(project);
        }


    @GetMapping("/getCurrentProject")
    public ResponseEntity<Project> getCurrentProject(@RequestParam Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-project")
    public ResponseEntity<Project> updateProject(@RequestParam Long projectId, @RequestBody Project project) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setProjectCategory(project.getProjectCategory());
            existingProject.setUsers(project.getUsers());
            existingProject.setIssues(project.getIssues());
            existingProject.setUpdatedAt(LocalDateTime.now());
            projectRepository.save(existingProject);
            return ResponseEntity.ok(existingProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-project-users")
    public ResponseEntity<List<User>> getAllUsersInProject(@RequestParam Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<User> users = optionalProject.get().getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-project-issues")
    public ResponseEntity<List<Issue>> getAllIssuesInProject(@RequestParam Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Issue> issues = optionalProject.get().getIssues();
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/get-project-issues-by-status")
    public ResponseEntity<List<Issue>> getAllIssuesInProjectByStatus(@RequestParam Long projectId, @RequestParam String status) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Issue> issues = optionalProject.get().getIssues().stream()
                .filter(issue -> issue.getStatus().equals(status))
                .collect(Collectors.toList());
        return ResponseEntity.ok(issues);
    }


}