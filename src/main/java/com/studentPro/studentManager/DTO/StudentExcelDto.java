package com.studentPro.studentManager.DTO;

import lombok.*;

public class StudentExcelDto {

    private String name;
    private String subject;
    private Integer mark;

    public StudentExcelDto() {

    }

    public StudentExcelDto(String name, String subject, Integer mark) {
        this.name = name;
        this.subject = subject;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
