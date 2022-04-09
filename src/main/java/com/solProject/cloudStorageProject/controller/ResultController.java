package com.solProject.cloudStorageProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {
    @GetMapping("/result")
    public String getResultPage(Model model){
        model.addAttribute("success",true);
        return "result";
    }
}
