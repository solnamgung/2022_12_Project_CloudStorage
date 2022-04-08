package com.solProject.cloudStorageProject.service;

import com.solProject.cloudStorageProject.mapper.NoteMapper;
import com.solProject.cloudStorageProject.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }
    public List<Note> getNotes(Integer userId) {
        return noteMapper.getNotes(userId);
    }
    public Note getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }
    public Integer create(Note note) {
        return noteMapper.createNote(note);
    }
    public Integer update(Note note) {
        return noteMapper.update(note);
    }
    public void delete(Integer noteId) {
       noteMapper.delete(noteId);
    }

}
