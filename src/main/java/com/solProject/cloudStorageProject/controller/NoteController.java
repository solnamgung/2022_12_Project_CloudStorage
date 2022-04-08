package com.solProject.cloudStorageProject.controller;

import com.solProject.cloudStorageProject.model.Note;
import com.solProject.cloudStorageProject.model.User;
import com.solProject.cloudStorageProject.service.NoteService;
import com.solProject.cloudStorageProject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public String createOrUpdate(@ModelAttribute Note note, Model model, Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        if(note.getNoteId() == null) {
            note.setUserId(userId);
            noteService.create(note);
        } else
            noteService.update(note);

        model.addAttribute("success", true);
        return "redirect:/result";
    }
    @GetMapping("/notes/delete/{noteid}")
    public String delete(@PathVariable("noteid") Integer noteId) {
        noteService.delete(noteId);
        return "redirect:/result";
    }
}
