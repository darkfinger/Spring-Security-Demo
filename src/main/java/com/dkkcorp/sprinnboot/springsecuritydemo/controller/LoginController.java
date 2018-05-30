package com.dkkcorp.sprinnboot.springsecuritydemo.controller;

import com.dkkcorp.sprinnboot.springsecuritydemo.config.Role;
import com.dkkcorp.sprinnboot.springsecuritydemo.model.User;
import com.dkkcorp.sprinnboot.springsecuritydemo.service.RoleService;
import com.dkkcorp.sprinnboot.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/login")
    public String loginPage(){
        //init();
        return "loginPage";
    }


    public void init(){
        Role admin= new Role("ROLE_ADMIN");
        Role empo= new Role("ROLE_EMPO");
        Role man= new Role("ROLE_MAN");

        User user=new User("dav","123");
        user.getRole().add(admin);
        user.getRole().add(empo);
        userService.saveUser(user);

        User user2=new User("mary","123");
        user2.getRole().add(man);
        user2.getRole().add(empo);
        userService.saveUser(user2);

        User user3=new User("john","123");
        user3.getRole().add(empo);
        userService.saveUser(user3);

    }
}
