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
public class Produits {
    
    private int id ;
    private String Name;
    private double prix;

    public Produits(int id, String Name, double prix) {
        this.id = id;
        this.Name = Name;
        this.prix = prix;
    }

    public Produits() {
    }

    public Produits(String Name, double prix) {
        this.Name = Name;
        this.prix = prix;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return Name;
    }
    
    
}
