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
//    private static List<StudentExcelDto> resultList;
//    private static XSSFWorkbook workbook;
//    private static XSSFSheet sheet;

//    public ExcelGenerator(List<StudentExcelDto> resultList){
//        this.resultList = resultList;
//        workbook = new XSSFWorkbook();
//    }
    private static void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style, XSSFSheet sheet) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
//        System.out.println(valueOfCell);
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
    private static void writeHeader(XSSFWorkbook workbook, XSSFSheet sheet){
//        sheet = workbook.createSheet("Result");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
//        createCell(row,0,"ID",style);
        createCell(row,0,"Name",style,sheet);
        createCell(row,1,"Subject",style,sheet);
        createCell(row,2,"Mark",style,sheet);
    }
    private static void write(XSSFWorkbook workbook, XSSFSheet sheet,List<StudentExcelDto> resultList){
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(StudentExcelDto result: resultList){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
//            createCell(row,columnCount++,result.getId(),style);
            createCell(row,columnCount++,result.getName(),style,sheet);
            createCell(row,columnCount++,result.getSubject(),style,sheet);
            createCell(row,columnCount,result.getMark(),style,sheet);
        }
    }
    public static void generateExcelFile(HttpServletResponse response,XSSFWorkbook workbook,XSSFSheet sheet, List<StudentExcelDto> resultList)throws IOException{
        writeHeader(workbook, sheet);
        write(workbook,sheet,resultList);
    }
}
