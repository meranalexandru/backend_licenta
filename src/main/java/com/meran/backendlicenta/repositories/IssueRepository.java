package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    public Issue findIssueByIdWithUsersAndComments(Long id);
}
