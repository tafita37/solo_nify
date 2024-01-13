package model.soin;

import java.sql.Connection;
import java.util.ArrayList;

import bdd.BddObject;
import database.ConnexionBdd;
import model.dent.EtatDent;

public class Traitement {
    String idTraitement;
    String nomTraitement;
    EtatDent objectif;
    int pas;
    EtatDent debut;
    EtatDent fin;
    double prix;
    EtatDent etatActuelle;
    public Traitement(String idTraitement, String nomTraitement, EtatDent objectif, int pas, EtatDent debut,
            EtatDent fin) {
        this.idTraitement = idTraitement;
        this.nomTraitement = nomTraitement;
        this.objectif = objectif;
        this.pas = pas;
        this.debut = debut;
        this.fin = fin;
    }
    public Traitement(String idTraitement, String nomTraitement, EtatDent objectif, int pas, EtatDent debut,
            EtatDent fin, double prix) {
        this.idTraitement = idTraitement;
        this.nomTraitement = nomTraitement;
        this.objectif = objectif;
        this.pas = pas;
        this.debut = debut;
        this.fin = fin;
        this.prix = prix;
    }
    public Traitement(Connection con, String idTraitement, String nomTraitement, int id_objectif, int pas, int id_debut, int id_fin, double prix)
    throws Exception {
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        try {
            this.idTraitement = idTraitement;
            this.nomTraitement = nomTraitement;
            this.objectif = (EtatDent) BddObject.findById(con, EtatDent.class, String.valueOf(id_objectif), "postgres", "AnaTaf37", "nify");
            this.pas = pas;
            this.debut = (EtatDent) BddObject.findById(con, EtatDent.class, String.valueOf(id_debut), "postgres", "AnaTaf37", "nify");;
            this.fin = (EtatDent) BddObject.findById(con, EtatDent.class, String.valueOf(id_fin), "postgres", "AnaTaf37", "nify");;
            this.prix = prix;
        } catch (Exception e) {
            throw e;
        } finally {
            if(jAiOuvert) {
                con.close();
            }
        }
    }
    public Traitement() {
    }
    public String getIdTraitement() {
        return idTraitement;
    }
    public void setIdTraitement(String idTraitement) {
        this.idTraitement = idTraitement;
    }
    public String getNomTraitement() {
        return nomTraitement;
    }
    public void setNomTraitement(String nomTraitement) {
        this.nomTraitement = nomTraitement;
    }
    public EtatDent getObjectif() {
        return objectif;
    }
    public void setObjectif(EtatDent objectif) {
        this.objectif = objectif;
    }
    public int getPas() {
        return pas;
    }
    public void setPas(int pas) {
        this.pas = pas;
    }
    public EtatDent getDebut() {
        return debut;
    }
    public void setDebut(EtatDent debut) {
        this.debut = debut;
    }
    public EtatDent getFin() {
        return fin;
    }
    public void setFin(EtatDent fin) {
        this.fin = fin;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void newTraitement(Connection con) {

    }

    public static double sommePrix(ArrayList<Traitement> listeTraitement) {
        double result=0;
        for(int i=0; i<listeTraitement.size(); i++) {
            result+=listeTraitement.get(i).getPrix();
        }
        return result;
    }

    public EtatDent getMaxEtat() {
        if(this.getPas()>0) {
            if(this.getDebut().compareToByNiveau(this.getFin())>=0) {
                return this.getDebut();
            }
            return this.getFin();
        }
        if(this.getDebut().compareToByNiveau(this.getFin())<=0) {
            return this.getDebut();
        }
        return this.getFin();
    }
    public EtatDent getEtatActuelle() {
        return etatActuelle;
    }
    public void setEtatActuelle(EtatDent etatActuelle) {
        this.etatActuelle = etatActuelle;
    }
}
