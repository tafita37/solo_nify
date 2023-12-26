package model.soin;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bdd.BddObject;
import database.ConnexionBdd;
import model.dent.Dent;

public class Traitement {
    private String idTraitement;
    private Consultation consultation;
    private Dent dent;
    private double coutTraitement;
    private Timestamp dateTraitement;

    public Traitement(){
    }

    public Traitement(String idTraitement, Consultation consultation, Dent dent, double coutTraitement, Timestamp dateTraitement) throws Exception {
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
        if(!this.getConsultation().estATraiter(dent)) {
            throw new Exception("Cette dent ne fait pas parti de la liste de traitement");
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

    public Timestamp getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Timestamp dateTraitement)
    throws Exception {
        if(dateTraitement==null) {
            throw new Exception("Veuillez entrer une date de traitement");
        }
        this.dateTraitement = dateTraitement;
    }

    public static Traitement getTraitementByConsultationAndDent(Connection con, String idConsultation, int numeroDent)
    throws Exception {
        Traitement result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from traitement where id_consultation=? and numero_dent=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(idConsultation));
            preparedStatement.setInt(2, numeroDent);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=new Traitement(resultSet.getString("id_traitement"), Consultation.getConsultationById(con, idConsultation), (Dent) BddObject.findById(con, Dent.class, String.valueOf(numeroDent), "postgres", "AnaTaf37", "nify"), resultSet.getDouble("cout_traitement"), resultSet.getTimestamp("date_traitement"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(preparedStatement!=null) {
                preparedStatement.close();
            }
            if(resultSet!=null) {
                resultSet.close();
            }
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public void newTraitement(Connection con) {
        // Implementation for newTraitement method
    }

    public void newTraitements(Connection con) {
        // Implementation for newTraitements method
    }
}

