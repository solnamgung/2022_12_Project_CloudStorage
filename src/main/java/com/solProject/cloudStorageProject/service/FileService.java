package com.solProject.cloudStorageProject.service;

import com.solProject.cloudStorageProject.mapper.FileMapper;
import com.solProject.cloudStorageProject.mapper.UserMapper;
import com.solProject.cloudStorageProject.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;


    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;

    }
    public boolean fileIsAvailable(Integer userId, String filename) {
        return fileMapper.getFileByName(userId, filename) == null;
    }
    public Integer uploadFile(File file) {
        return fileMapper.create(file);
    }
    public List<File> getFiles(Integer userId) {
        return fileMapper.getFiles(userId);
    }
    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }
    public void delete(Integer fileId) {
        fileMapper.delete(fileId);
    }
}
