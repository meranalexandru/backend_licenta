package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Project;
import com.meran.backendlicenta.models.User;
import com.meran.backendlicenta.repositories.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getCurrentUser")
    public ResponseEntity<Project> getCurrentUser(@RequestParam Long id) {
        Project project = projectRepository.getProjectUsersAndIssuesById(id);
        if(project == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(project);
    }

//    @PutMapping("/update-project")
//    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
//        try {
////            Project updatedProject = projectRepository.(currentUser.getProjectId(), project);
////            return ResponseEntity.ok(updatedProject);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }
}
