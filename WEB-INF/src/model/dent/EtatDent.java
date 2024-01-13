package model.dent;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;

@Table(tableName="etat_dent", idTable = "niveau_etat_dent", prefixe = "ETD", sequence = "niveau_etat_dent", nbNumerique = 6)
public class EtatDent {
    @PrimaryKey
    @FieldMapping(columnName = "niveau_etat_dent")
    int niveauEtatDent;
    @FieldMapping(columnName = "nom_etat_dent")
    String nomEtatDent;
    public EtatDent(int niveauEtatDent, String nomEtatDent) {
        this.niveauEtatDent = niveauEtatDent;
        this.nomEtatDent = nomEtatDent;
    }
    public EtatDent() {
    }
    public int getNiveauEtatDent() {
        return niveauEtatDent;
    }
    public void setNiveauEtatDent(int niveauEtatDent) {
        this.niveauEtatDent = niveauEtatDent;
    }
    public String getNomEtatDent() {
        return nomEtatDent;
    }
    public void setNomEtatDent(String nomEtatDent) {
        this.nomEtatDent = nomEtatDent;
    }

    public int compareToByNiveau(EtatDent etatDent) {
        if(this.getNiveauEtatDent()==etatDent.getNiveauEtatDent()) {
            return 0;
        } else if(this.getNiveauEtatDent()<etatDent.getNiveauEtatDent()) {
            return -1;
        } 
        return 1;
    }
}
