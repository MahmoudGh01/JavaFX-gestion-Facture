/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;

import app.aquaclean.entities.BL;
import app.aquaclean.entities.Client;
import app.aquaclean.entities.Facture;
import app.aquaclean.entities.ProduitBL;
import app.aquaclean.services.BLCRUD;
import app.aquaclean.services.ClientCRUD;
import app.aquaclean.services.FactureCRUD;
import app.aquaclean.services.ProduitBLCRUD;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FactureController implements Initializable {
    
    @FXML
    private DatePicker PCK_fin;
    @FXML
    private DatePicker PCK_deb;
    @FXML
    private ComboBox<Client> CBClient;
    @FXML
    private TableView<BL> TBV_BL;
    @FXML
    private TableColumn<BL, Integer> Id_BL;
    @FXML
    private TableColumn<BL, String> Client;
    @FXML
    private TableColumn<BL, Date> Date_BL;
    @FXML
    private TableColumn<BL, Double> Montant;
    @FXML
    private Button Btn_add;
    @FXML
    private Button Btn_print;
    @FXML
    private Label Timbre;
    @FXML
    private Label LB_THT;
    @FXML
    private Label LB_TTC;
    @FXML
    private Label LB_Net;
    @FXML
    private Button Btn_Invoice;
    @FXML
    private TableView<ProduitBL> TBV_Inv;
    @FXML
    private TableColumn<ProduitBL, Integer> qte;
    @FXML
    private TableColumn<ProduitBL, String> nom_prod;
    @FXML
    private TableColumn<ProduitBL, Double> PUHT;
    @FXML
    private TableColumn<ProduitBL, Double> THT;
    @FXML
    private TableColumn<ProduitBL, Double> TTC;
    @FXML
    private TableColumn<ProduitBL, Double> TVA;
    @FXML
    private Button Btn_update;
    @FXML
    private Button Btn_Annuler;
    ObservableList<BL> selectedBLs;
    // Create a new Invoice object
    Facture invoice = new Facture();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mapping des colonnes de la table avec les propriétés de l'objet BL
        Id_BL.setCellValueFactory(new PropertyValueFactory<>("id"));
        //B_Client_Col.setCellValueFactory(new PropertyValueFactory<>("client"));
        Client.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getName()));
        Client.setCellFactory(column -> new TableCell<BL, String>() {
            @Override
            protected void updateItem(String Client, boolean empty) {
                super.updateItem(Client, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(Client);
                }
            }
        });



        // Récupération de la liste de BL
        BLCRUD C = new BLCRUD();
        List<BL> blList = C.listerBL();

        // Ajout de chaque BL dans la table
        TBV_BL.getItems().addAll(blList);
        TBV_BL.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        CBClient();
    }
    
    private void CBClient() {
        ClientCRUD P = new ClientCRUD();
        
        ObservableList<Client> list = FXCollections.observableArrayList(P.listerClient());
        
        CBClient.setItems(list);
        
    }
    
    private List<ProduitBL> mergeSelectedBLs(ObservableList<BL> selectedBLs) {
        // Create a map to store merged ProduitBL objects based on their ID
        Map<Integer, ProduitBL> mergedProduitsMap = new HashMap<>();
        
        for (BL bl : selectedBLs) {
            // Set the list of products for the BL object
            List<ProduitBL> produits = new ProduitBLCRUD().listerProduitBL(bl);
            bl.setProduits(produits);
            System.out.println(bl);
            for (ProduitBL produitBL : bl.getProduits()) {
                if (produitBL.getId_Produit() != 0) {
                    int produitId = produitBL.getId_Produit();
                    //  System.out.println(produitId);
                    if (mergedProduitsMap.containsKey(produitId)) {
                        // Merge quantities for duplicate products
                        ProduitBL existingProduitBL = mergedProduitsMap.get(produitId);
                        existingProduitBL.setQte(existingProduitBL.getQte() + produitBL.getQte());
                    } else {
                        // Add the new product to the merged list
                        mergedProduitsMap.put(produitId, produitBL);
                    }
                } else {
                    System.out.println("app.aquaclean.views.FacturationController.Btn_Invoice()");
                }
            }
        }

        // Create a list of merged ProduitBL objects
        List<ProduitBL> mergedProduits = new ArrayList<>(mergedProduitsMap.values());
        
        return mergedProduits;
        
    }

    @FXML
    private void Btn_Invoice(ActionEvent event) {

        // Retrieve selected BLs from the TableView
        selectedBLs = TBV_BL.getSelectionModel().getSelectedItems();
        if (TBV_BL.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune BL sélectionné");
            alert.showAndWait();
            return;
        }
        
        List<ProduitBL> mergedProduits = mergeSelectedBLs(selectedBLs);
        ShowProduit(mergedProduits);
        System.out.println(mergedProduits);
        // Calculate the total amount for the invoice
        double totalAmount = calculateTotal(mergedProduits);
        
        invoice.setDate(new Date(System.currentTimeMillis())); // Set the invoice date to the current date
        invoice.setNet(totalAmount);
        invoice.setProduits(mergedProduits);
        // Set any other relevant attributes for the invoice
        // Assuming you have an InvoiceCRUD service to manage invoices
        FactureCRUD invoiceCRUD = new FactureCRUD();
        invoiceCRUD.ajouterFacture(invoice);

        // Generate the invoice PDF using the merged ProduitBL objects and the newly created invoice
    }
    
    private double calculateTotal(List<ProduitBL> produits) {
        double total = 0.0;
        for (ProduitBL produit : produits) {
            total += produit.getQte() * produit.getPU();
            System.out.println(total);
        }
        return total;
    }
    
    private void ShowProduit(List<ProduitBL> Prods) {
        ObservableList<ProduitBL> list = FXCollections.observableArrayList(Prods);
        
        nom_prod.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomP()));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        PUHT.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPU()).asObject());

        // Calculate the total amount (PU * Qte) for the THT column
        THT.setCellValueFactory(data -> {
            double pu = data.getValue().getPU();
            int qte = data.getValue().getQte();
            double totalAmount = pu * qte;
            return new SimpleDoubleProperty(totalAmount).asObject();
        });

        // Calculate the TVA (19% of THT) for the TVA column
        TVA.setCellValueFactory(data -> {
            double tvaRate = 0.19; // 19%
            double tht = data.getValue().getPU() * data.getValue().getQte();
            double tva = tht * tvaRate;
            return new SimpleDoubleProperty(tva).asObject();
        });

        // Calculate the TTC (THT + TVA) for the TTC column
        TTC.setCellValueFactory(data -> {
            double tht = data.getValue().getPU() * data.getValue().getQte();
            double tvaRate = 0.19; // 19%
            double tva = tht * tvaRate;
            double ttc = tht + tva;
            return new SimpleDoubleProperty(ttc).asObject();
        });
        
        TBV_Inv.setItems(list);
    }
    
    @FXML
    private void Btn_print(ActionEvent event) throws IOException {
        
        invoice.setProduits(mergeSelectedBLs(selectedBLs));
        invoice.generateInvoicePDF(invoice);
         Desktop.getDesktop().open(new File("invoice_imprime.pdf"));
        
    }
    
   
    
}
