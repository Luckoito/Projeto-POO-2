package com.projeto.ads.repository;

import com.projeto.ads.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String Username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email); 

}
