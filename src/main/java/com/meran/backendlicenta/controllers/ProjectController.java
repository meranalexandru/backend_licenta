package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Project;
import com.meran.backendlicenta.models.User;
import com.meran.backendlicenta.repositories.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

//    @PostMapping("/insertProject")
//    public Project insertProject(@RequestBody Project project) {
//       return projectRepository.save(project);
//    }
//
//    @PutMapping("/projects")
//    public ResponseEntity<Project> updateProject(@RequestBody Project project, @CurrentUser User currentUser) {
//        try {
//            Project updatedProject = projectRepository.(currentUser.getProjectId(), project);
//            return ResponseEntity.ok(updatedProject);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
