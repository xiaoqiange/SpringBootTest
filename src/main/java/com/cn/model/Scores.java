package com.cn.model;

public class Scores {
    private int id;
    private String sno;
    private String cno;
    private String degree;
    private Student student;
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
    public String getCno() {
        return cno;
    }
    public void setCno(String cno) {
        this.cno = cno;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
