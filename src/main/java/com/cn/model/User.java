package com.cn.model;

import java.util.Date;

public class User {
    private String name;
    private String code;
    private String phone;
    private Integer sex;
    private Integer age;
    private Date birthday;
    
    public User() {
        // TODO Auto-generated constructor stub
    }
    
    
    public User(String name, String code, String phone, Integer sex, Integer age, Date birthday) {
        super();
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
}
