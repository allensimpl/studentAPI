package com.studentPro.studentManager.DTO;

public class SubjectResponseDTO {
    int id;
    String Subject;

    public SubjectResponseDTO(int id, String subject) {
        this.id = id;
        Subject = subject;
    }

    public SubjectResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
