package com.company.invoice.tools;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.company.invoice.dictionaries.Dictionary.*;
import static com.company.invoice.dictionaries.Errors.PDF_ERROR;

public class PDFCreator {
    private Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);
    private Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.BOLD);

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
            createCustomerSellerTable(namesTable);
            addRowCustomerSeller(namesTable, "PROBUILD Cris Smith", "Frank Murphy");
            addRowCustomerSeller(namesTable, "45-900 Cracow, Street 34/12", "45-900 Warsaw, Street 56/3");
            addRowCustomerSeller(namesTable, "NIP: 453-5454-545", "NIP: 343-323-434");

            document.add(namesTable);
            addEmptyLine(document, 2);

            PdfPTable itemTable = new PdfPTable(9);
            itemTable.setWidths(new int[] {1, 3, 1, 1, 2, 2, 1, 2, 2});
            itemTable.setWidthPercentage(100);
            createItemTitleTable(itemTable);
            addItemTablePositions(itemTable);

            document.add(itemTable);
            addEmptyLine(document, 2);

            PdfPTable summaryTable = new PdfPTable(4);
            summaryTable.setWidths(new int[] {2, 2, 2, 2});
            summaryTable.setWidthPercentage(50);
            summaryTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            createSummaryTable(summaryTable);
            addSummaryValues(summaryTable, "23", "200", "70", "270", 0, normalFont);
            addSummaryValues(summaryTable, "23", "200", "70", "270", 0, normalFont);
            addSummaryValues(summaryTable, "", "400", "140", "540", 1, boldFont);

            document.add(summaryTable);
            document.close();
        }
        catch(DocumentException | IOException e) {
            System.out.println(PDF_ERROR +e.getMessage());
        }
    }

    /**
     * This method add title for PDF page
     * @param document
     * @throws DocumentException
     */
    private void addTitle(Document document) throws DocumentException {
        //*TODO date and invoice number will be added from db
        document.add(new Paragraph(INVOICE + " 01/11/2018", titleFont));
    }

    /**
     * This method adding date in right top corner of the pdf page
     * @param document
     * @param title is used to add date description
     * @param date specify date
     * @throws DocumentException
     */
    private void addDateTable(Document document, String title, String date) throws DocumentException {
        PdfPTable table = new PdfPTable(1);

        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidthPercentage(20);
        PdfPCell titleCell = new PdfPCell(new Phrase(title, normalFont));
        titleCell.setBorder(Rectangle.TOP);
//        titleCell.setBackgroundColor(new BaseColor(232, 230, 229));
        table.addCell(titleCell);

        PdfPCell cell = new PdfPCell(new Phrase(date, normalFont));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);
    }

    private void createItemTitleTable(PdfPTable table) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 8, Font.BOLD);

        PdfPCell lp = new PdfPCell(new Phrase(NUMBER, font));
        lp.setRowspan(2);
        lp.setFixedHeight(25);
        table.addCell(lp);

        PdfPCell name = new PdfPCell(new Phrase(NAME, font));
        name.setRowspan(2);
        table.addCell(name);

        PdfPCell quantity = new PdfPCell(new Phrase(QUANTITY, font));
        quantity.setRowspan(2);
        table.addCell(quantity);

        PdfPCell unitOfMeasure = new PdfPCell(new Phrase(UNIT_OF_MEASURE, font));
        unitOfMeasure.setRowspan(2);
        table.addCell(unitOfMeasure);

        PdfPCell priceNetto = new PdfPCell(new Phrase(PRICE_NETTO, font));
        priceNetto.setRowspan(2);
        table.addCell(priceNetto);

        PdfPCell valueNetto = new PdfPCell(new Phrase(VALUE_NETTO, font));
        valueNetto.setRowspan(2);
        table.addCell(valueNetto);

        PdfPCell vat = new PdfPCell(new Phrase(VAT, font));
        vat.setRowspan(2);
        table.addCell(vat);

        PdfPCell vatPrice = new PdfPCell(new Phrase(VAT_PRICE, font));
        vatPrice.setRowspan(2);
        table.addCell(vatPrice);

        PdfPCell priceBrutto = new PdfPCell(new Phrase(PRICE_BRUTTO, font));
        priceBrutto.setRowspan(2);
        table.addCell(priceBrutto);

//        document.add(table);
    }

    private void addItemTablePositions(PdfPTable table) throws DocumentException {
        //*TODO download values from DB
        PdfPCell f1 = new PdfPCell(new Phrase("1", normalFont));
        table.addCell(f1);

        PdfPCell f2 = new PdfPCell(new Phrase("Usluga", normalFont));
        table.addCell(f2);

        PdfPCell f3 = new PdfPCell(new Phrase("10", normalFont));
        table.addCell(f3);

        PdfPCell f4 = new PdfPCell(new Phrase("szt.", normalFont));
        table.addCell(f4);

        PdfPCell f5 = new PdfPCell(new Phrase("2300.98", normalFont));
        f5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f5);

        PdfPCell f6 = new PdfPCell(new Phrase("2300.98", normalFont));
        f6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f6);

        PdfPCell f7 = new PdfPCell(new Phrase("23%", normalFont));
        f7.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f7);

        PdfPCell f8 = new PdfPCell(new Phrase("505,45", normalFont));
        f8.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f8);

        PdfPCell f9 = new PdfPCell(new Phrase("2704,11", normalFont));
        f9.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(f9);

//        document.add(table);
    }

    private void createCustomerSellerTable(PdfPTable table) throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 8, Font.BOLD);


        PdfPCell sellerCell = new PdfPCell(new Phrase("Sprzedawca", font));
        sellerCell.setBorder(Rectangle.TOP);
        table.addCell(sellerCell);

        PdfPCell emptyCell = new PdfPCell((new Phrase("")));
        emptyCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptyCell);

        PdfPCell customerCell = new PdfPCell(new Phrase("Nabywca", font));
        customerCell.setBorder(Rectangle.TOP);
        table.addCell(customerCell);

//        document.add(table);
    }

    private void addRowCustomerSeller(PdfPTable table, String seller, String customer) throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 8);

        PdfPCell sellerCell = new PdfPCell(new Phrase(seller, font));
        sellerCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(sellerCell);

        PdfPCell emptyCell = new PdfPCell(new Phrase(""));
        emptyCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(emptyCell);

        PdfPCell customerCell = new PdfPCell(new Phrase(customer, font));
        customerCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(customerCell);

    }

    private void createSummaryTable(PdfPTable table) {
        PdfPCell percentageCell = new PdfPCell(new Phrase(PERCENTAGE, boldFont));
        percentageCell.setBorder(Rectangle.NO_BORDER);
        percentageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(percentageCell);

        PdfPCell nettoCell = new PdfPCell(new Phrase(NETTO, boldFont));
        nettoCell.setBorder(Rectangle.NO_BORDER);
        nettoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(nettoCell);

        PdfPCell vatCell = new PdfPCell(new Phrase(VAT_VALUE, boldFont));
        vatCell.setBorder(Rectangle.NO_BORDER);
        vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(vatCell);

        PdfPCell bruttoCell = new PdfPCell(new Phrase(BRUTTO, boldFont));
        bruttoCell.setBorder(Rectangle.NO_BORDER);
        bruttoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(bruttoCell);

    }

    /**
     * Method to add summary table for invoice
     * @param table
     * @param percentage
     * @param netto
     * @param vat
     * @param brutto
     * @param cellBorder 0 -> without border; 1 -> top border; {@link Rectangle}
     */
    private void addSummaryValues(PdfPTable table, String percentage, String netto, String vat, String brutto, int cellBorder, Font font) {
        PdfPCell percentageCell = new PdfPCell(new Phrase(percentage, font));
        percentageCell.setBorder(cellBorder);
        percentageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(percentageCell);

        PdfPCell nettoCell = new PdfPCell(new Phrase(netto, font));
        nettoCell.setBorder(cellBorder);
        nettoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(nettoCell);

        PdfPCell vatCell = new PdfPCell(new Phrase(vat, font));
        vatCell.setBorder(cellBorder);
        vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(vatCell);

        PdfPCell bruttoCell = new PdfPCell(new Phrase(brutto, font));
        bruttoCell.setBorder(cellBorder);
        bruttoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(bruttoCell);
    }

    private void addEmptyLine(Document document, int emptyLines) throws DocumentException {
        for (int i = 0; i < emptyLines; i++) {
            document.add(new Paragraph(" "));
        }
    }
}
