package com.customer.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.customer.assessment.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
    
    @Query("SELECT l FROM Login l WHERE l.username = :username AND l.password = :password")
    Login findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
