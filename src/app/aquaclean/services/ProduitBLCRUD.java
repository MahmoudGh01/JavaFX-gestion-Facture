/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.services;

import app.aquaclean.entities.BL;
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
public class ProduitBLCRUD {
     public void ajouterProduitBL(ProduitBL c){
        try {
            String requete="INSERT INTO ProduitBL(qte,Id_Produit,Id_BL)Values(?,?,?,?)";
            
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
        
            
        pst.setInt(1, c.getQte());
        pst.setString(2, c.getProduit().getName());
        pst.setInt(3, c.getId_BL());
        
       
        
        pst.executeUpdate();
            System.out.println("elemenT AJOUTEEEEE");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        
        }          
    }
    
    public List<ProduitBL>listerProduitBL(BL B){
     List<ProduitBL>mylist=new ArrayList();
        try {
            
            String requete="SELECT *FROM ProduitBL ";
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            
            while(rs.next()){
            if (rs.getInt("Id_BL")== B.getId()){
            ProduitBL P=new ProduitBL();
            
            P.setId(rs.getInt(1));
            P.setId_BL(rs.getInt("Id_BL"));
            
            
            
            
            mylist.add(P);
            
            }
            
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;}
     public void deleteProduitBL(ProduitBL C) {
      String requete = "DELETE FROM ProduitBL WHERE id=?";
 
        try {
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            statement.setInt(1,C.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
           
 
    public void modifierProduitBL(ProduitBL c){
        
    }
    
}
