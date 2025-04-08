package Societe.entite;

import Societe.Base.BaseModel;

public class Prevision extends BaseModel{
    String libelle;
    int montant;
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public Prevision(int id, String libelle, int montant) {
        super(id);
        this.libelle = libelle;
        this.montant = montant;
    }


}
