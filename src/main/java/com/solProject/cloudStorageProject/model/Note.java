package com.solProject.cloudStorageProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

}
