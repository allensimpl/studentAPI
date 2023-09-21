package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.StudentExcelDto;
import com.studentPro.studentManager.Repository.StudentRepository;
import com.studentPro.studentManager.Utils.ExcelGenerator;
import com.studentPro.studentManager.view.IStudentMarkView;
import com.studentPro.studentManager.mapper.StudentMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class ResultServiceImpl implements IResultService{
    @Autowired
    private StudentRepository repository;



    @Override
    public List<StudentExcelDto> getResults(HttpServletResponse response) throws IOException {
        setExcelHeader(response);
        List<StudentExcelDto> resultsList = StudentMapper.studentViewToDtoConvertor(repository.getResults());
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Result");
        ExcelGenerator.generateExcelFile(response,workbook,sheet,resultsList);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return StudentMapper.studentViewToDtoConvertor(repository.getResults());
    }

    private void setExcelHeader(HttpServletResponse response){
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey,headerValue);
    }
}
