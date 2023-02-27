package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    public Issue deleteIssueByIssueId(Long id);

    public Issue findIssueByIssueId(Long id);

}
