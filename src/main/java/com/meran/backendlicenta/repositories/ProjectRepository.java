package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.models.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    public Project findProjectByProjectId(Long id);

//    @Query("SELECT p FROM projects p WHERE i.projects.projectId = :projectId AND i.status = :status")
//    List<Issue> findIssueByProjectIdAndStatus(@Param("projectId") Long projectId, @Param("status") String status);
//
//    @Query("SELECT p FROM projects p LEFT JOIN FETCH p.issues WHERE p.id = :projectId")
//    Project findByIdWithIssues(@Param("projectId") Long projectId);
}