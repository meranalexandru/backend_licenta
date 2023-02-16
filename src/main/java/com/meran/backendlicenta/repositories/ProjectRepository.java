package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.models.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    public Project getProjectUsersAndIssuesById(Long id);

    @Query("SELECT issues from projects where projects.id =:#{id} and issues.status = :#{status}")
    List<Issue> getAllIssuesByProjectIdAndStatus(Integer id, String status);
}
