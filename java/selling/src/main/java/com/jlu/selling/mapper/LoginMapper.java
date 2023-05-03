package com.jlu.selling.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    String getPassword();
    Integer modifyPassword(String oldPassword,String newPassword);
}
