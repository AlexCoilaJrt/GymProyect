package com.example.mspagomembresia.util;

import com.example.mspagomembresia.entity.Pagomembresia;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UserExcelExporter {
    private static final Logger logger = LoggerFactory.getLogger(UserExcelExporter.class);
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Pagomembresia> listUsers;

    public UserExcelExporter(List<Pagomembresia> pagomembresias) {
        this.listUsers = pagomembresias;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Monto", style);
        createCell(row, 2, "Fecha Pago", style);
        createCell(row, 3, "Cliente Gym ID", style);
        createCell(row, 4, "Membresia ID", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Date) {
            cell.setCellValue(value.toString());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Pagomembresia pagomembresia : listUsers) {
            try {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;

                createCell(row, columnCount++, pagomembresia.getId(), style);
                createCell(row, columnCount++, pagomembresia.getMonto(), style);
                createCell(row, columnCount++, pagomembresia.getFechaPago(), style);
                createCell(row, columnCount++, pagomembresia.getClientegymId(), style);
                createCell(row, columnCount++, pagomembresia.getMembresiaId(), style);
            } catch (Exception e) {
                logger.error("Error writing data line for user: " + pagomembresia, e);
            }
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new IOException("Error writing Excel file", e);
        } finally {
            workbook.close();
        }
    }
}




