package com.company.invoice.tools;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFCreator {
    private Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);

    public void createPdf(String filename){
        //*TODO add bigger font size
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            addTitle(document);
            addEmptyLine(document, 1);
            addDateTable(document, "Data sprzedaży", "24-11-2018");
            addDateTable(document, "Data wystawienia", "14-11-2018");
            addEmptyLine(document, 2);

            PdfPTable namesTable = new PdfPTable(3);
            namesTable.setWidths(new int[] {6, 1, 6});
            namesTable.setWidthPercentage(100);
            createCustomerSellerTable(document, namesTable);
            addEmptyLine(document, 2);

            PdfPTable itemTable = new PdfPTable(8);
            itemTable.setWidths(new int[]{1, 3, 1, 1, 2, 1, 2, 2});
            itemTable.setWidthPercentage(100);
            createItemTitleTable(document, itemTable);
            createItemTablePositions(document, itemTable);

            document.add(itemTable);
            document.close();
        }
        catch(DocumentException | IOException e) {
            System.out.println("PDF exception" +e.getMessage());
        }
    }

    private void addTitle(Document document) throws DocumentException {
        document.add(new Paragraph("Faktura 01/11/2018", titleFont));
    }

    private void addDateTable(Document document, String title, String date) throws DocumentException {
        PdfPTable table = new PdfPTable(1);


        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidthPercentage(20);
        PdfPCell titleCell = new PdfPCell(new Phrase(title, normalFont));
        titleCell.setBorder(Rectangle.TOP);
        titleCell.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(titleCell);

        PdfPCell cell = new PdfPCell(new Phrase(date, normalFont));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);
    }

    private void createItemTitleTable(Document document, PdfPTable table) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 8, Font.BOLD);

        PdfPCell lp = new PdfPCell(new Phrase("L.p.", font));
        lp.setRowspan(2);
        lp.setFixedHeight(25);
        lp.setBorder(Rectangle.LEFT | Rectangle.TOP);
        lp.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(lp);

        PdfPCell name = new PdfPCell(new Phrase("Nazwa", font));
        name.setRowspan(2);
        name.setBorder(Rectangle.LEFT | Rectangle.TOP);
        name.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(name);

        PdfPCell quantity = new PdfPCell(new Phrase("Ilość", font));
        quantity.setRowspan(2);
        quantity.setBorder(Rectangle.LEFT | Rectangle.TOP);
        quantity.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(quantity);

        PdfPCell unitOfMeasure = new PdfPCell(new Phrase("j.m.", font));
        unitOfMeasure.setRowspan(2);
        unitOfMeasure.setBorder(Rectangle.LEFT | Rectangle.TOP);
        unitOfMeasure.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(unitOfMeasure);

        PdfPCell priceNetto = new PdfPCell(new Phrase("Wartość netto", font));
        priceNetto.setRowspan(2);
        priceNetto.setBorder(Rectangle.LEFT | Rectangle.TOP);
        priceNetto.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(priceNetto);

        PdfPCell vat = new PdfPCell(new Phrase("VAT [%]", font));
        vat.setRowspan(2);
        vat.setBorder(Rectangle.LEFT | Rectangle.TOP);
        vat.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(vat);

        PdfPCell vatPrice = new PdfPCell(new Phrase("Wartość VAT", font));
        vatPrice.setRowspan(2);
        vatPrice.setBorder(Rectangle.LEFT | Rectangle.TOP);
        vatPrice.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(vatPrice);

        PdfPCell priceBrutto = new PdfPCell(new Phrase("Wartość brutto", font));
        priceBrutto.setRowspan(2);
        priceBrutto.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
        priceBrutto.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(priceBrutto);
    }

    private void createItemTablePositions(Document document, PdfPTable table) {
        //*TODO close Item table with BOTTOM border
        PdfPCell f1 = new PdfPCell(new Phrase("1", normalFont));
        f1.setBorder(Rectangle.LEFT);
        f1.setBackgroundColor(BaseColor.WHITE);
        table.addCell(f1);

        PdfPCell f2 = new PdfPCell(new Phrase("Usluga", normalFont));
        f2.setBorder(Rectangle.LEFT);
        f2.setBackgroundColor(BaseColor.WHITE);
        table.addCell(f2);

        PdfPCell f3 = new PdfPCell(new Phrase("10", normalFont));
        f3.setBorder(Rectangle.LEFT);
        f3.setBackgroundColor(BaseColor.WHITE);
        table.addCell(f3);

        PdfPCell f4 = new PdfPCell(new Phrase("szt.", normalFont));
        f4.setBackgroundColor(BaseColor.WHITE);
        f4.setBorder(Rectangle.LEFT);
        table.addCell(f4);

        PdfPCell f5 = new PdfPCell(new Phrase("2300.98", normalFont));
        f5.setBorder(Rectangle.LEFT);
        f5.setBackgroundColor(BaseColor.WHITE);
        f5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f5);

        PdfPCell f6 = new PdfPCell(new Phrase("23%", normalFont));
        f6.setBorder(Rectangle.LEFT);
        f6.setBackgroundColor(BaseColor.WHITE);
        f6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f6);

        PdfPCell f7 = new PdfPCell(new Phrase("505,45", normalFont));
        f7.setBorder(Rectangle.LEFT);
        f7.setBackgroundColor(BaseColor.WHITE);
        f7.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f7);

        PdfPCell f8 = new PdfPCell(new Phrase("2704,11", normalFont));
        f8.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        f8.setBackgroundColor(BaseColor.WHITE);
        f8.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f8);
    }

    private void createCustomerSellerTable(Document document, PdfPTable table) throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 8, Font.BOLD);


        PdfPCell sellerCell = new PdfPCell(new Phrase("Sprzedawca", font));
        sellerCell.setBorder(Rectangle.TOP);
        sellerCell.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(sellerCell);

        PdfPCell emptyCell = new PdfPCell((new Phrase("")));
        emptyCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptyCell);

        PdfPCell customerCell = new PdfPCell(new Phrase("Nabywca", font));
        customerCell.setBorder(Rectangle.TOP);
        customerCell.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(customerCell);

        document.add(table);
    }

    private void addEmptyLine(Document document, int emptyLines) throws DocumentException {
        for (int i = 0; i < emptyLines; i++) {
            document.add(new Paragraph(" "));
        }
    }
}
