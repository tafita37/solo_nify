package model.soin;

import java.sql.Connection;
import java.util.Date;

import model.dent.Dent;
import model.people.Personne;

public class Consultation {
    private String idConsultation;
    private TypeSoin choix;
    private Personne personne;
    private Date dateConsultation;
    private Dent[] dentAReparer;
    private Dent[] dentARemplacer;
    private double budgetPersonne;
    private int etatConsultation;

    public Consultation() {}

    public Consultation(String idConsultation, TypeSoin choix, Personne personne, Date dateConsultation, Dent[] dentAReparer, Dent[] dentARemplacer, double budgetPersonne, int etatConsultation) throws Exception {
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

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) throws Exception {
        if (dateConsultation == null) {
            throw new Exception("Date Consultation ne peut pas être null");
        }
        this.dateConsultation = dateConsultation;
    }

    public Dent[] getDentAReparer() {
        return dentAReparer;
    }

    public void setDentAReparer(Dent[] dentAReparer) throws Exception {
        if (dentAReparer == null) {
            throw new Exception("Dent à réparer ne peut pas être null");
        }
        this.dentAReparer = dentAReparer;
    }

    public Dent[] getDentARemplacer() {
        return dentARemplacer;
    }

    public void setDentARemplacer(Dent[] dentARemplacer) throws Exception {
        if (dentARemplacer == null) {
            throw new Exception("Dent à remplacer ne peut pas être null");
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

    public void newConsultation(Connection con) {
        // Implémentation de la méthode
    }

    public Dent[] getOrdreDeTraitement() {
        // Implémentation de la méthode
        return null;
    }

    public Dent[] getDentTraitable() {
        // Implémentation de la méthode
        return null;
    }

    public double getCoutTotalTabDent(Dent[] listeDents) {
        // Implémentation de la méthode
        return 0.0;
    }

    public double getCoutTotalTraitement() {
        // Implémentation de la méthode
        return 0.0;
    }

    public double getResteBudget() {
        // Implémentation de la méthode
        return 0.0;
    }

    public static Consultation getConsultationById(Connection con, String valueId) {
        // Implémentation de la méthode
        return null;
    }

    public void terminerConsultation(Connection con) {
        // Implémentation de la méthode
    }
}

