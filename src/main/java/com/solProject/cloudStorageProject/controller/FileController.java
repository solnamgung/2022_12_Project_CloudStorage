package com.solProject.cloudStorageProject.controller;

import com.solProject.cloudStorageProject.model.File;
import com.solProject.cloudStorageProject.model.User;
import com.solProject.cloudStorageProject.service.FileService;
import com.solProject.cloudStorageProject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/files")
public class FileController {
    private FileService fileService;
    private UserService userService;

    @Autowired
    @Value("${files.max-file-size}")
    private DataSize maximumFileSize;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }
    @PostMapping("/saving")
    public String fileUpload(@RequestParam("fileUpload")MultipartFile multipartFile, Model model, Authentication authentication) throws IOException {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        String filename = multipartFile.getOriginalFilename();

        if(!fileService.fileIsAvailable(userId, filename)) {
            model.addAttribute("error", "FILE ALREADY EXISTS.");
            model.addAttribute("success", false);
            return "result";
        } else {
            String contentType = multipartFile.getContentType();
            Long fileSize = multipartFile.getSize();
            byte[] fileData = multipartFile.getBytes();

            if(multipartFile.getSize()  <= maximumFileSize.toBytes()) {
                fileService.uploadFile(new File(null, filename, contentType, fileSize, userId, fileData ));
                model.addAttribute("success", true);
            }
            return "result";
        }
    }
    @GetMapping("/view/{fileId}")
    public void viewFile(@PathVariable("fileId") Integer fileId, HttpServletResponse response, Authentication authentication) throws IOException {
        User currentUser = userService.getUser(authentication.getName());
        File file = fileService.getFile(fileId);

        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getFilename() + "\"");
        response.setContentLengthLong(file.getFileSize());

        OutputStream ops = response.getOutputStream();
        try {
            ops.write(file.getFileData(), 0, file.getFileData().length);
        } catch (Exception e) {

        } finally {
            ops.close();
        }
    }
    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId) {
        fileService.delete(fileId);
        return "redirect:/result";
    }

}
