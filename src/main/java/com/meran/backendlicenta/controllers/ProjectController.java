package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.models.Project;
import com.meran.backendlicenta.models.User;
import com.meran.backendlicenta.repositories.IssueRepository;
import com.meran.backendlicenta.repositories.ProjectRepository;
import com.meran.backendlicenta.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        projectRepository.save(project);
        return ResponseEntity.ok(project);
        }


    @GetMapping("/getCurrentProject")
    public ResponseEntity<Project> getCurrentProject(@RequestParam Long id) {
        Project project = projectRepository.findProjectByProjectId(id);
        if(project == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(project);
    }

    @PutMapping("/update-project")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        try {
            Project updatedProject = projectRepository.findProjectByProjectId(project.getProjectId());
            updatedProject.setName(project.getName());
            updatedProject.setProjectCategory(project.getProjectCategory());
            updatedProject.setIssues(project.getIssues());
            updatedProject.setDescription(project.getDescription());
            updatedProject.setUsers(project.getUsers());
            projectRepository.save(updatedProject);
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}