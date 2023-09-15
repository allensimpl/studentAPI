package com.studentPro.studentManager.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue
    @Column(name = "student-id")
    private int markId;


    @Column(name="id")
    int rollNo;
    @Column(name = "subject")
    String subject;

    int mark;

    public Mark() {

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



    public Mark(int rollNo, String subject, int mark) {
        this.rollNo = rollNo;
        this.subject = subject;
        this.mark = mark;
    }


    public int getsId() {
        return this.rollNo;
    }

    public void setsId(int rollNo) {
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
