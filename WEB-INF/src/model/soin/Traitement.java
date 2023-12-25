package model.soin;

import java.sql.Connection;
import java.util.Date;

import model.dent.Dent;

public class Traitement {
    private String idTraitement;
    private Consultation consultation;
    private Dent dent;
    private double coutTraitement;
    private Date dateTraitement;

    public Traitement(){
    }

    public Traitement(String idTraitement, Consultation consultation, Dent dent, double coutTraitement, Date dateTraitement) throws Exception {
        setIdTraitement(idTraitement);
        setConsultation(consultation);
        setDent(dent);
        setCoutTraitement(coutTraitement);
        setDateTraitement(dateTraitement);
    }

    public String getIdTraitement() {
        return idTraitement;
    }

    public void setIdTraitement(String idTraitement)
    throws Exception {
        if(idTraitement==null||idTraitement.length()==0) {
            throw new Exception("Veuillez entrer un id de traitement");
        }
        this.idTraitement = idTraitement;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation)
    throws Exception {
        if(consultation==null) {
            throw new Exception("Veuillez entrer une consultation");
        }
        this.consultation = consultation;
    }

    public Dent getDent() {
        return dent;
    }

    public void setDent(Dent dent)
    throws Exception {
        if(dent==null) {
            throw new Exception("Veuillez entrer une dent");
        }
        this.dent = dent;
    }

    public double getCoutTraitement() {
        return coutTraitement;
    }

    public void setCoutTraitement(double coutTraitement)
    throws Exception {
        if(coutTraitement<=0) {
            throw new Exception("Veuillez entrer un cout de traitement plus grand");
        }
        this.coutTraitement = coutTraitement;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement)
    throws Exception {
        if(dateTraitement==null) {
            throw new Exception("Veuillez entrer une date de traitement");
        }
        this.dateTraitement = dateTraitement;
    }

    public Traitement getTraitementByConsultationAndDent(Connection con, String idConsultation, String numeroDent) {
        // Implementation for getTraitementByConsultationAndDent method
        return null;
    }

    public void newTraitement(Connection con) {
        // Implementation for newTraitement method
    }

    public void newTraitements(Connection con) {
        // Implementation for newTraitements method
    }
}

