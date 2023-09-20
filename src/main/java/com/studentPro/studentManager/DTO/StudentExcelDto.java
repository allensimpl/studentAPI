package com.studentPro.studentManager.DTO;

import lombok.Builder;

@Builder
public class StudentExcelDto {
    private String name;
    private String subject;
    private Integer mark;
}
