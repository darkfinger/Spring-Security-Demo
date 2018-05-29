package com.dkkcorp.sprinnboot.springsecuritydemo.service;

import com.dkkcorp.sprinnboot.springsecuritydemo.config.Role;
import com.dkkcorp.sprinnboot.springsecuritydemo.repository.RoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        Role roleSaved=roleRepository.save(role);
        return roleSaved;
    }
}
