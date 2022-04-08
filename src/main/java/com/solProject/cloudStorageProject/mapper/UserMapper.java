package com.solProject.cloudStorageProject.mapper;

import com.solProject.cloudStorageProject.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
            "VALUES(#{username}, #{salt}, #{password},#{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer create(User user);
}
