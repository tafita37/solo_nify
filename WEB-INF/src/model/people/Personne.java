package model.people;
import java.sql.Connection;
import java.sql.Date;

import model.soin.Consultation;
import model.soin.Traitement;

public class Personne {
    private String idPersonne;
    private String nomPersonne;
    private String prenomPersonne;
    private String emailPersonne;
    private String mdpPersonne;
    private Date dtnPersonne;

    public Personne() {}

    public Personne(String idPersonne, String nomPersonne, String prenomPersonne, String emailPersonne, String mdpPersonne, Date dtnPersonne) throws Exception {
        setIdPersonne(idPersonne);
        setNomPersonne(nomPersonne);
        setPrenomPersonne(prenomPersonne);
        setEmailPersonne(emailPersonne);
        setMdpPersonne(mdpPersonne);
        setDtnPersonne(dtnPersonne);
    }

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) throws Exception {
        if (idPersonne == null||idPersonne.length()==0) {
            throw new Exception("Id Personne ne peut pas être null");
        }
        this.idPersonne = idPersonne;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) throws Exception {
        if (nomPersonne == null||nomPersonne.length()==0) {
            throw new Exception("Nom Personne ne peut pas être null");
        }
        this.nomPersonne = nomPersonne;
    }

    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    public void setPrenomPersonne(String prenomPersonne) 
    throws Exception {
        if (prenomPersonne == null||prenomPersonne.length()==0) {
            throw new Exception("Prénom Personne ne peut pas être null");
        }
        this.prenomPersonne = prenomPersonne;
    }

    public String getEmailPersonne() {
        return emailPersonne;
    }

    public void setEmailPersonne(String emailPersonne) 
    throws Exception {
        if (emailPersonne == null||emailPersonne.length()==0) {
            throw new Exception("Email Personne ne peut pas être null");
        }
        this.emailPersonne = emailPersonne;
    }

    public String getMdpPersonne() {
        return mdpPersonne;
    }

    public void setMdpPersonne(String mdpPersonne) throws Exception {
        if (mdpPersonne == null||mdpPersonne.length()==0) {
            throw new Exception("Mot de passe Personne ne peut pas être null");
        }
        this.mdpPersonne = mdpPersonne;
    }

    public Date getDtnPersonne() {
        return dtnPersonne;
    }

    public void setDtnPersonne(Date dtnPersonne) throws Exception {
        if (dtnPersonne == null) {
            throw new Exception("Date de naissance Personne ne peut pas être null");
        }
        this.dtnPersonne = dtnPersonne;
    }

    public void newPersonne(Connection con) {
        // Implémentation de la méthode
    }

    public Consultation[] getAllConsultation(Connection con) {
        // Implémentation de la méthode
        return null;
    }

    public Traitement[] getAllTraitement(Connection con) {
        // Implémentation de la méthode
        return null;
    }
}
