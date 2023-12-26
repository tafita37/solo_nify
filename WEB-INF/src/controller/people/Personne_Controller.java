package controller.people;

import java.sql.Connection;
import java.sql.Date;

import annotation.DefaultParameter;
import annotation.NotNullParameter;
import annotation.Parameters;
import annotation.Url;
import bdd.BddObject;
import database.ConnexionBdd;
import model.people.Personne;
import url.ModelView;

public class Personne_Controller {
    @Url(link = "newPersonne.htm")
    public Object newPersonne()
    throws Exception {
        ModelView result=new ModelView("web/pages/formPersonne.jsp");
        return result;
    }

    @Url(link = "traitementNewPersonne.htm")
    @Parameters(args = {"nom_personne", "prenom_personne", "email_personne", "mdp_personne", "date_naissance"})
    public Object createNewPersonne(@NotNullParameter String nomPersonne, @NotNullParameter String prenomPersonne, @NotNullParameter String emailPersonne, @NotNullParameter String mdpPersonne, @NotNullParameter Date dateNaissance)
    throws Exception {
        ModelView result=new ModelView();
        result.setUrlRedirect("newPersonne.htm");
        Personne personne=new Personne(nomPersonne, prenomPersonne, emailPersonne, mdpPersonne, dateNaissance);
        BddObject.insert(null, personne, "postgres", "AnaTaf37", "nify");
        return result;
    }
}
