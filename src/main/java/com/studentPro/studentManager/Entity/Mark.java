package com.studentPro.studentManager.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int markId;


    @Column(name="st_id")
    int studentID;

    @Column(name = "subject_id")
    int subjectID;

    @Column(name = "mark")
    int mark;

    public Mark() {
    }

    public Mark(int studentID, int subject, int mark) {
        this.studentID = studentID;
        this.subjectID = subject;
        this.mark = mark;
    }

    public Mark(int markId, int studentID, int subject, int mark) {
        this.markId = markId;
        this.studentID = studentID;
        this.subjectID = subject;
        this.mark = mark;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getSubject() {
        return subjectID;
    }

    public void setSubject(int subject) {
        this.subjectID = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}
