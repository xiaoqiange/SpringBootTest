package com.cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cn.model.User;

@Mapper
public interface UserMapper {
    public List<User> getAll();
}
