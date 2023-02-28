package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.repositories.IssueRepository;
import com.meran.backendlicenta.repositories.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    IssueRepository issueRepository;
    ProjectRepository projectRepository;

    public IssueController(IssueRepository issueRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/getIssueById")
    public ResponseEntity<Issue> getIssueWithUsersAndComments(@RequestParam Long issueId) {
        try {
            Issue issue = issueRepository.findIssueByIssueId(issueId);
            return ResponseEntity.ok(issue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping("/getIssueOnProjectId")
//    public ResponseEntity<List<Issue> > getIssueByIdAndStatus(@RequestParam Long projectId) {
//        try {
//           List<Issue> issues = projectRepository.findIssueByProjectIdAndStatus(projectId,"active" );
//            return ResponseEntity.ok(issues);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }



    @DeleteMapping("/removeIssue")
    public ResponseEntity<Issue> removeIssue(@RequestParam Long issueId) {
        try {
            Issue deletedIssue = issueRepository.deleteIssueByIssueId(issueId);
            return ResponseEntity.ok(deletedIssue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create-issue")
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue){
        try{
            issue.setCreatedAt(LocalDate.now());
            issue.setUpdatedAt(LocalDateTime.now());
            issueRepository.save(issue);
            return ResponseEntity.ok(issue);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PutMapping("/update-issue")
//    public ResponseEntity<Issue> updateProject(@RequestBody Issue issue) {
//        try {
//            Issue updatedIssue = issueRepository.findById(issue.getId()).orElseThrow();
//            updatedIssue.setTitle(issue.getTitle());
//            updatedIssue.setDescriptionText(issue.getDescriptionText());
//            updatedIssue.setStatus(issue.getStatus());
//            issueRepository.save(updatedIssue);
//            return ResponseEntity.ok(updatedIssue);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
