package com.dkkcorp.sprinnboot.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/","","index"})
    public String index(Model model){
        return "view/index";
    }
    @GetMapping("/manager/")
    public String managers(){
        return "manager-space";
    }
    @GetMapping("/admin/")
    public String admin(){
        return "admin-space";
    }
    @GetMapping("/common/")
    public String common(){
        return "common-space";
    }
    @RequestMapping("/denied")
    public String denied(){
        return "denied";
    }
}

