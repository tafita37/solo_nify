package model.soin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import bdd.BddObject;
import database.ConnexionBdd;
import model.dent.Dent;
import model.people.Personne;

public class Consultation {
    private String idConsultation;
    private TypeSoin choix;
    private Personne personne;
    private Timestamp dateConsultation;
    private Dent[] listeDents;
    private double budgetPersonne;
    private int etatConsultation;

    public Consultation(String idConsultation, TypeSoin choix, Personne personne, Timestamp dateConsultation, Dent[] listeDents, double budgetPersonne, int etatConsultation) {
        this.idConsultation = idConsultation;
        this.choix = choix;
        this.personne = personne;
        this.dateConsultation = dateConsultation;
        this.listeDents = listeDents;
        this.budgetPersonne = budgetPersonne;
        this.etatConsultation = etatConsultation;
    }

    public Consultation(Connection con, String idTypeSoin, String idPersonne, Timestamp dateConsultation, Dent[] listeDents, double budgetPersonne)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            this.choix = (TypeSoin) BddObject.findById(con, TypeSoin.class, idTypeSoin, "postgres", "AnaTaf37", "nify");
            this.personne = (Personne) BddObject.findById(con, Personne.class, idPersonne, "postgres", "AnaTaf37", "nify");;
            this.dateConsultation = dateConsultation;
            this.listeDents = listeDents;
            this.budgetPersonne = budgetPersonne;     
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Consultation(Connection con, String idConsultation, String idTypeSoin, String idPersonne, Timestamp dateConsultation, Dent[] listeDents, double budgetPersonne, int etatConsultation)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            this.idConsultation = idConsultation;
            this.choix = (TypeSoin) BddObject.findById(con, TypeSoin.class, idTypeSoin, "postgres", "AnaTaf37", "nify");
            this.personne = (Personne) BddObject.findById(con, Personne.class, idPersonne, "postgres", "AnaTaf37", "nify");;
            this.dateConsultation = dateConsultation;
            this.listeDents = listeDents;
            this.budgetPersonne = budgetPersonne;
            this.etatConsultation = etatConsultation;        
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Consultation(Connection con, String idTypeSoin, String idPersonne, Timestamp dateConsultation, ArrayList<Dent> listeDents, double budgetPersonne)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            this.choix = (TypeSoin) BddObject.findById(con, TypeSoin.class, idTypeSoin, "postgres", "AnaTaf37", "nify");
            this.personne = (Personne) BddObject.findById(con, Personne.class, idPersonne, "postgres", "AnaTaf37", "nify");;
            this.dateConsultation = dateConsultation;
            this.listeDents = new Dent[listeDents.size()];
            for(int i=0; i<listeDents.size(); i++) {
                this.listeDents[i]=listeDents.get(i);
            }
            this.budgetPersonne = budgetPersonne;        
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Consultation() {}

    public String getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(String idConsultation) throws Exception {
        if (idConsultation == null||idConsultation.length()==0) {
            throw new Exception("Id Consultation ne peut pas être null");
        }
        this.idConsultation = idConsultation;
    }

    public TypeSoin getChoix() {
        return choix;
    }

    public void setChoix(TypeSoin choix) throws Exception {
        if (choix == null) {
            throw new Exception("Choix ne peut pas être null");
        }
        this.choix = choix;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) throws Exception {
        if (personne == null) {
            throw new Exception("Personne ne peut pas être null");
        }
        this.personne = personne;
    }

    public Timestamp getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Timestamp dateConsultation) throws Exception {
        if (dateConsultation == null) {
            throw new Exception("Date Consultation ne peut pas être null");
        }
        this.dateConsultation = dateConsultation;
    }

    public double getBudgetPersonne() {
        return budgetPersonne;
    }

    public void setBudgetPersonne(double budgetPersonne) throws Exception {
        if (budgetPersonne < 0) {
            throw new Exception("Budget Personne ne peut pas être négatif");
        }
        this.budgetPersonne = budgetPersonne;
    }

    public int getEtatConsultation() {
        return etatConsultation;
    }

    public void setEtatConsultation(int etatConsultation) throws Exception {
        if (etatConsultation !=0&&etatConsultation !=11) {
            throw new Exception("Consultation est soit termine soit non termine");
        }
        this.etatConsultation = etatConsultation;
    }

    public void newConsultationTable(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        try {
            String sql="insert into consultation(id_type_soin, id_personne, date_consultation, budget_personne, etat_consultation) values(?, ?, ?, ?, 0)";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getChoix().getIdTypeSoin());
            preparedStatement.setString(2, this.getPersonne().getIdPersonne());
            preparedStatement.setTimestamp(3, this.getDateConsultation());
            preparedStatement.setDouble(4, this.getBudgetPersonne());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (Exception e) {
            throw e;
        } finally {
            if(preparedStatement!=null) {
                preparedStatement.close();
            }
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public static int countListeDentATraiterByIdConsultation(Connection con, String idConsultation)
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
            String sql="select count(*) as nb from v_dent_abimer where id_consultation=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(idConsultation));
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

    public static Dent[] getListeDentATraiterByIdConsultation(Connection con, String idConsultation)
    throws Exception {
        Dent[] result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        result=new Dent[Consultation.countListeDentATraiterByIdConsultation(con, idConsultation)];
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from v_dent_abimer where id_consultation=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(idConsultation));
            resultSet=preparedStatement.executeQuery();
            int i=0;
            while(resultSet.next()) {
                result[i]=new Dent(con, resultSet.getInt("numero_dent"), resultSet.getString("nom_dent"), resultSet.getInt("niveau_etat_dent"));
                System.out.println("niveau : "+resultSet.getInt("niveau_etat_dent"));
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

    public static Consultation getLastConsultationById(Connection con, String idConsultation)
    throws Exception {
        Consultation result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from consultation where id_consultation=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(idConsultation));
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=new Consultation(con, resultSet.getString("id_consultation"), resultSet.getString("id_type_soin"), resultSet.getString("id_personne"), resultSet.getTimestamp("date_consultation"), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation")), resultSet.getDouble("budget_personne"), resultSet.getInt("etat_consultation"));
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

    public static Consultation getLastConsultation(Connection con)
    throws Exception {
        Consultation result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from v_last_consultation";
            preparedStatement=con.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=new Consultation(con, resultSet.getString("id_consultation"), resultSet.getString("id_type_soin"), resultSet.getString("id_personne"), resultSet.getTimestamp("date_consultation"), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation")), resultSet.getDouble("budget_personne"), resultSet.getInt("etat_consultation"));
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

    public void newEtatDent(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        try {
            Consultation consultation=Consultation.getLastConsultation(con);
            String sql="insert into consultation_traitement(id_consultation, numero_dent, niveau_etat_dent) values(?, ?, ?)";
            preparedStatement=con.prepareStatement(sql);
            for(int i=0; i<this.getListeDents().length; i++) {
                preparedStatement.setInt(1, Integer.valueOf(consultation.getIdConsultation()));
                preparedStatement.setInt(2, listeDents[i].getNumeroDent());
                preparedStatement.setInt(3, listeDents[i].getEtat().getNiveauEtatDent());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            throw e;
        } finally {
            if(preparedStatement!=null) {
                preparedStatement.close();
            }
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public void newConsultation(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            this.newConsultationTable(con);
            this.newEtatDent(con);
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public void terminerConsultation(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        try {
            String sql="update consultation set etat_consultation=11";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (Exception e) {
            throw e;
        } finally {
            if(preparedStatement!=null) {
                preparedStatement.close();
            }
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Dent[] getListeDents() {
        return listeDents;
    }

    public void setListeDents(Dent[] listeDents) {
        this.listeDents = listeDents;
    }

    public Dent[] ordonnerDentTraiter(Connection con)
    throws Exception {
        Dent[] result=new Dent[this.getListeDents().length];
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        int k=0;
        try {
            Dent[] priorites=this.getChoix().getPriorite(con);
            for(int i=0; i<priorites.length; i++) {
                for(int j=0; j<this.getListeDents().length; j++) {
                    if(priorites[i].getNumeroDent()==this.getListeDents()[j].getNumeroDent()) {
                        result[k]=this.getListeDents()[j];
                        k++;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public Dent[] getListeTraitable(Connection con)
    throws Exception {
        Dent[] result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            result=this.ordonnerDentTraiter(con);
            double budget=this.getBudgetPersonne();
            for(int i=0; i<result.length; i++) {
                if(result[i].getCoutTotalTraitement(con)>budget) {
                    result[i]=null;
                } else {
                    budget-=result[i].getCoutTotalTraitement(con);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public Dent getCorrespondingDent(Connection con, int numeroDent)
    throws Exception {
        Dent result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            Dent[] listeProposition=this.getListeTraitable(con);
            for(int i=0; i<listeProposition.length; i++) {
                if(listeProposition[i]!=null&&listeProposition[i].getNumeroDent()==numeroDent) {
                    result=listeProposition[i];
                    result.setEtat(listeProposition[i].getEtat());
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }
}

