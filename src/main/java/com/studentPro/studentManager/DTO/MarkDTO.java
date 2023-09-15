package com.studentPro.studentManager.DTO;

import jakarta.persistence.Column;

public class MarkDTO {
    private int markId;
    private int rollNo;

    public MarkDTO() {
        
    }

    private String subject;

    private int mark;

    public MarkDTO(int markId, int rollNo, String subject, int mark) {
        this.markId = markId;
        this.rollNo = rollNo;
        this.subject = subject;
        this.mark = mark;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
