package controller.soin;

import java.sql.Connection;
import java.sql.Timestamp;

import annotation.DefaultParameter;
import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import database.ConnexionBdd;
import model.dent.Dent;
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
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            con.close();
        }
        return result;
    }

    @Url(link = "traitementNewConsultation.htm")
    @Parameters(args = {"id_type_soin", "date_consultation", "heure_consultation", "id_personne", "dent_reparer", "dent_remplacer", "budget"})
    public Object createConsultation(String idTypeSoin, String dateConsultation, String heureConsultation, String idPersonne, String[] dentReparer, String[] dentRemplacer, double budget)
    throws Exception {
        ModelView result=new ModelView();
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            Dent[] dentAReparer=new Dent[dentReparer.length];
            for(int i=0; i<dentAReparer.length; i++) {
                dentAReparer[i]=(Dent) BddObject.findById(con, Dent.class, dentReparer[i], "postgres", "AnaTaf37", "nify");
            }
            Dent[] dentARemplacer=new Dent[dentReparer.length];
            for(int i=0; i<dentARemplacer.length; i++) {
                dentARemplacer[i]=(Dent) BddObject.findById(con, Dent.class, dentRemplacer[i], "postgres", "AnaTaf37", "nify");
            }
            Consultation consultation=new Consultation((TypeSoin) BddObject.findById(con, TypeSoin.class, idTypeSoin, "postgres", "AnaTaf37", "nify"), (Personne) BddObject.findById(con, Personne.class, idPersonne, "postgres", "AnaTaf37", "nify"), Timestamp.valueOf(dateConsultation+" "+heureConsultation+":00"), dentAReparer, dentARemplacer, budget);
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
    
    @Url(link = "allConsultation.htm")
    @Parameters(args = {"id_personne"})
    public Object showAllConsultationByPersonne(@DefaultParameter(defaultValue = "PERS000001") String idPersonne)
    throws Exception {
        ModelView result=new ModelView("web/pages/listeConsultation.jsp");
        Connection con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        try {
            Personne personne=(Personne) BddObject.findById(con, Personne.class, idPersonne, "postgres", "AnaTaf37", "nify");
            if(personne!=null) {
                result.addItem("listeConsultation", personne.getAllConsultationOfPersonne(con));
            }
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
            Consultation consultation=Consultation.getConsultationById(con, idConsultation);
            if(consultation!=null) {
                result.addItem("dentTraitable", consultation.getDentTraitable(con));
                result.addItem("consultation", consultation);
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
