package model.dent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;
import bdd.BddObject;
import database.ConnexionBdd;
import model.soin.Traitement;

@Table(tableName="dent", idTable = "numero_dent", prefixe = "DEBT", sequence = "numero_dent", nbNumerique = 6)
public class Dent {
    @PrimaryKey
    @FieldMapping(columnName = "numero_dent")
    private int numeroDent;
    @FieldMapping(columnName = "nom_dent")
    private String nomDent;
    EtatDent etat;

    public Dent(int numeroDent, String nomDent) {
        this.numeroDent = numeroDent;
        this.nomDent = nomDent;
    }

    public Dent(Connection con, int numeroDent, String nomDent, int niveau_etat_dent)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            this.numeroDent = numeroDent;
            this.nomDent = nomDent;
            this.etat=(EtatDent) BddObject.findById(con, EtatDent.class, String.valueOf(niveau_etat_dent), "postgres", "AnaTaf37", "nify");
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }

    public Dent(int numeroDent, String nomDent, EtatDent etat) {
        this.numeroDent = numeroDent;
        this.nomDent = nomDent;
        this.etat = etat;
    }

    public Dent() {}

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

    public String getVraiNom() {
        if(this.getNumeroDent()<=16) {
            return this.getNomDent()+" du haut numero : "+this.getNumeroDent();
        }
        return this.getNomDent()+" du bas numero : "+this.getNumeroDent();
    }

    public EtatDent getEtat() {
        return etat;
    }

    public void setEtat(EtatDent etat) {
        this.etat = etat;
    }

    public ArrayList<Traitement> getTraitementNecessaire(Connection con)
    throws Exception {
        ArrayList<Traitement> result=new ArrayList<Traitement>();
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql=null;
            if(this.getEtat().getNiveauEtatDent()!=0) {
                sql="select * from v_dent_traitement_traitement where ? between id_min and id_max and numero_dent=? and id_traitement!=4";
            } else {
                sql="select * from v_dent_traitement_traitement where id_min=? and numero_dent=?";
            }
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, this.getEtat().getNiveauEtatDent());
            preparedStatement.setInt(2, this.getNumeroDent());
            System.out.println(preparedStatement.toString());
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Traitement traitement=new Traitement(con, resultSet.getString("id_traitement"), resultSet.getString("nom_traitement"), resultSet.getInt("id_objectif"), resultSet.getInt("pas"), resultSet.getInt("id_debut"), resultSet.getInt("id_fin"), resultSet.getDouble("prix"));
                if(1==1) {
                    if(traitement.getPas()>0) {
                        for(int i=this.getEtat().getNiveauEtatDent(); i<traitement.getObjectif().getNiveauEtatDent(); i+=traitement.getPas()) {
                            // if(this.getNumeroDent()==2) {
                                System.out.println(i+"  "+this.getEtat().getNiveauEtatDent());
                            // }
                            result.add(traitement);
                        }
                    } else {
                        for(int i=this.getEtat().getNiveauEtatDent(); i>traitement.getObjectif().getNiveauEtatDent(); i+=traitement.getPas()) {
                            result.add(traitement);
                        }
                    }
                } else {
                    result.add(traitement);
                }
                this.setEtat(traitement.getObjectif());
            }
            // this.setEtat(result.get(result.size()-1).getObjectif());
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
        return result;
    }

    public ArrayList<Traitement> getListeTraitement(Connection con)
    throws Exception {
        ArrayList<Traitement> result=new ArrayList<Traitement>();
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            EtatDent oldEtat=this.getEtat();
            while(this.getEtat().getNiveauEtatDent()!=10) {
                ArrayList<Traitement> listeTraitement=this.getTraitementNecessaire(con);
                for(int i=0; i<listeTraitement.size(); i++) {
                    result.add(listeTraitement.get(i));
                }
            }
            this.setEtat(oldEtat);
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
            result=Traitement.sommePrix(this.getListeTraitement(con));
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
        return result;
    }

    public static ArrayList<Dent> geListeDentInIntervalle(Connection con, String debut, String fin)
    throws Exception {
        ArrayList<Dent> result=new ArrayList<Dent>();
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select*from dent where numero_dent>? and numero_dent<?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.valueOf(debut));
            preparedStatement.setInt(2, Integer.valueOf(fin));
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new Dent(resultSet.getInt("numero_dent"), resultSet.getString("nom_dent")));
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
