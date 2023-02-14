package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.repositories.IssueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
