/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;

import app.aquaclean.entities.BL;
import app.aquaclean.entities.Facture;
import app.aquaclean.entities.BL;
import app.aquaclean.services.BLCRUD;
import app.aquaclean.services.FactureCRUD;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

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
    private TableColumn<BL, Void> B_Action_Col;
    @FXML
    private TableColumn<Facture, Integer> F_Id_Col;
    @FXML
    private TableColumn<Facture, String> F_Client_Col;
    @FXML
    private TableColumn<Facture, Date> F_Date_Col;
    @FXML
    private TableColumn<Facture, Void> F_Action_Col;
    @FXML
    private Button Home;
    @FXML
    private TableColumn<BL, Double> B_Montant;
    @FXML
    private TableColumn<Facture, Double> Montant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowBLs();
        ShowFactures();
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

    @FXML
    private void home(ActionEvent event) {
        NewFXMain.setScene("Home");
    }

    private void loadPage(String page) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);

    }

    private void ShowBLs() {
        // Mapping des colonnes de la table avec les propriétés de l'objet BL
        B_Id_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
        // B_Client_Col.setCellValueFactory(new PropertyValueFactory<>("client"));
        B_Client_Col
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getName()));
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
        B_Montant.setCellValueFactory(new PropertyValueFactory<>("totale"));
        BLCRUD C = new BLCRUD();
        Callback<TableColumn<BL, Void>, TableCell<BL, Void>> cellFactory = (final TableColumn<BL, Void> param) -> {
            final TableCell<BL, Void> cell = new TableCell<BL, Void>() {
                //
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PRINT);
                        //
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;");
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;");

                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            BL rdv = TblBL.getSelectionModel().getSelectedItem();
                            rdv.imprimerBL(rdv);

                            

                        });
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            BL rdv = TblBL.getSelectionModel().getSelectedItem();
                            C.deleteBL(rdv);
                            ShowBLs();
                        });

                        HBox managebtn = new HBox(deleteIcon, editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };

        B_Action_Col.setCellFactory(cellFactory);

        // Récupération de la liste de BL
        ObservableList<BL> blList = FXCollections.observableArrayList(C.listerBL());
        //List<BL> blList = C.listerBL();

        // Ajout de chaque BL dans la table
        TblBL.setItems(blList);
    }

    private void ShowFactures() {
        // Mapping des colonnes de la table avec les propriétés de l'objet BL
        F_Id_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
        // B_Client_Col.setCellValueFactory(new PropertyValueFactory<>("client"));

        F_Date_Col.setCellValueFactory(new PropertyValueFactory<>("date"));
        F_Date_Col.setCellFactory(column -> {
            return new TableCell<Facture, Date>() {
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
        Montant.setCellValueFactory(new PropertyValueFactory<>("net"));
        FactureCRUD C = new FactureCRUD();
        Callback<TableColumn<Facture, Void>, TableCell<Facture, Void>> cellFactory = (
                final TableColumn<Facture, Void> param) -> {
            final TableCell<Facture, Void> cell = new TableCell<Facture, Void>() {
                //
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PRINT);
                        //
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;");
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;");

                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            Facture rdv = TblF.getSelectionModel().getSelectedItem();
                            rdv.generateInvoicePDF(rdv);

                        });
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Facture rdv = TblF.getSelectionModel().getSelectedItem();
                            C.deleteFacture(rdv);
                            ShowFactures();

                        });

                        HBox managebtn = new HBox(deleteIcon, editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };

        F_Action_Col.setCellFactory(cellFactory);

        // Récupération de la liste de BL
        ObservableList<Facture> blList = FXCollections.observableArrayList(C.listerFacture());
        //List<Facture> blList = C.listerFacture();

        // Ajout de chaque BL dans la table
        TblF.setItems(blList);
    }
}
