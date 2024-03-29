package com.studentPro.studentManager.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class MarkDTO {
    private int markId;
    int studentID;
    int subjectID;
    int mark;

    public MarkDTO(int markId, int studentID, int subjectID, int mark) {
        this.markId = markId;
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.mark = mark;
    }

    public MarkDTO(int studentID, int subjectID, int mark) {
        this.studentID = studentID;
        this.subjectID = subjectID;
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

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
