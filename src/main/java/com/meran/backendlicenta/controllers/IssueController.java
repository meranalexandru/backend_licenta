package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.repositories.IssueRepository;
import com.meran.backendlicenta.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Issue> getIssueById(@RequestParam Long issueId) {
        try {
            Issue issue = issueRepository.findIssueByIssueId(issueId);
            return ResponseEntity.ok(issue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update-issue")
    public ResponseEntity<Issue> updateIssue(@RequestParam Long issueId, @RequestBody Issue updatedIssue) {
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        if (optionalIssue.isPresent()) {
            Issue issue = optionalIssue.get();
            issue.setTitle(updatedIssue.getTitle());
            issue.setDescription(updatedIssue.getDescription());
            issue.setDescriptionText(updatedIssue.getDescriptionText());
            issue.setType(updatedIssue.getType());
            issue.setPriority(updatedIssue.getPriority());
            issue.setEstimate(updatedIssue.getEstimate());
            issue.setTimeSpent(updatedIssue.getTimeSpent());
            issue.setTimeRemaining(updatedIssue.getTimeRemaining());
            issue.setStatus(updatedIssue.getStatus());
            issue.setUpdatedAt(LocalDateTime.now());
            issueRepository.save(issue);
            return ResponseEntity.ok(issue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @Transactional
    @DeleteMapping("/delete-issue")
    public ResponseEntity<Issue> deleteIssue(@RequestParam Long issueId) {
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        if (!optionalIssue.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Issue issue = optionalIssue.get();
        issueRepository.delete(issue);
        return ResponseEntity.ok(issue);
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

}
