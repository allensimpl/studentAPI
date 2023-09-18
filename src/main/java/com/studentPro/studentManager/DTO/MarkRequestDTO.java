package com.studentPro.studentManager.DTO;

import jakarta.persistence.Column;

public class MarkRequestDTO {
    int studentID;
    int subjectID;
    int mark;

    public MarkRequestDTO() {
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

    public MarkRequestDTO(int studentID, int subjectID, int mark) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.mark = mark;
    }

}
