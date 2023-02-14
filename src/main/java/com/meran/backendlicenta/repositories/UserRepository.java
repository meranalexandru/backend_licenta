package com.meran.backendlicenta.repositories;

import com.meran.backendlicenta.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByName(String name);

    public User findById(Integer id);
}
