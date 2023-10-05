/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.services;

import app.aquaclean.entities.Facture;
import app.aquaclean.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mahmo
 */
public class FactureCRUD {

    public int ajouterFacture(Facture invoice) {
        ResultSet rs = null;
        ProduitBLCRUD PBL = new ProduitBLCRUD(); // Assuming you have a similar CRUD class for ProduitBL
        int invoiceId = 0;
        try {
            String requete = "INSERT INTO Facture(Net, Date,Id_client) VALUES (?, ?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete,
                    Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, invoice.getNet());
            pst.setDate(2, invoice.getDate()); // Assuming you have a method to get the invoice date
            pst.setInt(3, invoice.getClient().getId());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();

            if (rs.next()) {
                 invoiceId = rs.getInt(1);
                PBL.ajouterProduitBL(invoiceId, invoice.getProduits()); // Add associated ProduitBL items
            }

            System.out.println("Invoice added successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return invoiceId;
    }

    public void deleteFacture(Facture C) {
        String requete = "DELETE FROM Facture WHERE id=?";
        //
        try {
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            statement.setInt(1, C.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            // Logger.getLogger(FactureService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //
    public List<Facture> listerFacture() {
        List<Facture> mylist = new ArrayList();
        ClientCRUD C = new ClientCRUD();
        try {

            String requete = "SELECT * FROM Facture";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ProduitBLCRUD PBL = new ProduitBLCRUD();
                Facture P = new Facture();

                P.setId(rs.getInt(1));
                P.setDate(rs.getDate("date"));
                P.setNet(rs.getDouble("net"));
                P.setClient(C.getClientById(rs.getInt("id_client")));
                P.setProduits(PBL.getProduitsbyFacture(rs.getInt(1)));

                mylist.add(P);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mylist;
    }
}

// public void deleteFacture(Facture C) {
// String requete = "DELETE FROM Facture WHERE id=?";
//
// try {
// PreparedStatement statement =
// MyConnection.getInstance().getCnx().prepareStatement(requete);
// statement.setInt(1,C.getId());
// statement.executeUpdate();
// } catch (SQLException ex) {
// //Logger.getLogger(FactureService.class.getName()).log(Level.SEVERE, null,
// ex);
// }
// }
//
//
//
// public void modifierFacture(Facture c){
// try {
// String requete = "UPDATE Facture SET Name = ?, MF = ? , Adresse =? WHERE
// id=?";
//
// PreparedStatement pst=
// MyConnection.getInstance().getCnx().prepareStatement(requete);
// pst.setInt(4, c.getId());
// pst.setString(1, c.getName());
// pst.setString(2, c.getMF());
// pst.setString(3, c.getAdresse());
//
// pst.executeUpdate();
// System.out.println("Facture modifiée!");
// } catch (SQLException ex) {
// System.out.println(ex.getMessage());
// }
// }
//
// public Facture getFactureById(int clientId) {
// Facture client = null;
// try {
// String query = "SELECT * FROM Facture WHERE id = ?";
// PreparedStatement statement =
// MyConnection.getInstance().getCnx().prepareStatement(query);
// statement.setInt(1, clientId);
// ResultSet resultSet = statement.executeQuery();
// if (resultSet.next()) {
// client = new Facture(resultSet.getInt("id"), resultSet.getString("Name"),
// resultSet.getString("MF"), resultSet.getString("Adresse"));
// }
// } catch (SQLException ex) {
// System.out.println(ex.getMessage());
// }
// return client;
// }
