package Societe.entite;

import Societe.Base.BaseModel;

public class Depense extends BaseModel{
    int idPrevion;
    int montant;
    public int getIdPrevion() {
        return idPrevion;
    }
    public void setIdPrevion(int idPrevion) {
        this.idPrevion = idPrevion;
    }
    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public Depense(int id, int idPrevion, int montant) {
        super(id);
        this.idPrevion = idPrevion;
        this.montant = montant;
    }
}
