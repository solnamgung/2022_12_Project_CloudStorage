package com.solProject.cloudStorageProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String viewLogin(Model model) {
        return "login";
    }
}