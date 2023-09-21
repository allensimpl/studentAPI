package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.StudentExcelDto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface IResultService{

    public List<StudentExcelDto> getResults(HttpServletResponse response) throws IOException;
}
