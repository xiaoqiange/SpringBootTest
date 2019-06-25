package com.cn.model;

import java.util.Date;
import java.util.List;

public class Student {
    private int id;
    private String sno;
    private String sname;
    private String ssex;
    private Date sbirthday;
    private String classes;
    private List<Scores> scores;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSno() {
        return sno;
    }
    public void setSno(String sno) {
        this.sno = sno;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSsex() {
        return ssex;
    }
    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
    public Date getSbirthday() {
        return sbirthday;
    }
    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }
    public String getClasses() {
        return classes;
    }
    public void setClasses(String classes) {
        this.classes = classes;
    }
    public List<Scores> getScores() {
        return scores;
    }
    public void setScores(List<Scores> scores) {
        this.scores = scores;
    }
}
