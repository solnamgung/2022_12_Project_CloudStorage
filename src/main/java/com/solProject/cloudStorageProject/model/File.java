package com.solProject.cloudStorageProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class File {
    private Integer fileId;
    private String filename;
    private String contentType;
    private Long fileSize;
    private Integer userId;
    private byte[]fileData;

}
