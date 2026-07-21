package com.hr.hrms.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.hr.hrms.entity.Candidate;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

public class CandidatePDFExporter {

    private List<Candidate> listCandidates;

    public CandidatePDFExporter(List<Candidate> listCandidates) {
        this.listCandidates = listCandidates;
    }

    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        String[] headers = {
        	    "S.No",
        	    "Name",
        	    "Email",
        	    "Phone",
        	    "Applied Job",
        	    "Status"
        	};

        for(String h : headers){

            cell.setPhrase(new Phrase(h,font));

            table.addCell(cell);

        }

    }

    private void writeTableData(PdfPTable table){

    	int serialNo = 1;

    	for (Candidate c : listCandidates) {

    	    table.addCell(String.valueOf(serialNo++));
    	    table.addCell(c.getFullName());
    	    table.addCell(c.getEmail());
    	    table.addCell(c.getPhone());
    	    table.addCell(c.getAppliedJob());
    	    table.addCell(c.getStatus());

    	}

    }

    public void export(HttpServletResponse response)
            throws DocumentException, IOException{

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document,
                response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        font.setSize(18);

        Paragraph p =
                new Paragraph("Candidate Report",font);

        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        document.add(new Paragraph(" "));

        PdfPTable table =
                new PdfPTable(6);

        table.setWidthPercentage(100);

        table.setSpacingBefore(10);

        writeTableHeader(table);

        writeTableData(table);

        document.add(table);

        document.close();

    }

}