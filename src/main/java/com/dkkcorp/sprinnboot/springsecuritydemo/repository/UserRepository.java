package com.dkkcorp.sprinnboot.springsecuritydemo.repository;

import com.dkkcorp.sprinnboot.springsecuritydemo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String name);
}
