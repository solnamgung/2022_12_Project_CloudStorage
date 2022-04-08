package com.solProject.cloudStorageProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userId = #{userid}")
}
