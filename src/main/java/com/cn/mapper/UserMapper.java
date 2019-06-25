package com.cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cn.model.Role;
import com.cn.model.Scores;
import com.cn.model.Student;
import com.cn.model.User;

@Mapper
public interface UserMapper {
    public List<User> getAll();
    public Role role();
    public List<Scores> getScores();
    public Student getStudentBySno();
}
