package com.pd.jwt.mapper;

import com.pd.jwt.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author dpeng
 */
public interface UserMapper {

    @Select("select * from user where name = #{name" +
            "} and password = #{password} ")
    User login(User user);
}
