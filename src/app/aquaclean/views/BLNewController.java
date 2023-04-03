/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;


import app.aquaclean.entities.BL;
import app.aquaclean.entities.Client;
import app.aquaclean.entities.ProduitBL;
import app.aquaclean.entities.Produits;
import app.aquaclean.services.BLCRUD;
import app.aquaclean.services.ClientCRUD;
import app.aquaclean.services.ProduitsCRUD;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BLNewController implements Initializable {
    
    BL bb =new BL();
    List<ProduitBL> Prods =new ArrayList() ;
    
    @FXML
    private DatePicker datepck;
    @FXML
    private Button btnAdd;
    @FXML
    private Label lblTotl;
     @FXML
    private TextField Qte;
    @FXML
    private ComboBox<Client> CBClient;
    @FXML
    private ComboBox<Produits> CBProduit;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnImprimer;
    @FXML
    private TableView<ProduitBL> Tbv;
    @FXML
    private TableColumn<ProduitBL, Integer> QteCol;
    @FXML
    private TableColumn<ProduitBL, String> ProduitCol;
    @FXML
    private TableColumn<ProduitBL, Double> PUCol;
    @FXML
    private TableColumn<ProduitBL, Double> PTCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ComboxClient();
       ComboxProd();
       ShowProduit();
       
    }    

    @FXML
    private void btnAdd(ActionEvent event) {
        
        Produits Prod = CBProduit.getSelectionModel().getSelectedItem();
        
        int Quantité = Integer.parseInt(Qte.getText()) ;
        
        
        ProduitBL P = new ProduitBL(Quantité,Prod );
        
       
        
        Prods.add(P);
        
        
        ShowProduit();
        
        lblTotl.setText(Double.toString(calculateTotal(Prods)));
        
        
    }

    @FXML
    private void btnValider(ActionEvent event) {
        
        Client C = CBClient.getSelectionModel().getSelectedItem();
        BLCRUD BC = new BLCRUD();
        
        
        BL B =new BL(C,Prods,calculateTotal(Prods), Date.valueOf(datepck.getValue()) );
 
        BC.ajouterBL(B);
        
      
        
        
        
        
    }

    @FXML
    private void btnImprimer(ActionEvent event) {
        
        Client C = CBClient.getSelectionModel().getSelectedItem();
        
        BL bl =new BL(C,Prods,calculateTotal(Prods), Date.valueOf(datepck.getValue()) );
        
        imprimerBL(bl);
    }
    
    private void ComboxClient() {
        ClientCRUD P = new ClientCRUD();
     
      ObservableList <Client> list = FXCollections.observableArrayList(P.listerClient()) ;
      
      CBClient.setItems(list);
        
    }
    private void ComboxProd() {
        ProduitsCRUD P = new ProduitsCRUD();
     
      ObservableList <Produits> list = FXCollections.observableArrayList(P.listerProduits()) ;
      
      CBProduit.setItems(list);
        
    }
    
    private void ShowProduit() {
        
        
     
      ObservableList <ProduitBL> list = FXCollections.observableArrayList(Prods) ;
        
        QteCol.setCellValueFactory(new PropertyValueFactory<ProduitBL,Integer>("Qte"));
        ProduitCol.setCellValueFactory(new PropertyValueFactory<ProduitBL,String>("Produit"));
        //PUCol.setCellValueFactory(new PropertyValueFactory<ProduitBL,Double>("PU"));
       PUCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProduit().getPrix()).asObject());
PUCol.setCellFactory(column -> new TableCell<ProduitBL, Double>() {
    @Override
    protected void updateItem(Double prixUnitaire, boolean empty) {
        super.updateItem(prixUnitaire, empty);
        if (empty) {
            setText("");
        } else {
            setText(String.format("%.2f", prixUnitaire));
        }
    }
});
PTCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQte() * cellData.getValue().getProduit().getPrix()).asObject());
PTCol.setCellFactory(column -> new TableCell<ProduitBL, Double>() {
    @Override
    protected void updateItem(Double prixTotal, boolean empty) {
        super.updateItem(prixTotal, empty);
        if (empty) {
            setText("");
        } else {
            setText(String.format("%.2f", prixTotal));
        }
    }
});


        
        Tbv.setItems(list);
        
        
    }
    
    
    public double calculateTotal(List<ProduitBL> factureItems) {
    double total = 0.0;
    for (ProduitBL factureItem : factureItems) {
        total += factureItem.getQte() * factureItem.getProduit().getPrix();
    }
    return total;
}
    
    
    public void imprimerBL(BL bl) {
    try {
        // Créer un nouveau document PDF
        Document document = new Document();

        // Créer un writer PDF
        String fileName = "BL_" + bl.getId() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        // Ouvrir le document
        document.open();

        Image img = Image.getInstance("Image1.png");
        img.scaleToFit(100, 100);
        img.setAlignment(Element.ALIGN_LEFT);
        document.add(img);

        // Ajouter les informations du bon de livraison
        Paragraph info = new Paragraph();
        info.add("Client : " + bl.getClient().getName()+ "\n");
        info.add("Date : " + bl.getDate_bl().toString() + "\n");
        info.add("Total : " + bl.getTotale() + "\n\n");
        document.add(info);

       
        
         // Créer une table PDF à partir du contenu du TableView
            PdfPTable pdfTable = new PdfPTable(Tbv.getColumns().size());
            addTableHeader(pdfTable, Tbv);
            addRows(pdfTable, Tbv.getItems(),Tbv);

            // Ajouter la table PDF au document
            document.add(pdfTable);

        // Fermer le document
        document.close();

        System.out.println("Le fichier " + fileName + " a été généré avec succès !");
    } catch (Exception e) {
        System.out.println("Erreur lors de la génération du fichier PDF : " + e.getMessage());
    }
}
     // Ajouter les en-têtes de colonnes à la table PDF
    private void addTableHeader(PdfPTable pdfTable, TableView table) {
        for (TableColumn column : (ObservableList<TableColumn>) table.getColumns()) {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(new com.itextpdf.text.BaseColor(150, 150, 150));
            header.setBorderWidth(1);
            header.setPhrase(new com.itextpdf.text.Phrase(column.getText()));
            pdfTable.addCell(header);
        }
    }

    // Ajouter les lignes de données à la table PDF
    private void addRows(PdfPTable pdfTable, ObservableList items, TableView table) {
    for (Object item : items) {
        for (TableColumn column : (ObservableList<TableColumn>) table.getColumns()) {
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new com.itextpdf.text.Phrase(column.getCellData(item).toString()));
            pdfTable.addCell(cell);
        }
    }
}

}


