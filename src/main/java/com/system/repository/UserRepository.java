package com.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.system.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}