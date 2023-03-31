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
public class ProduitBL {
    private int id,Qte,Id_BL;
    private Produits Produit;

    public ProduitBL(int id, int Qte, int Id_BL, Produits Produit) {
        this.id = id;
        this.Qte = Qte;
        this.Id_BL = Id_BL;
        this.Produit = Produit;
    }

    public ProduitBL(int Qte, int Id_BL, Produits Produit) {
        this.Qte = Qte;
        this.Id_BL = Id_BL;
        this.Produit = Produit;
    }

    public ProduitBL(int Qte, Produits Produit) {
        this.Qte = Qte;
        this.Produit = Produit;
    }

   

    @Override
    public String toString() {
        return "ProduitBL{" + "id=" + id + ", Qte=" + Qte + ", Id_BL=" + Id_BL + ", Produit=" + Produit + '}';
    }

    public ProduitBL() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public int getId_BL() {
        return Id_BL;
    }

    public void setId_BL(int Id_BL) {
        this.Id_BL = Id_BL;
    }

    public Produits getProduit() {
        return Produit;
    }

    public void setProduit(Produits Produit) {
        this.Produit = Produit;
    }

   

   
    
}
