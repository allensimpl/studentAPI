package com.studentPro.studentManager.DTO;

public class SubjectRequestDTO {
    String subject;

    public SubjectRequestDTO() {
    }

    public SubjectRequestDTO(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
