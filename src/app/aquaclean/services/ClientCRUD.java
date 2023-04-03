/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.services;

import app.aquaclean.entities.Client;
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
public class ClientCRUD {
     public void ajouterClient(Client c){
        try {
            String requete="INSERT INTO Client(Name,MF,Adresse)Values(?,?,?)";
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, c.getName());
        pst.setString(2, c.getMF());
        pst.setString(3, c.getAdresse());
       
        
        pst.executeUpdate();
            System.out.println("elemenT AJOUTEEEEE");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        
        }          
    }
    
    public List<Client>listerClient(){
     List<Client>mylist=new ArrayList();
        try {
            
            String requete="SELECT *FROM Client";
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
            
            Client P=new Client();
            
            P.setId(rs.getInt(1));
            P.setName(rs.getString("Name"));
            P.setMF(rs.getString("MF"));
            P.setAdresse(rs.getString("Adresse"));
            
            
            
            mylist.add(P);
            
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   return mylist;}
     public void deleteClient(Client C) {
      String requete = "DELETE FROM Client WHERE id=?";
 
        try {
            PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            statement.setInt(1,C.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
           
 
    public void modifierClient(Client c){
        try {
            String requete =  "UPDATE Client SET Name = ?, MF = ? , Adresse =? WHERE id=?";
           
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(4, c.getId());
             pst.setString(1, c.getName());
        pst.setString(2, c.getMF());
        pst.setString(3, c.getAdresse());
           
            pst.executeUpdate();
            System.out.println("Client modifiée!");                        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }
    }

    public Client getClientById(int clientId) {
    Client client = null;
    try {
        String query = "SELECT * FROM Client WHERE id = ?";
        PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query);
        statement.setInt(1, clientId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            client = new Client(resultSet.getInt("id"), resultSet.getString("Name"), resultSet.getString("MF"), resultSet.getString("Adresse"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return client;
}
    
}
