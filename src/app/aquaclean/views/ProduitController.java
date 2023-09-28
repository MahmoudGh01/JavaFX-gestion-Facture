/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;

import app.aquaclean.entities.Produits;
import app.aquaclean.services.ProduitsCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProduitController implements Initializable {

    @FXML
    private TextField NomP;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField PUnitaire;
    @FXML
    private TableView<Produits> TvP;
    @FXML
    private TableColumn<Produits, Integer> IDCol;
    @FXML
    private TableColumn<Produits, String> NomCol;
    @FXML
    private TableColumn<Produits, Double> PUCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowProduit();
    }    

    @FXML
    private void btnAdd(ActionEvent event) {
        
        Produits p;
        p = new Produits(NomP.getText(),Double.parseDouble(PUnitaire.getText()));
        ProduitsCRUD pp= new ProduitsCRUD();
        
        pp.ajouterProduits(p);
        ShowProduit();
        
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
        TvP.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void btnDelete(ActionEvent event) {

    }
    
    private void ShowProduit() {
        ProduitsCRUD P = new ProduitsCRUD();
     
      ObservableList <Produits> list = FXCollections.observableArrayList(P.listerProduits()) ;
        IDCol.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<Produits,String>("Name"));
        PUCol.setCellValueFactory(new PropertyValueFactory<Produits,Double>("prix"));
       


        //System.out.print("test");
        TvP.setItems(list);
        
        
    }
    
    
}
