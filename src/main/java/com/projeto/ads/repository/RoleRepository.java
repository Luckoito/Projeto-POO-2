package com.projeto.ads.repository;

import com.projeto.ads.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String name);

}
