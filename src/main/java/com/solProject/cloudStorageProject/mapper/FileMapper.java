package com.solProject.cloudStorageProject.mapper;

import com.solProject.cloudStorageProject.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userid} AND filename = #{filename}")
    File getFileByName(Integer userid, String filename);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer create(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void delete(Integer fileId);

}
