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
    private int id_Produit;
    private String NomP;
    private double PU;
    private int ID_Invoice;

    public ProduitBL() {
    }

    public ProduitBL(int id, int Qte, int Id_BL, int id_Produit, String NomP, double PU) {
        this.id = id;
        this.Qte = Qte;
        this.Id_BL = Id_BL;
        this.id_Produit = id_Produit;
        this.NomP = NomP;
        this.PU = PU;
    }

    public ProduitBL(int Qte, int id_Produit, String NomP, double PU) {
        this.Qte = Qte;
        
        this.id_Produit = id_Produit;
        this.NomP = NomP;
        this.PU = PU;
    }
    
    
    public int getInvoice_Id()
    {
        return ID_Invoice;
    }

    public void setInvoice_Id(int ID_Invoice)
    {
        this.ID_Invoice = ID_Invoice;
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

    public int getId_Produit() {
        return id_Produit;
    }

    public void setId_Produit(int id_Produit) {
        this.id_Produit = id_Produit;
    }

    public String getNomP() {
        return NomP;
    }

    public void setNomP(String NomP) {
        this.NomP = NomP;
    }

    public double getPU() {
        return PU;
    }

    public void setPU(double PU) {
        this.PU = PU;
    }

    @Override
    public String toString() {
        return "ProduitBL{" + "id=" + id + ", Qte=" + Qte + ", Id_BL=" + Id_BL + ", id_Produit=" + id_Produit + ", NomP=" + NomP + ", PU=" + PU + '}';
    }

  

   

   
    
}
