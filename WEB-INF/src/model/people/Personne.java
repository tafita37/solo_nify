package model.people;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;
import bdd.BddObject;
import database.ConnexionBdd;
import model.soin.Consultation;
import model.soin.TypeSoin;

@Table(tableName="personne", idTable = "id_personne", prefixe = "PERS", sequence = "id_personne", nbNumerique = 6)
public class Personne {
    @PrimaryKey
    @FieldMapping(columnName = "id_personne")
    private String idPersonne;
    @FieldMapping(columnName = "nom_personne")
    private String nomPersonne;
    @FieldMapping(columnName = "prenom_personne")
    private String prenomPersonne;
    @FieldMapping(columnName = "email_personne")
    private String emailPersonne;
    @FieldMapping(columnName = "mdp_personne")
    private String mdpPersonne;
    @FieldMapping(columnName = "dtn_personne")
    private Date dtnPersonne;

    public Personne(String nomPersonne, String prenomPersonne, String emailPersonne, String mdpPersonne, Date dtnPersonne)
    throws Exception {
        setNomPersonne(nomPersonne);
        setPrenomPersonne(prenomPersonne);
        setEmailPersonne(emailPersonne);
        setMdpPersonne(mdpPersonne);
        setDtnPersonne(dtnPersonne);
    }

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

    public int countAllConsultationOfPersonne(Connection con)
    throws Exception {
        int result=0;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select count(*) as nb from consultation where id_personne=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getIdPersonne());
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=resultSet.getInt("nb");
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

    public Consultation[] getAllConsultationOfPersonne(Connection con)
    throws Exception {
        Consultation[] result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        result=new Consultation[this.countAllConsultationOfPersonne(con)];
        try {
            String sql="select*from consultation where id_personne=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getIdPersonne());
            resultSet=preparedStatement.executeQuery();
            int i=0;
            while(resultSet.next()) {
                result[i]=new Consultation(resultSet.getString("id_consultation"), (TypeSoin) BddObject.findById(con, TypeSoin.class, resultSet.getString("id_type_soin"), "postgres", "AnaTaf37", "nify"), this, resultSet.getTimestamp("date_consultation"), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation"), 11), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation"), 21), resultSet.getDouble("budget_personne"), resultSet.getInt("etat_consultation"));
                i++;
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

    public String getCompleteName() {
        return this.getNomPersonne()+" "+this.getPrenomPersonne();
    }
}
