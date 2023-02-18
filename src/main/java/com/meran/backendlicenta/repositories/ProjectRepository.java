package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.models.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    public Project findProjectById(Long id);

    @Query("SELECT i FROM issues i WHERE i.project.id = :projectId AND i.status = :status")
    List<Issue> findIssueByProjectIdAndStatus(@Param("projectId") Long projectId, @Param("status") String status);
}