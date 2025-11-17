package com.mycompany.utils;

import com.mycompany.entities.Employees;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.BorderStyle;

public class ExcelExporter {

    public static  byte[] exportPeople(List<Employees> employees) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("People");

// Header style
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.DOUBLE);
        headerStyle.setBorderTop(BorderStyle.DOUBLE);
        headerStyle.setBorderRight(BorderStyle.DOUBLE);
        headerStyle.setBorderLeft(BorderStyle.DOUBLE);

// Data style (example)
        XSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.LEFT);

// Create header row
        Row header = sheet.createRow(0);
        String[] columns = new String[]{"Code","UserName","E-mail","Full Name","Gender","Age","Job","Department","Creation Date","Active"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

// Fill data
        int rowIdx = 1;
        for (Employees e : employees) {
            Row row = sheet.createRow(rowIdx++);
            Cell c0 = row.createCell(0);
            c0.setCellValue(e.getEmployeeID());
            c0.setCellStyle(dataStyle);

            Cell c1 = row.createCell(1);
            c1.setCellValue(e.getUsername());
            c1.setCellStyle(dataStyle);

            Cell c2 = row.createCell(2);
            c2.setCellValue(e.getEmail());
            c2.setCellStyle(dataStyle);
            
            Cell c3 = row.createCell(3);
            c3.setCellValue(e.getFullName());
            c3.setCellStyle(dataStyle);

            Cell c4 = row.createCell(4);
            c4.setCellValue(e.getGender());
            c4.setCellStyle(dataStyle);

            Cell c5 = row.createCell(5);
            c5.setCellValue(e.getAge());
            c5.setCellStyle(dataStyle);
            
            Cell c6 = row.createCell(6);
            c6.setCellValue(e.getJob());
            c6.setCellStyle(dataStyle);

            Cell c7 = row.createCell(7);
            String s; 
            try{
                  s = e.getDepID().getDepartmentName();
            }
            catch(ExceptionInInitializerError ex){
                s="N/A";
            }
            c7.setCellValue(s);
            c7.setCellStyle(dataStyle);

            Cell c8 = row.createCell(8);
            c8.setCellValue(e.getCreationDate());
            c8.setCellStyle(dataStyle);
            
            Cell c9 = row.createCell(9);
            c9.setCellValue(e.getActive());
            c9.setCellStyle(dataStyle);
        }

// Autosize columns
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
          // Write to memory
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            workbook.write(baos);
            workbook.close();

     
          return baos.toByteArray();
    }
}
