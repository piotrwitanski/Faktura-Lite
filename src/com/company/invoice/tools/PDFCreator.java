package com.company.invoice.tools;

import com.company.invoice.dto.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.*;
import java.util.List;

import static com.company.invoice.dictionaries.Dictionary.*;
import static com.company.invoice.dictionaries.Errors.PDF_ERROR;

public class PDFCreator {
    private Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);
    private Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.BOLD);

    private Customer customer;
    private Invoice invoice;
    private java.util.List<Item> items;
    private User user;
    private Payment payment;

    public void createPdf(String filename, Customer customer, Invoice invoice, List<Item> items, User user, Payment payment){
        //*TODO add bigger font size
        //*TODO add Payment table to PDF
        try {

            this.customer = customer;
            this.invoice = invoice;
            this.items = items;
            this.user = user;
            this.payment = payment;

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            addTitle(document);
            addEmptyLine(document, 1);
            addDateTable(document, "Data sprzedaży", invoice.getInvoiceDate());
            addDateTable(document, "Data wystawienia", invoice.getIssueDate());
            addEmptyLine(document, 2);

            PdfPTable namesTable = new PdfPTable(3);
            namesTable.setWidths(new int[] {6, 1, 6});
            namesTable.setWidthPercentage(100);
            createCustomerSellerTable(namesTable);


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

            document.add(summaryTable);
            addEmptyLine(document, 2);

            PdfPTable signsTable = new PdfPTable(3);
            signsTable.setWidths(new int[] {6, 5, 6});
            signsTable.setWidthPercentage(100);
            createSignTable(signsTable);

            document.add(signsTable);

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
        //*TODO date and invoice number will be added from db. Add position for invoice number to DB
        document.add(new Paragraph(INVOICE + " 01/11/2018", titleFont));
    }

    /**
     * This method adding date in right top corner of the pdf page
     * @param document
     * @param title is used to add date description
     * @param date specify date
     * @throws DocumentException
     */
    private void addDateTable(Document document, String title, String date) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 8);

        PdfPTable table = new PdfPTable(1);

        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.setWidthPercentage(20);
        PdfPCell titleCell = new PdfPCell(new Phrase(title, font));
        titleCell.setBorder(Rectangle.TOP);
        table.addCell(titleCell);

        PdfPCell cell = new PdfPCell(new Phrase(date, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        document.add(table);
    }

    private void createItemTitleTable(PdfPTable table) throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 8, Font.BOLD);

        createColumnTitle(table, NUMBER, font);
        createColumnTitle(table, NAME, font);
        createColumnTitle(table, QUANTITY, font);
        createColumnTitle(table, UNIT_OF_MEASURE, font);
        createColumnTitle(table, PRICE_NETTO, font);
        createColumnTitle(table, VALUE_NETTO, font);
        createColumnTitle(table, VAT, font);
        createColumnTitle(table, VAT_PRICE, font);
        createColumnTitle(table, PRICE_BRUTTO, font);

    }

    private void createColumnTitle(PdfPTable table, String title, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(title, font));
        cell.setRowspan(2);
        cell.setFixedHeight(25);
        table.addCell(cell);
    }

    private void addItemTablePositions(PdfPTable table) throws DocumentException {
        //*TODO download values from DB

        for (int i = 0; i < items.size(); i++) {
            addTableCell(table, Integer.toString(i + 1), normalFont,15, -1);
            addTableCell(table,items.get(i).getName(), normalFont, 15,-1);
            addTableCell(table,Integer.toString(items.get(i).getQuantity()), normalFont, 15,-1);
            addTableCell(table,items.get(i).getUnitOfMeasure(), normalFont, 15,-1);
            addTableCell(table,Double.toString(items.get(i).getPriceNetto()), normalFont, 15,2);
            addTableCell(table,Double.toString(items.get(i).getQuantity() * items.get(i).getPriceNetto()), normalFont, 15,2);
            addTableCell(table,Integer.toString(items.get(i).getVat()) + "%", normalFont, 15,2);
            addTableCell(table,Double.toString((items.get(i).getQuantity() * items.get(i).getPriceBrutto()) -
                    (items.get(i).getQuantity() * items.get(i).getPriceNetto())), normalFont, 15,2);
            addTableCell(table,Double.toString(items.get(i).getQuantity() * items.get(i).getPriceBrutto()), normalFont, 15,2);
        }

    }

    /**
     * This method is created to add values for specify item table positions.
     * @param table is Item table for invoice
     * @param font specify which font type want to use
     * @param text contain value that will be displayed in item table
     * @param cellBorder 0 -> without cellBorder; 1 -> top cellBorder; 15 -> all cellBorders; {@link Rectangle}
     * @param align 2 -> ALIGN_RIGHT; 1 -> ALIGN_CENTER; -1 -> ALIGN_UNDEFINED {@link Element}
     */
    private void addTableCell(PdfPTable table, String text, Font font, int cellBorder, int align) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(cellBorder);
        cell.setHorizontalAlignment(align);
        table.addCell(cell);
    }
//-------------------------------------------------------------------------------------------------------------------------------------------
    private void createCustomerSellerTable(PdfPTable table) throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontBold = new Font(bf, 8, Font.BOLD);
        Font font = new Font(bf, 8);

        addTableCell(table, SELLER, fontBold, 1, -1);
        addTableCell(table, "", fontBold, 0, -1);
        addTableCell(table, BUYER, fontBold, 1, -1);

        //Here we will add items from item list
        addTableCell(table, user.getName(), font, 0, -1);
        addTableCell(table, "", font, 0, -1);
        addTableCell(table, customer.getName(), font, 0, -1);

        addTableCell(table, user.getPostCode() + " " + user.getCity() +
                                ", " + user.getStreet(), font, 0, -1);
        addTableCell(table, "", font, 0, -1);
        addTableCell(table, customer.getPostCode() + " " + customer.getCity() +
                                ", " + customer.getStreet(), font, 0, -1);

        addTableCell(table, "NIP: " + user.getNIP(), font, 0, -1);
        addTableCell(table, "", font, 0, -1);
        addTableCell(table, "NIP: " + customer.getNIP(), font, 0, -1);
    }

//-------------------------------------------------------------------------------------------------------------------------------------------
    /**
     *Method create summary table for invoice
     * @param table
     */
    private void createSummaryTable(PdfPTable table) {
        double totalVat = 0;
        double totalNetto = 0;
        double totalBrutto = 0;

        addTableCell(table, PERCENTAGE, boldFont, 0, 2);
        addTableCell(table, NETTO, boldFont, 0, 2);
        addTableCell(table, VAT_VALUE, boldFont, 0, 2);
        addTableCell(table, BRUTTO, boldFont, 0, 2);

        for (Item item : items) {
            addTableCell(table, Integer.toString(item.getVat()), normalFont, 0, 2);
            addTableCell(table, Double.toString(item.getQuantity() * item.getPriceNetto()), normalFont, 0, 2);
            addTableCell(table, Double.toString((item.getQuantity() * item.getPriceBrutto()) -
                                                    (item.getQuantity() * item.getPriceNetto())), normalFont, 0, 2);
            addTableCell(table, Double.toString(item.getQuantity() * item.getPriceBrutto()), normalFont, 0, 2);

            totalNetto += item.getQuantity() * item.getPriceNetto();
            totalVat += (item.getQuantity() * item.getPriceBrutto()) -
                    (item.getQuantity() * item.getPriceNetto());
            totalBrutto += item.getQuantity() * item.getPriceBrutto();
        }

        addTableCell(table, "", boldFont, 1, 2);
        addTableCell(table, Double.toString(totalNetto), boldFont, 1, 2);
        addTableCell(table, Double.toString(totalVat), boldFont, 1, 2);
        addTableCell(table, Double.toString(totalBrutto), boldFont, 1, 2);
    }


    private void createSignTable(PdfPTable table) throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontBold = new Font(bf, 8, Font.BOLD);
        Font font = new Font(bf, 8);
        //*TODO change John London to user
        addTableCell(table, DOTS, font, 0, -1);
        addTableCell(table, "", font, 0, -1);
        addTableCell(table, DOTS, font, 0, -1);

        addTableCell(table, "", font, 0, -1);
        addTableCell(table, "", font, 0, -1);
        addTableCell(table, "John London", font, 0, 1);

        addTableCell(table, BUYER, fontBold, 0, 1);
        addTableCell(table, "", font, 0, -1);
        addTableCell(table, SELLER, fontBold, 0, 1);
    }

    private void addEmptyLine(Document document, int emptyLines) throws DocumentException {
        for (int i = 0; i < emptyLines; i++) {
            document.add(new Paragraph(" "));
        }
    }
}
