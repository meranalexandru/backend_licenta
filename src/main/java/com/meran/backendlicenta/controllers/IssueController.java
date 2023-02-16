package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Comment;
import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.repositories.IssueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    IssueRepository issueRepository;

    @GetMapping("/issues/{issueId}")
    public ResponseEntity<Issue> getIssueWithUsersAndComments(@PathVariable Long issueId) {
        try {
            Issue issue = issueRepository.findIssueByIdWithUsersAndComments(issueId);
            return ResponseEntity.ok(issue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/issue/{issueId}")
    public ResponseEntity<Issue> removeComment(@PathVariable Long issueId) {
        try {
            Issue deletedIssue = issueRepository.deleteIssueById(issueId);
            return ResponseEntity.ok(deletedIssue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PutMapping("/update-issue")
//    public ResponseEntity<Issue> updateProject(@RequestBody Issue issue) {
//        try {
//            Issue updatedIssue = issueRepository.findIssueById(issue.getId());
//            updatedIssue.setBody(comment.getBody());
//            updatedIssue.setUpdatedAt(comment.getUpdatedAt());
//            updatedIssue.setCreatedAt(comment.getCreatedAt());
//            return ResponseEntity.ok(comment);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }
}
