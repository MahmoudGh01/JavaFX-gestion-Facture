/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.services;

import app.aquaclean.entities.Produits;
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
public class ProduitsCRUD {
      public void ajouterProduits(Produits c){
        try {
            String requete="INSERT INTO Produit(Name,P_Unitaire)Values(?,?)";
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, c.getName());
        pst.setDouble(2, c.getPrix());
       
        
        pst.executeUpdate();
            System.out.println("elemenT AJOUTEEEEE");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        
        }          
    }
    
    public List<Produits>listerProduits(){
     List<Produits>mylist=new ArrayList();
        try {
            
            String requete="SELECT *FROM Produit";
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
            
            Produits P=new Produits();
            
            P.setId(rs.getInt(1));
            P.setName(rs.getString("Name"));
            P.setPrix(rs.getDouble("P_Unitaire"));
            
            
            
            mylist.add(P);
            
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;}
     public void deleteProduits(Produits C) {
      String requete = "DELETE FROM Produit WHERE id=?";
 
        try {
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            statement.setInt(1,C.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
           
 
    public void modifierProduits(Produits c){
        try {
            String requete =  "UPDATE Produit SET Name = ?, P_Unitaire = ? WHERE id=?";
           
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(3, c.getId());
            pst.setString(1, c.getName());
            pst.setDouble(2, c.getPrix());
           
            pst.executeUpdate();
            System.out.println("Produit modifiée!");                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }
    }
    
}
