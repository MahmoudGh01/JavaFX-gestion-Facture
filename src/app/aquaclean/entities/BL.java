/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class BL {
    private int id ;
    private Client client;
    public List<ProduitBL> produits;
    private double totale;
    private Date date_bl;

    public BL() {
    }

    @Override
    public String toString() {
        return "BL{" + "id=" + id + ", client=" + client + ", produits=" + produits + ", totale=" + totale + ", date_bl=" + date_bl + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ProduitBL> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitBL> produits) {
        this.produits = produits;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public Date getDate_bl() {
        return date_bl;
    }

    public void setDate_bl(Date date_bl) {
        this.date_bl = date_bl;
    }

    public BL(Client client, List<ProduitBL> produits, double totale, Date date_bl) {
        this.client = client;
        this.produits = produits;
        this.totale = totale;
        this.date_bl = date_bl;
    }

    public BL(int id, Client client, List<ProduitBL> produits, double totale, Date date_bl) {
        this.id = id;
        this.client = client;
        this.produits = produits;
        this.totale = totale;
        this.date_bl = date_bl;
    }

    
    
    
    
}
