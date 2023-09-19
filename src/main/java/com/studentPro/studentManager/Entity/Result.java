package com.studentPro.studentManager.Entity;

public class Result {
    private String studentName;
    private String subjectName;
    private int mark;
    public Result() {
    }
    public Result(String studentName, String subjectName, int mark) {
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.mark = mark;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
