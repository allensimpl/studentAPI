package com.studentPro.studentManager.Controller;

import com.studentPro.studentManager.DTO.StudentExcelDto;
import com.studentPro.studentManager.Service.IResultService;
import com.studentPro.studentManager.Service.IStudentService;
import com.studentPro.studentManager.Utils.ExcelGenerator;
import com.studentPro.studentManager.view.IStudentMarkView;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("results")
public class ResultController {
    @Autowired
    private IResultService service;
    @GetMapping("exportExcel")
    public void getExcelFile(HttpServletResponse response)throws IOException{
        service.getResults(response);
    }
}