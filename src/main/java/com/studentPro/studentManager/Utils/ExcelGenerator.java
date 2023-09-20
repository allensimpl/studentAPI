package com.studentPro.studentManager.Utils;

import com.studentPro.studentManager.DTO.StudentExcelDto;
import com.studentPro.studentManager.Entity.Student;
import com.studentPro.studentManager.view.IStudentMarkView;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class ExcelGenerator {
    private List<StudentExcelDto> resultList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<StudentExcelDto> resultList){
        this.resultList = resultList;
        workbook = new XSSFWorkbook();
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        System.out.println(valueOfCell);
        if(valueOfCell == null)
            return;
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void writeHeader(){
        sheet = workbook.createSheet("Result");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
//        createCell(row,0,"ID",style);
        createCell(row,0,"Name",style);
        createCell(row,1,"Subject",style);
        createCell(row,2,"Mark",style);
    }
    private void write(){
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(StudentExcelDto result: resultList){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
//            createCell(row,columnCount++,result.getId(),style);
            createCell(row,columnCount++,result.getName(),style);
            createCell(row,columnCount++,result.getSubject(),style);
            createCell(row,columnCount,result.getMark(),style);
        }
    }
    public void generateExcelFile(HttpServletResponse response)throws IOException{
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
