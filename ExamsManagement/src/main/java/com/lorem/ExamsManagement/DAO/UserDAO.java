package com.lorem.ExamsManagement.DAO;


import com.lorem.ExamsManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
