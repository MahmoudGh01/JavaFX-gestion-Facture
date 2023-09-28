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
     
    public void ajouterProduitBL(List<ProduitBL> PBL ,int B){
        try {
            String requete="INSERT INTO ProduitBL(qte,Id_BL,Id_Produit,PU,Nom)Values(?,?,?,?,?)";
            
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
        
             for (ProduitBL c : PBL) {
        pst.setInt(1, c.getQte());
        pst.setInt(2, B);
        pst.setInt(3, c.getId_Produit());
        pst.setDouble(4, c.getPU());
        pst.setString(5, c.getNomP());
        
       
        
        pst.executeUpdate();
             }
            System.out.println("BLItems AJOUTEEEEE");
        
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
            P.setId_Produit(rs.getInt("Id_Produit"));
            P.setNomP(rs.getString("Nom"));
            P.setPU(rs.getDouble("PU"));
            P.setQte(rs.getInt("qte"));
            
            
            mylist.add(P);
            
            }
            
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;
    }
     
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
     public List<ProduitBL>listerProduitBL(int id){
     List<ProduitBL>mylist=new ArrayList();
        try {
            
            String requete="SELECT *FROM ProduitBL ";
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            
            while(rs.next()){
            if (rs.getInt("Id_Facture")== id){
            ProduitBL P=new ProduitBL();
            
            P.setId(rs.getInt(1));
            P.setId_BL(rs.getInt("Id_BL"));
            P.setId_Produit(rs.getInt("Id_Produit"));
            P.setNomP(rs.getString("Nom"));
            P.setPU(rs.getDouble("PU"));
            P.setQte(rs.getInt("qte"));
            //P.setInvoice_Id(rs.getInt("Id_Facture"));
            
            
            mylist.add(P);
            
            }
            
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;
    }

    public void ajouterProduitBL(int invoiceId, List<ProduitBL> produits) {

        try {
            String requete="INSERT INTO ProduitFacture(qte,Id_BL,Id_Produit,PU,Nom,Id_Facture)Values(?,?,?,?,?,?)";
            
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
        
             for (ProduitBL c : produits) {
        pst.setInt(1, c.getQte());
        pst.setInt(2, c.getId_BL());
        pst.setInt(3, c.getId_Produit());
        pst.setDouble(4, c.getPU());
        pst.setString(5, c.getNomP());
        pst.setInt(6, invoiceId);
       
        
        pst.executeUpdate();
             }
            System.out.println("FactureItems AJOUTEEEEE");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        
        }         
    }
    public List<ProduitBL> getProduitsbyFacture (int Id_Facture)
    { 
        List<ProduitBL>mylist=new ArrayList();
        try {
            
            String requete="SELECT *FROM ProduitFacture WHERE Id_Facture = "+Id_Facture;
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            
            while(rs.next()){
            if (rs.getInt("Id_Facture")== Id_Facture){
            ProduitBL P=new ProduitBL();
            
            P.setId(rs.getInt(1));
            P.setId_BL(rs.getInt("Id_BL"));
            P.setId_Produit(rs.getInt("Id_Produit"));
            P.setNomP(rs.getString("Nom"));
            P.setPU(rs.getDouble("PU"));
            P.setQte(rs.getInt("qte"));
            P.setInvoice_Id(Id_Facture);
            
            
            mylist.add(P);
            
            }
            
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;
        
        

       } 

}
