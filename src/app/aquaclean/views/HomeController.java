/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;

import app.aquaclean.entities.BL;
import app.aquaclean.entities.Facture;
import app.aquaclean.entities.ProduitBL;
import app.aquaclean.services.BLCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeController implements Initializable {

    @FXML
    private Button Produit;
    @FXML
    private Button Client;
    @FXML
    private Button BL;
    @FXML
    private Button Facture;
    @FXML
    private BorderPane bp;
    @FXML
    private TableView<BL> TblBL;
    @FXML
    private TableView<Facture> TblF;
    @FXML
    private TableColumn<BL, Integer> B_Id_Col;
    @FXML
    private TableColumn<BL, String> B_Client_Col;
    @FXML
    private TableColumn<BL, Date> B_Date_Col;
    @FXML
    private TableColumn<?, ?> B_Action_Col;
    @FXML
    private TableColumn<Facture, Integer> F_Id_Col;
    @FXML
    private TableColumn<Facture, String> F_Client_Col;
    @FXML
    private TableColumn<Facture, Date> F_Date_Col;
    @FXML
    private TableColumn<?, ?> F_Action_Col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mapping des colonnes de la table avec les propriétés de l'objet BL
    B_Id_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
    //B_Client_Col.setCellValueFactory(new PropertyValueFactory<>("client"));
    B_Client_Col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getName()));
    B_Client_Col.setCellFactory(column -> new TableCell<BL, String>() {
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
    
    
   B_Date_Col.setCellValueFactory(new PropertyValueFactory<>("date_bl"));
   B_Date_Col.setCellFactory(column -> {
    return new TableCell<BL, Date>() {
        @Override
        protected void updateItem(Date item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(new SimpleDateFormat("dd/MM/yyyy").format(item));
            }
        }
    };
});

   
    // Récupération de la liste de BL
    BLCRUD C =new BLCRUD();
    List<BL> blList = C.listerBL();
    
    // Ajout de chaque BL dans la table
    TblBL.getItems().addAll(blList);
    }    

    @FXML
    private void Produit(MouseEvent event) {
        loadPage("Produit");
    }

    @FXML
    private void Client(MouseEvent event) {
        loadPage("Client");
    }

    @FXML
    private void BL(MouseEvent event) {
        loadPage("BLNew");
    }

    @FXML
    private void Facture(MouseEvent event) {
        loadPage("Facture");
    }
    
    private void loadPage(String page){
    
        Parent root =null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
        
    }
    
}
