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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BLNewController implements Initializable {

    BL bb = new BL();
    List<ProduitBL> Prods = new ArrayList();

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
    @FXML
    private TableColumn<ProduitBL, Void> Action_col;

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

        int Quantité = Integer.parseInt(Qte.getText());

        ProduitBL P = new ProduitBL(Quantité, Prod.getId(), Prod.getName(), Prod.getPrix());

        Prods.add(P);

        ShowProduit();

        lblTotl.setText(Double.toString(calculateTotal(Prods)));

    }

    @FXML
    private void btnValider(ActionEvent event) {

        Client C = CBClient.getSelectionModel().getSelectedItem();
        BLCRUD BC = new BLCRUD();

        BL B = new BL(C, Prods, calculateTotal(Prods), Date.valueOf(datepck.getValue()));

        BC.ajouterBL(B);

    }

    @FXML
    private void btnImprimer(ActionEvent event) {

        Client C = CBClient.getSelectionModel().getSelectedItem();

        BL bl = new BL(C, Prods, calculateTotal(Prods), Date.valueOf(datepck.getValue()));

        bl.imprimerBL(bl);
    }

    private void ComboxClient() {
        ClientCRUD P = new ClientCRUD();

        ObservableList<Client> list = FXCollections.observableArrayList(P.listerClient());

        CBClient.setItems(list);

    }

    private void ComboxProd() {
        ProduitsCRUD P = new ProduitsCRUD();

        ObservableList<Produits> list = FXCollections.observableArrayList(P.listerProduits());

        CBProduit.setItems(list);

    }

    private void ShowProduit() {

        ObservableList<ProduitBL> list = FXCollections.observableArrayList(Prods);

        QteCol.setCellValueFactory(new PropertyValueFactory<ProduitBL, Integer>("Qte"));
        ProduitCol.setCellValueFactory(new PropertyValueFactory<ProduitBL, String>("NomP"));

        // PUCol.setCellValueFactory(new PropertyValueFactory<ProduitBL,Double>("PU"));
        PUCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPU()).asObject());
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
        PTCol.setCellValueFactory(
                cellData -> new SimpleDoubleProperty(cellData.getValue().getQte() * cellData.getValue().getPU())
                        .asObject());
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

        Callback<TableColumn<ProduitBL, Void>, TableCell<ProduitBL, Void>> cellFactory = (
                final TableColumn<ProduitBL, Void> param) -> {
            final TableCell<ProduitBL, Void> cell = new TableCell<ProduitBL, Void>() {
                //
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        // FontAwesomeIconView editIcon = new
                        // FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;");
                        // editIcon.setStyle(
                        // " -fx-cursor: hand ;"
                        // + "-glyph-size:28px;"
                        // + "-fx-fill:#00E676;"
                        // );
                        //
                        // editIcon.setOnMouseClicked((MouseEvent event) -> {
                        //
                        // ProduitBL rdv = Tbv.getSelectionModel().getSelectedItem();

                        // FXMLLoader loader = new FXMLLoader();
                        // loader.setLocation(getClass().getResource("Pick_date.fxml"));
                        // try {
                        // loader.load();
                        // } catch (IOException ex) {
                        // Logger.getLogger(ListRDVController.class.getName()).log(Level.SEVERE, null,
                        // ex);
                        // }
                        //
                        // Pick_dateController addStudentController = loader.getController();
                        // addStudentController.setUpdate(true);
                        // addStudentController.setTextField(rdv.getDocteur().getId(), rdv.getId(),
                        // rdv.getDocteur().getNom());
                        // Parent parent = loader.getRoot();
                        // Stage stage = new Stage();
                        // stage.setScene(new Scene(parent));
                        // stage.initStyle(StageStyle.UTILITY);
                        // stage.show();
                        // ShowProduit();
                        //
                        // });
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            ProduitBL produitBL = Tbv.getSelectionModel().getSelectedItem();
                            Prods.remove(produitBL); // Remove the selected item from your Prods list
                            ShowProduit(); // Refresh the table to reflect the changes
                            lblTotl.setText(Double.toString(calculateTotal(Prods)));
                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };

        Action_col.setCellFactory(cellFactory);
        Tbv.setItems(list);

    }

    public double calculateTotal(List<ProduitBL> factureItems) {
        double total = 0.0;
        for (ProduitBL factureItem : factureItems) {
            total += factureItem.getQte() * factureItem.getPU();
        }
        return total;
    }

}
