package com.cn.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.cn.model.Scores;
import com.cn.model.User;

public interface UserService {
    public List<User> getAll();
    public void downLoadExcel(HttpServletResponse response,List<?> list);
    public List<Scores> list();
}
