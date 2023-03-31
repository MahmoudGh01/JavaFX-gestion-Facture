/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.entities;

/**
 *
 * @author user
 */
public class Client {
    private int id;
    private String Name;
    private String MF;
    private String Adresse;

    public Client() {
    }

    public Client(int id, String Name, String MF, String Adresse) {
        this.id = id;
        this.Name = Name;
        this.MF = MF;
        this.Adresse = Adresse;
    }

    public Client(String Name, String MF, String Adresse) {
        this.Name = Name;
        this.MF = MF;
        this.Adresse = Adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getMF() {
        return MF;
    }

    public void setMF(String MF) {
        this.MF = MF;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    @Override
    public String toString() {
        return Name ;
    }
    
    
}
