
package app.aquaclean.services;

import app.aquaclean.entities.BL;
import app.aquaclean.entities.Client;
import app.aquaclean.entities.ProduitBL;
import app.aquaclean.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class BLCRUD {
    
         public void ajouterBL(BL c) {
    ResultSet rs = null;
    ProduitBLCRUD PBL = new ProduitBLCRUD();
    try {
        String requete = "INSERT INTO BL(Id_Client,Totale,Date_BL)Values(?,?,?)";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, c.getClient().getId());
        pst.setDouble(2, c.getTotale());
        pst.setDate(3, c.getDate_bl());

        pst.executeUpdate();
        rs = pst.getGeneratedKeys();

        if (rs.next()) {
            int blId = rs.getInt(1);
            PBL.ajouterProduitBL(c.getProduits(), blId);
        }
        System.out.println("élément ajouté avec succès");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }          
}

    
    
          
     public List<BL> listerBL() {
    List<BL> mylist = new ArrayList<>();
    try {
        String requete = "SELECT * FROM BL";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            BL bl = new BL();
            bl.setId(rs.getInt("id"));
            bl.setTotale(rs.getDouble("Totale"));
            bl.setDate_bl(rs.getDate("Date_BL"));

            // Set the client for the BL object
            int clientId = rs.getInt("Id_Client");
            Client client = new ClientCRUD().getClientById(clientId);
            bl.setClient(client);

            // Set the list of products for the BL object
            List<ProduitBL> produits = new ProduitBLCRUD().listerProduitBL(bl);
            bl.setProduits(produits);

            mylist.add(bl);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return mylist;
}

     public void deleteBL(BL C) {
      String requete = "DELETE FROM BL WHERE id= ?";
 
        try {
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            statement.setInt(1,C.getId());
            statement.executeUpdate();
            System.out.println("BL supprimée!");
        } catch (SQLException ex) {
            //Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
           
 
    public void modifierBL(BL c){
        try {
            String requete =  "UPDATE BL SET Id_Client = ?, Totale = ? , Date_BL=? WHERE id=?";
           
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(4, c.getId());
            pst.setInt(1, c.getClient().getId());
            pst.setDouble(2, c.getTotale());
            pst.setDate(3, c.getDate_bl());
           
            pst.executeUpdate();
            System.out.println("BL modifiée!");                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }
    }
}
