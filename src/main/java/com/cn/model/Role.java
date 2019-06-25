package com.cn.model;

import java.util.List;

public class Role {
    private int rid;
    private String rname;
    private int superid;
    private List<User> user;
    public List<User> getUser() {
        return user;
    }
    public void setUser(List<User> user) {
        this.user = user;
    }
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
    }
    public String getRname() {
        return rname;
    }
    public void setRname(String rname) {
        this.rname = rname;
    }
    public int getSuperid() {
        return superid;
    }
    public void setSuperid(int superid) {
        this.superid = superid;
    }
}
