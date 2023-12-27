package model.dent;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;

@Table(tableName="dent", idTable = "numero_dent", prefixe = "DEBT", sequence = "numero_dent", nbNumerique = 6)
public class Dent {
    @PrimaryKey
    @FieldMapping(columnName = "numero_dent")
    private int numeroDent;
    @FieldMapping(columnName = "nom_dent")
    private String nomDent;
    @FieldMapping(columnName = "cout_reparation")
    private double coutReparation;
    @FieldMapping(columnName = "cout_remplacement")
    private double coutRemplacement;
    private int reparationRemplacement;
    
    

    public Dent(int numeroDent, String nomDent, double coutReparation, double coutRemplacement)
    throws Exception {
        setNumeroDent(numeroDent);
        setNomDent(nomDent);
        setCoutReparation(coutReparation);
        setCoutRemplacement(coutRemplacement);
    }

    public Dent() {}

    public Dent(int numeroDent, String nomDent, double coutReparation, double coutRemplacement, int reparationRemplacement)
            throws Exception {
        setNumeroDent(numeroDent);
        setNomDent(nomDent);
        setCoutReparation(coutReparation);
        setCoutRemplacement(coutRemplacement);
        setReparationRemplacement(reparationRemplacement);
    }

    public int getNumeroDent() {
        return numeroDent;
    }

    public void setNumeroDent(int numeroDent) throws Exception {
        if (numeroDent <= 0) {
            throw new Exception("Numéro de dent doit être supérieur à zéro");
        }
        this.numeroDent = numeroDent;
    }

    public String getNomDent() {
        return nomDent;
    }

    public void setNomDent(String nomDent) throws Exception {
        if (nomDent == null||nomDent.length()==0) {
            throw new Exception("Nom de dent ne peut pas être null");
        }
        this.nomDent = nomDent;
    }

    public double getCoutReparation() {
        return coutReparation;
    }

    public void setCoutReparation(double coutReparation) throws Exception {
        if (coutReparation < 0) {
            throw new Exception("Coût de réparation ne peut pas être négatif");
        }
        this.coutReparation = coutReparation;
    }

    public double getCoutRemplacement() {
        return coutRemplacement;
    }

    public void setCoutRemplacement(double coutRemplacement) throws Exception {
        if (coutRemplacement < 0) {
            throw new Exception("Coût de remplacement ne peut pas être négatif");
        }
        this.coutRemplacement = coutRemplacement;
    }

    public int getReparationRemplacement() {
        return reparationRemplacement;
    }

    public void setReparationRemplacement(int reparationRemplacement) throws Exception {
        if (reparationRemplacement !=11&&reparationRemplacement !=21) {
            throw new Exception("Il doit s'agir soit d'une reparation soit d'un remplacement");
        }
        this.reparationRemplacement = reparationRemplacement;
    }

    public double getCoutTraitement() {
        if(this.getReparationRemplacement()==11) {
            return this.getCoutReparation();
        }
        return this.getCoutRemplacement();
    }

    public String getTypeTraitement() {
        if(this.getReparationRemplacement()==11) {
            return "Reparation";
        }
        return "Remplacement";
    }

    public static double getCoutTotalTabDent(Dent[] listeDents) {
        double result=0;
        for(int i=0; i<listeDents.length; i++) {
            if(listeDents[i]!=null) {
                result+=listeDents[i].getCoutTraitement();
            }
        }
        return result;
    }

    public String getVraiNom() {
        if(this.getNumeroDent()<=16) {
            return this.getNomDent()+" du haut numero : "+this.getNumeroDent();
        }
        return this.getNomDent()+" du bas numero : "+this.getNumeroDent();
    }
}
