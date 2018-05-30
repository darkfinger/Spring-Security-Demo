package com.dkkcorp.sprinnboot.springsecuritydemo.repository;

import com.dkkcorp.sprinnboot.springsecuritydemo.config.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(String role);
}
