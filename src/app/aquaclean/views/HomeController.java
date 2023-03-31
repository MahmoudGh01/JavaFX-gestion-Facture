/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
