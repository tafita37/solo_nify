import java.sql.Connection;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import bdd.BddObject;
import database.ConnexionBdd;
import model.dent.Dent;
import model.dent.EtatDent;
import model.soin.Traitement;

public class App {
    public static void main(String[] args)
    throws Exception {
        Dent dent=(Dent) BddObject.findById(null, Dent.class, "16", "postgres", "AnaTaf37", "nify");
        dent.setEtat((EtatDent) BddObject.findById(null, EtatDent.class, "3", "postgres", "AnaTaf37", "nify"));
        ArrayList<Traitement> listeTraitement=dent.getListeTraitement(null);
        for(int i=0; i<listeTraitement.size(); i++) {
            System.out.println(listeTraitement.get(i).getNomTraitement()+" prix : "+listeTraitement.get(i).getPrix());
        }
    }
}
