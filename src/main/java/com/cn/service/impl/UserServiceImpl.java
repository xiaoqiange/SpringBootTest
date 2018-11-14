package com.cn.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.mapper.UserMapper;
import com.cn.model.User;
import com.cn.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper; 
    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
    @Override
    public void downLoadExcel(HttpServletResponse response, List<?> list) {
        // TODO Auto-generated method stub
        
    }
}
