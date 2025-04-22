package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.User.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {

    public static ByteArrayOutputStream exportUsersToExcel(List<User> users) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Create the header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("FirstName");
        headerRow.createCell(2).setCellValue("LastName");
        headerRow.createCell(3).setCellValue("Email");
        headerRow.createCell(4).setCellValue("Position");
        headerRow.createCell(5).setCellValue("Seniority");
        headerRow.createCell(6).setCellValue("Country");
        headerRow.createCell(7).setCellValue("Role");

        // Populate the data rows
        int rowIndex = 1;
        for (User user : users) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(user.getUserId());
            row.createCell(1).setCellValue(user.getFirstName() != null ? user.getFirstName() : "");
            row.createCell(2).setCellValue(user.getLastName() != null ? user.getLastName() : "");
            row.createCell(3).setCellValue(user.getEmail() != null ? user.getEmail() : "");
            row.createCell(4).setCellValue(user.getPosition() != null && user.getPosition().getName() != null ? user.getPosition().getName() : "");
            row.createCell(5).setCellValue(user.getSeniority() != null && user.getSeniority().getName() != null ? user.getSeniority().getName() : "");
            row.createCell(6).setCellValue(user.getCity() != null && user.getCity().getCountry() != null && user.getCity().getCountry().getCountryName() != null ? user.getCity().getCountry().getCountryName() : "");
            row.createCell(7).setCellValue(user.getRole() != null && user.getRole().getName() != null ? user.getRole().getName() : "");
        }


        // Write to output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream;
    }
}

