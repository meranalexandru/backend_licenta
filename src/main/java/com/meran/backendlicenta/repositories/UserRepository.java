package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.Issue;
import com.meran.backendlicenta.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByName(String name);

    public User findByUserId(Long id);

    User findUserByName(String name);

//    @Query("select u from users u where u.project.projectId = ?1")
//    public List<User> getUsersByProjectId(Long projectId);

//    List<User> findAllUsersByProjectId(Long projectId);

}
