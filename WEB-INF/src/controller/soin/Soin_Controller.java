package controller.soin;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import annotation.DefaultParameter;
import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import database.ConnexionBdd;
import model.dent.Dent;
import model.dent.EtatDent;
import model.people.Personne;
import model.soin.Consultation;
import model.soin.TypeSoin;
import url.ModelView;

public class Soin_Controller {
    @Url(link = "formSoin.htm")
    public Object formSoin()
    throws Exception {
        ModelView result=new ModelView("web/pages/formSoin.jsp");
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            result.addItem("allTypeSoin", BddObject.selectAllFromBdd(con, TypeSoin.class, "postgres", "AnaTaf37", "nify"));
            result.addItem("allPersonne", BddObject.selectAllFromBdd(con, Personne.class, "postgres", "AnaTaf37", "nify"));
            result.addItem("allDent", BddObject.selectAllFromBdd(con, Dent.class, "postgres", "AnaTaf37", "nify"));
            result.addItem("allEtatDents", BddObject.selectAllFromBdd(con, EtatDent.class, "postgres", "AnaTaf37", "nify"));
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "formSoinAlea.htm")
    public Object formSoinAlea()
    throws Exception {
        ModelView result=new ModelView("web/pages/formSoinAlea.jsp");
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            result.addItem("allTypeSoin", BddObject.selectAllFromBdd(con, TypeSoin.class, "postgres", "AnaTaf37", "nify"));
            result.addItem("allPersonne", BddObject.selectAllFromBdd(con, Personne.class, "postgres", "AnaTaf37", "nify"));
            result.addItem("allDent", BddObject.selectAllFromBdd(con, Dent.class, "postgres", "AnaTaf37", "nify"));
            result.addItem("allEtatDents", BddObject.selectAllFromBdd(con, EtatDent.class, "postgres", "AnaTaf37", "nify"));
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "traitementNewConsultation.htm")
    @Parameters(args = {"id_type_soin", "date_consultation", "heure_consultation", "id_personne", "note", "budget", "numero_dent"})
    public Object createConsultation(String idTypeSoin, String dateConsultation, String heureConsultation, String idPersonne, int[] note, double budget, int[] numeroDent)
    throws Exception {
        ModelView result=new ModelView();
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            if(note.length!=numeroDent.length) {
                throw new Exception("Il n'y a pas le meme nombre de dent que de note");
            }
            Dent[] listeDent=new Dent[note.length];
            for(int i=0; i<note.length; i++) {
                listeDent[i]=(Dent) BddObject.findById(con, Dent.class, String.valueOf(numeroDent[i]), "postgres", "AnaTaf37", "nify");
                listeDent[i].setEtat((EtatDent) BddObject.findById(con, EtatDent.class, String.valueOf(note[i]), "postgres", "AnaTaf37", "nify"));
            }
            Consultation consultation=new Consultation(con, idTypeSoin, idPersonne, Timestamp.valueOf(dateConsultation+" "+heureConsultation+":00"), listeDent, budget);
            consultation.newConsultation(con);
            result.setUrlRedirect("formSoin.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "traitementNewConsultationAlea.htm")
    @Parameters(args = {"id_type_soin", "date_consultation", "heure_consultation", "id_personne", "note", "budget", "numero_dent"})
    public Object createConsultationAlea(String idTypeSoin, String dateConsultation, String heureConsultation, String idPersonne, String note, double budget, String numeroDent)
    throws Exception {
        ModelView result=new ModelView();
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            ArrayList<Dent> listeDent=new ArrayList<Dent>();
            if(numeroDent.contains("-")&&numeroDent.contains(";")) {
                throw new Exception("ca contient les deux separateurs");
            }
            String[] listeNote=note.split(";");
            if(numeroDent.contains("-")) {
                String[] listeIdDent=numeroDent.split("-");
                if(listeIdDent.length!=2) {
                    throw new Exception("Il n'y a pas de bornes");
                }
                listeDent=Dent.geListeDentInIntervalle(con, listeIdDent[0], listeIdDent[1]);
                for(int i=0; i<listeDent.size(); i++) {
                    listeDent.get(i).setEtat((EtatDent) BddObject.findById(con, EtatDent.class, note, "postgres", "AnaTaf37", "nify"));
                }
            } else if(numeroDent.contains(";")) {
                String[] listeIdDent=numeroDent.split(";");
                if(listeIdDent.length!=listeNote.length) {
                    throw new Exception("Il n'y a pas le meme nombre");
                }
                for(int i=0; i<listeIdDent.length; i++) {
                    listeDent.add((Dent) BddObject.findById(con, Dent.class, listeIdDent[i], "postgres", "AnaTaf37", "nify"));
                    listeDent.get(i).setEtat((EtatDent) BddObject.findById(con, EtatDent.class, listeNote[i], "postgres", "AnaTaf37", "nify"));
                }
            }
            Consultation consultation=new Consultation(con, idTypeSoin, idPersonne, Timestamp.valueOf(dateConsultation+" "+heureConsultation+":00"), listeDent, budget);
            consultation.newConsultation(con);
            result.setUrlRedirect("formSoinAlea.htm");
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        } finally {
            con.close();
        }
        return result;
    }
    
    @Url(link = "allConsultation.htm")
    @Parameters(args = {"id_personne"})
    public Object showAllConsultationByPersonne(@DefaultParameter(defaultValue = "PERS000001") String idPersonne)
    throws Exception {
        ModelView result=new ModelView("web/pages/listeConsultation.jsp");
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            Personne personne=(Personne) BddObject.findById(con, Personne.class, idPersonne, "postgres", "AnaTaf37", "nify");
            result.addItem("listeConsultation", personne.getAllConsultationOfPersonne(con));
            result.addItem("allPersonne", BddObject.selectAllFromBdd(con, Personne.class, "postgres", "AnaTaf37", "nify"));
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "showProposition.htm")
    @Parameters(args = {"id_consultation"})
    public Object getPropositionByConsultation(@DefaultParameter(defaultValue = "1") String idConsultation)
    throws Exception {
        ModelView result=new ModelView("web/pages/proposition.jsp");
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            Consultation consultation=Consultation.getLastConsultationById(con, idConsultation);
            result.addItem("consultation", consultation);
            result.addItem("dentTraitable", consultation.getListeTraitable(con));
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "detailProposition.htm")
    @Parameters(args = {"id_consultation", "numero_dent"})
    public Object getDetailProposition(String idConsultation, int numeroDent)
    throws Exception {
        ModelView result=new ModelView("web/pages/detailProposition.jsp");
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            Consultation consultation=Consultation.getLastConsultationById(con, idConsultation);
            Dent dent=consultation.getCorrespondingDent(con, numeroDent);
            if(dent!=null) {
                result.addItem("dent", dent);
                result.addItem("traitements", dent.getListeTraitement(con));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        } finally {
            con.close();
        }
        return result;
    }
}
