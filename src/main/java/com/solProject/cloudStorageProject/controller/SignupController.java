package com.solProject.cloudStorageProject.controller;

import com.solProject.cloudStorageProject.model.User;
import com.solProject.cloudStorageProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/signup")
    public String signupPage(@ModelAttribute("createUser") User user){
        return "signup";
    }
    @PostMapping("/signup")
    public String signupUser(@ModelAttribute("createUser") User user, Model model) {
        String signupError = null;

        if(!userService.usernameIsAvailable(user.getUsername())) {
            model.addAttribute("signupError", true);
            return "signup";
        }
        if(signupError == null) {
            int rowAdded = userService.createUser(user);
            if(rowAdded < 0) {
                signupError = "There was an error, please try again!";
            }
        }
        if(signupError == null) {
            model.addAttribute("successMessage", true);
            return "signup";
        } else
            model.addAttribute("signupError", true);

        return "signup";
    }
}
