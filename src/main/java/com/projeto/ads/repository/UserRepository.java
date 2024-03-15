package com.projeto.ads.repository;

import com.projeto.ads.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String Username);

}
