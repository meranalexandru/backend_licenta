package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    public Project getProjectUsersAndIssuesById(Long id);

}
