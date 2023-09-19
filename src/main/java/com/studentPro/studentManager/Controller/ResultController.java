package com.studentPro.studentManager.Controller;

import com.studentPro.studentManager.DTO.StudentRequestDTO;
import com.studentPro.studentManager.Service.ResultService;
import com.studentPro.studentManager.Service.StudentServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("results")
public class ResultController {
    @Autowired
    private ResultService service;
    @GetMapping("exportExcel")
    public void getExcelFile(HttpServletResponse response)throws IOException{
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    }
}