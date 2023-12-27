package model.soin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import bdd.BddObject;
import database.ConnexionBdd;
import model.dent.Dent;
import model.people.Personne;

public class Consultation {
    private String idConsultation;
    private TypeSoin choix;
    private Personne personne;
    private Timestamp dateConsultation;
    private Dent[] dentAReparer;
    private Dent[] dentARemplacer;
    private double budgetPersonne;
    private int etatConsultation;

    public Consultation(TypeSoin choix, Personne personne, Timestamp dateConsultation, Dent[] dentAReparer, Dent[] dentARemplacer, double budgetPersonne)
    throws Exception {
        setChoix(choix);
        setPersonne(personne);
        setDateConsultation(dateConsultation);
        setDentAReparer(dentAReparer);
        setDentARemplacer(dentARemplacer);
        setBudgetPersonne(budgetPersonne);
    }

    public Consultation() {}

    public Consultation(String idConsultation, TypeSoin choix, Personne personne, Timestamp dateConsultation, Dent[] dentAReparer, Dent[] dentARemplacer, double budgetPersonne, int etatConsultation) throws Exception {
        setIdConsultation(idConsultation);
        setChoix(choix);
        setPersonne(personne);
        setDateConsultation(dateConsultation);
        setDentAReparer(dentAReparer);
        setDentARemplacer(dentARemplacer);
        setBudgetPersonne(budgetPersonne);
        setEtatConsultation(etatConsultation);
    }

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

    public Dent[] getDentAReparer() {
        return dentAReparer;
    }

    public void setDentAReparer(Dent[] dentAReparer) throws Exception {
        this.dentAReparer = dentAReparer;
    }

    public Dent[] getDentARemplacer() {
        return dentARemplacer;
    }

    public void setDentARemplacer(Dent[] dentARemplacer) throws Exception {
        if(dentARemplacer!=null) {
            for(int i=0; i<dentARemplacer.length; i++) {
                if(this.getDentAReparer()==null) {
                    break;
                }
                for(int j=0; j<this.getDentAReparer().length; j++) {
                    if(dentARemplacer[i].getNumeroDent()==this.getDentAReparer()[j].getNumeroDent()) {
                        throw new Exception("Vous ne pouvez pas reparer et remplacer "+this.getDentAReparer()[j].getNomDent());
                    }
                }
            }
        } else {
            if(this.getDentAReparer()==null) {
                throw new Exception("Vous devez reparer ou remplacer au moins une dent");
            }
        }
        this.dentARemplacer = dentARemplacer;
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
                result=new Consultation(resultSet.getString("id_consultation"), (TypeSoin) BddObject.findById(con, TypeSoin.class, resultSet.getString("id_type_soin"), "postgres", "AnaTaf37", "nify"), (Personne) BddObject.findById(con, Personne.class, resultSet.getString("id_personne"), "postgres", "AnaTaf37", "nify"), resultSet.getTimestamp("date_consultation"), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation"), 11), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation"), 21), resultSet.getDouble("budget_personne"), resultSet.getInt("etat_consultation"));
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

    public static void newReparationAndRemplacementConsultation(Connection con, Dent[] listeDents, int reparerOuRemplacer)
    throws Exception {
        if(listeDents!=null) {
            boolean jAiOuvert=false;
            if(con==null) {
                jAiOuvert=true;
                con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
            }
            PreparedStatement preparedStatement=null;
            try {
                Consultation lastConsultation=Consultation.getLastConsultation(con);
                if(lastConsultation==null) {
                    throw new Exception("Veuillez d'abord faire une consultation");
                }
                String tableName="";
                if(reparerOuRemplacer==11) {
                    tableName="reparation";
                } else {
                    tableName="remplacement";
                }
                String sql="insert into "+tableName+"(id_consultation, numero_dent) values(?, ?)";
                preparedStatement=con.prepareStatement(sql);
                for(int i=0; i<listeDents.length; i++) {
                    preparedStatement.setInt(1, Integer.valueOf(lastConsultation.getIdConsultation()));
                    preparedStatement.setInt(2, listeDents[i].getNumeroDent());
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
            Consultation.newReparationAndRemplacementConsultation(con, this.getDentAReparer(), 11);
            Consultation.newReparationAndRemplacementConsultation(con, this.getDentARemplacer(), 21);
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Dent[] getOrdreDeTraitement(Connection con)
    throws Exception {
        Dent[] result=new Dent[this.getDentARemplacer().length+this.getDentAReparer().length];
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            Dent[] priorites=this.getChoix().getPriorite(con);
            int k=0;
            for(int i=0; i<priorites.length; i++) {
                if(this.getDentAReparer()!=null) {
                    for(int j=0; j<this.getDentAReparer().length; j++) {
                        if(this.getDentAReparer()[j].getNumeroDent()==priorites[i].getNumeroDent()) {
                            result[k]=this.getDentAReparer()[j];
                            k++;
                        }
                    }
                }
                if(this.getDentARemplacer()!=null) {
                    for(int j=0; j<this.getDentARemplacer().length; j++) {
                        if(this.getDentARemplacer()[j].getNumeroDent()==priorites[i].getNumeroDent()) {
                            result[k]=this.getDentARemplacer()[j];
                            k++;
                        }
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

    public Dent[] getDentTraitable(Connection con)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        Dent[] result=null;
        try {
            result=this.getOrdreDeTraitement(con);
            double budget=this.getBudgetPersonne();
            for(int i=0; i<result.length; i++) {
                if(result[i].getCoutTraitement()<budget) {
                    budget-=result[i].getCoutTraitement();
                } else {
                    result[i]=null;
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

    public double getCoutTotalTraitement(Connection con)
    throws Exception {
        double result=0;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            Dent[] traitable=this.getDentTraitable(con);
            result=Dent.getCoutTotalTabDent(traitable);
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public double getResteBudget(Connection con)
    throws Exception {
        double result=0;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            double coutTotal=this.getCoutTotalTraitement(con);
            result=this.getBudgetPersonne()-coutTotal;
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public static Consultation getConsultationById(Connection con, String valueId)
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
            preparedStatement.setInt(1, Integer.valueOf(valueId));
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result=new Consultation(resultSet.getString("id_consultation"), (TypeSoin) BddObject.findById(con, TypeSoin.class, resultSet.getString("id_type_soin"), "postgres", "AnaTaf37", "nify"), (Personne) BddObject.findById(con, Personne.class, resultSet.getString("id_personne"), "postgres", "AnaTaf37", "nify"), resultSet.getTimestamp("date_consultation"), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation"), 11), Consultation.getListeDentATraiterByIdConsultation(con, resultSet.getString("id_consultation"), 21), resultSet.getDouble("budget_personne"), resultSet.getInt("etat_consultation"));
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

    public Traitement[] getAllTraitementOfConsultation(Connection con)
    throws Exception {
        return null;
    }

    public static int countListeDentATraiterByIdConsultation(Connection con, String idConsultation, int reparerOuRemplacer)
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
            String tableName=null;
            if(reparerOuRemplacer==11) {
                tableName="v_dent_reparation";
            } else {
                tableName="v_dent_remplacement";
            }
            String sql="select count(*) as nb from "+tableName+" where id_consultation=?";
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

    public static Dent[] getListeDentATraiterByIdConsultation(Connection con, String idConsultation, int reparerOuRemplacer)
    throws Exception {
        Dent[] result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        result=new Dent[Consultation.countListeDentATraiterByIdConsultation(con, idConsultation, reparerOuRemplacer)];
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String tableName=null;
            if(reparerOuRemplacer==11) {
                tableName="v_dent_reparation";
            } else {
                tableName="v_dent_remplacement";
            }
            String sql="select*from "+tableName+" where id_consultation=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(idConsultation));
            resultSet=preparedStatement.executeQuery();
            int i=0;
            while(resultSet.next()) {
                result[i]=new Dent(resultSet.getInt("numero_dent"), resultSet.getString("nom_dent"), resultSet.getDouble("cout_reparation"), resultSet.getDouble("cout_remplacement"), reparerOuRemplacer);
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

    public boolean estATraiter(Dent dent) {
        if(this.getDentAReparer()!=null) {
            for(int i=0; i<this.getDentAReparer().length; i++) {
                if(dent.getNumeroDent()==this.getDentAReparer()[i].getNumeroDent()) {
                    return true;
                }
            }
        }
        if(this.getDentARemplacer()!=null) {
            for(int i=0; i<this.getDentARemplacer().length; i++) {
                if(dent.getNumeroDent()==this.getDentARemplacer()[i].getNumeroDent()) {
                    return true;
                }
            }
        }
        return false;
    }
}

