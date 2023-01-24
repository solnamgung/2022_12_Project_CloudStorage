package com.solProject.cloudStorageProject.controller;

import com.solProject.cloudStorageProject.model.Credential;
import com.solProject.cloudStorageProject.model.File;
import com.solProject.cloudStorageProject.model.Note;
import com.solProject.cloudStorageProject.model.User;
import com.solProject.cloudStorageProject.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomepageController {
    private UserService userService;
    private EncryptionService encryptionService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomepageController(UserService userService, EncryptionService encryptionService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }
    @GetMapping("/home")
    public String homePage(Authentication authentication, Model model, File file, Note note, Credential credential) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        List<Note> noteList = noteService.getNotes(userId);
        model.addAttribute("notes", noteList);
        List<File> fileList = fileService.getFiles(userId);
        model.addAttribute("files", fileList);
        List<Credential> credentialList = credentialService.getCredentials(userId);
        model.addAttribute("credentials", credentialList);
        model.addAttribute("encryptionService", encryptionService);

        return "home";

    }
}
