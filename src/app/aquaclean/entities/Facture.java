/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.entities;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;
import java.awt.Desktop;
import com.github.royken.converter.FrenchNumberToWords;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import app.aquaclean.views.NewFXMain;

/**
 *
 * @author user
 */
public class Facture {
    private int id;
    private Client client;
    private Date date;
    private Double net;
    public List<ProduitBL> produits;

    public List<ProduitBL> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitBL> produits) {
        this.produits = produits;
    }

    public Facture() {
    }

    public Facture(int id, Date date, Double net) {
        this.id = id;
        this.date = date;
        this.net = net;
    }

    public Facture(Date date, Double net) {
        this.date = date;
        this.net = net;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", date=" + date + ", net=" + net + '}';
    }

    public void generateInvoicePDF(Facture invoice) {
        try {
            // Open the existing PDF file using PdfReader
            PdfReader reader = new PdfReader("Facture.pdf");

            // Create a PdfStamper object from the PdfReader and a FileOutputStream
            PdfStamper stamper = new PdfStamper(reader,
                    new FileOutputStream("Facture/invoice_" + invoice.id + "_" + invoice.date + ".pdf"));

            // Access the fields in the PDF using the AcroFields object
            AcroFields form = stamper.getAcroFields();
            System.out.println(invoice);
            // Fill in the fields with the desired values using the setField method
            form.setField("ID", String.valueOf(invoice.getId()));
            form.setField("DATE", invoice.getDate().toString());
            form.setField("CLIENT", invoice.getClient().getName());
            form.setField("ADRESSE", String.valueOf(invoice.getClient().getAdresse()));
            form.setField("MF", String.valueOf(invoice.getClient().getMF()));
            form.setField("Totale", String.format("%.3f", invoice.getNet()));
            form.setField("TVA", String.format("%.3f", invoice.getNet() * 0.19));
            form.setField("NET", String.format("%.3f", invoice.getNet() + invoice.getNet() * 0.19));
            // ...

            double net = invoice.getNet() + invoice.getNet() * 0.19;
            // Séparer la partie entière et la partie décimale du montant net
            int partieEntiere = (int) net;
            int partieDecimale = (int) ((net - partieEntiere) * 1000); // Supposant que nous travaillons avec des
                                                                       // centimes

            // Remplir le champ "somme_text" avec le montant en toutes lettres + le montant
            // après la virgule en chiffres
            form.setField("text", FrenchNumberToWords.convert((long) net) + " Dinars et "
                    + String.format("%03d", partieDecimale) + " millimes");

            // Create a PdfPTable object for the products table
            float tableWidth = (PageSize.A4.getWidth() - 72f * 2); // width of page minus left/right margin
            PdfPTable pdfTable = new PdfPTable(7); // Adjust the number of columns according to your product data
            // Set column widths
            float[] columnWidths = { 2f, 1.5f, 1.5f, 2f, 2f, 1f, 2f }; // Adjust the values as needed
            pdfTable.setWidths(columnWidths);
            pdfTable.setWidthPercentage(100);
            pdfTable.setTotalWidth(tableWidth);
            pdfTable.getDefaultCell().setPadding(3);
            pdfTable.getDefaultCell().setBorderWidth(1);
            pdfTable.getDefaultCell().setBorderColor(BaseColor.BLACK);
            pdfTable.getDefaultCell().setBackgroundColor(new BaseColor(225, 225, 225));
            pdfTable.getDefaultCell().setBackgroundColor(new BaseColor(150, 150, 150));
            pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Add the table header
            String[] tableHeaders = { "Désignation", "Unité", "Quantity", "P.U.H.T", "Totale HT", "TVA", "Totale TTC" };
            for (String header : tableHeaders) {
                PdfPCell headerCell = new PdfPCell(
                        new Phrase(header, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setBorderWidth(1);
                pdfTable.addCell(headerCell);
            }

            // Add the table rows with product data
            // Assuming you have a method to merge the selected BLs
            for (ProduitBL produitBL : invoice.getProduits()) {
                // Assuming you have methods to get product data like produitBL.getId(),
                // produitBL.getNom(), etc.

                PdfPCell nameCell = new PdfPCell(
                        new Phrase(produitBL.getNomP(), new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                PdfPCell unity = new PdfPCell(
                        new Phrase("Piéce", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(produitBL.getQte()),
                        new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                PdfPCell unitPriceCell = new PdfPCell(new Phrase(String.format("%.3f", produitBL.getPU()),
                        new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                double totaleHT = produitBL.getPU() * produitBL.getQte();
                PdfPCell totht = new PdfPCell(new Phrase(String.format("%.3f", totaleHT),
                        new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                double totaleTTC = (produitBL.getPU() * produitBL.getQte()) * 1.19;
                PdfPCell totttc = new PdfPCell(new Phrase(String.format("%.3f", totaleTTC),
                        new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                PdfPCell tva = new PdfPCell(new Phrase("19%", new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
                pdfTable.addCell(nameCell);
                pdfTable.addCell(unity);
                pdfTable.addCell(quantityCell);
                pdfTable.addCell(unitPriceCell);
                pdfTable.addCell(totht);
                pdfTable.addCell(tva);
                pdfTable.addCell(totttc);
            }

            // Add the table to the PDF
            PdfContentByte content = stamper.getOverContent(1);
            // Adjust the Y-coordinate to position the table higher on the page
            float yCoordinate = content.getPdfDocument().getPageSize().getHeight() - (pdfTable.getTotalHeight() + 230f); // Modify
                                                                                                                         // the
                                                                                                                         // 150f
                                                                                                                         // value
                                                                                                                         // as
                                                                                                                         // needed
            pdfTable.writeSelectedRows(0, -1, content.getPdfDocument().getPageSize().getWidth() / 10, yCoordinate,
                    content);

            // Close the PdfStamper object to finalize the filled PDF
            stamper.close();
            Desktop.getDesktop().open(new File("Facture/invoice_" + invoice.id + "_" + invoice.date + ".pdf"));
            System.out.println("Le fichier invoice_imprime.pdf a été généré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la génération du fichier PDF : " + e.getMessage());
        }
    }

}
