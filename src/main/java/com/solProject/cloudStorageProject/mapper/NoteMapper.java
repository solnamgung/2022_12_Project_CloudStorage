package com.solProject.cloudStorageProject.mapper;

import com.solProject.cloudStorageProject.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getNotes(Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNote(Integer noteId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer createNote(Note note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} " +
            "WHERE noteid = #{noteId}" )
    Integer update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void delete(Integer noteId);
}
