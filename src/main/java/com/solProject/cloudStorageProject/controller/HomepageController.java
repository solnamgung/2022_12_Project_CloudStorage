package com.solProject.cloudStorageProject.controller;

import com.solProject.cloudStorageProject.model.Note;
import com.solProject.cloudStorageProject.model.User;
import com.solProject.cloudStorageProject.service.EncryptionService;
import com.solProject.cloudStorageProject.service.NoteService;
import com.solProject.cloudStorageProject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomepageController {
    private UserService userService;
    private EncryptionService encryptionService;
    private NoteService noteService;

    public HomepageController(UserService userService, EncryptionService encryptionService, NoteService noteService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.noteService = noteService;
    }

    @GetMapping("/home")
    public String homePageList(Authentication authentication, Model model, Note note) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        List<Note> notes = noteService.getNotes(userId);
        model.addAttribute("notes", notes);
        return "home";
    }
}
