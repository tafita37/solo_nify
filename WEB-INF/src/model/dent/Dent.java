package model.dent;
import java.sql.Connection;

public class Dent {
    private int numeroDent;
    private String nomDent;
    private double coutReparation;
    private double coutRemplacement;
    private int reparationRemplacement;

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

    public Dent findDentByNumeroDent(Connection con, String numeroDent)
    throws Exception {

        return null;
    }
}
