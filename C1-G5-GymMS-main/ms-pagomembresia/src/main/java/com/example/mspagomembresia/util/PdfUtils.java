package com.example.mspagomembresia.util;

import com.example.mspagomembresia.entity.Pagomembresia;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfUtils {
    public static ByteArrayOutputStream generatePdfStream(List<Pagomembresia> pagomembresias
    ) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        // Write column names
        // Map<String, Object> firstRow = queryResults.get(0);
        for (Pagomembresia pagomembresia : pagomembresias) {
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph paragraphMonto  = new Paragraph(String.valueOf(pagomembresia.getMonto()), boldFont);
            Paragraph paragraphFechaPago  = new Paragraph(String.valueOf(pagomembresia.getFechaPago()), boldFont);
            Paragraph paragraphClienteGymId  = new Paragraph(String.valueOf(pagomembresia.getClientegymId()), boldFont);
            Paragraph paragraphMembresiaId  = new Paragraph(String.valueOf(pagomembresia.getMembresiaId()), boldFont);
            document.add(paragraphMonto);
            document.add(paragraphFechaPago);
            document.add(paragraphClienteGymId);
            document.add(paragraphMembresiaId);
        }
        document.add(new Paragraph("\n"));
        // Write data rows
        /*for (Map<String, Object> row : queryResults) {
            for (Object value : row.values()) {
                Paragraph paragraph = new Paragraph(value.toString());
                document.add(paragraph);
            }
            document.add(new Paragraph("\n"));
        }*/
        document.close();
        return outputStream;
    }
}
