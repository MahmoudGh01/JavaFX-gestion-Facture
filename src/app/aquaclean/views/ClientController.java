/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;

import app.aquaclean.entities.Client;
import app.aquaclean.entities.Produits;
import app.aquaclean.services.ClientCRUD;
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
public class ClientController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private TextField MF;
    @FXML
    private TextField Adresse;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Client> TbV;
    @FXML
    private TableColumn<Client, Integer> IDCol;
    @FXML
    private TableColumn<Client, String> NomCol;
    @FXML
    private TableColumn<Client,String> MFCol;
    @FXML
    private TableColumn<Client, String> AdresseCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowClient();
    }    

    @FXML
    private void btnAdd(ActionEvent event) {
        
        ClientCRUD cc =new ClientCRUD();
        Client c =new Client(Nom.getText(),MF.getText(),Adresse.getText());
        
        cc.ajouterClient(c);
        ShowClient();
        
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
    }

    @FXML
    private void btnDelete(ActionEvent event) {
    }
    private void ShowClient() {
        ClientCRUD P = new ClientCRUD();
     
      ObservableList <Client> list = FXCollections.observableArrayList(P.listerClient()) ;
        IDCol.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<Client,String>("Name"));
        MFCol.setCellValueFactory(new PropertyValueFactory<Client,String>("MF"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<Client,String>("Adresse"));
       


        //System.out.print("test");
        TbV.setItems(list);
        
        
    }
    
}
