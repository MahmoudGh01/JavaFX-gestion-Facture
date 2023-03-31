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
import app.aquaclean.services.ClientCRUD;
import app.aquaclean.services.ProduitBLCRUD;
import app.aquaclean.services.ProduitsCRUD;
import java.net.URL;
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
import javafx.scene.control.Spinner;
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
    
    
    List<ProduitBL> Prods =new ArrayList() ;
    
    @FXML
    private DatePicker Date;
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
        
        
        
        
    }

    @FXML
    private void btnImprimer(ActionEvent event) {
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
}
