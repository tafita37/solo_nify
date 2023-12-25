package model.soin;

import model.dent.Dent;

public class TypeSoin {
    private String idTypeSoin;
    private String nomTypeSoin;
    private Dent[] priorite;

    public TypeSoin() {}

    public TypeSoin(String idTypeSoin, String nomTypeSoin, Dent[] priorite) throws Exception {
        setIdTypeSoin(idTypeSoin);
        setNomTypeSoin(nomTypeSoin);
        setPriorite(priorite);
    }

    public String getIdTypeSoin() {
        return idTypeSoin;
    }

    public void setIdTypeSoin(String idTypeSoin) throws Exception {
        if (idTypeSoin == null||idTypeSoin.length()==0) {
            throw new Exception("Id Type Soin ne peut pas être null");
        }
        this.idTypeSoin = idTypeSoin;
    }

    public String getNomTypeSoin() {
        return nomTypeSoin;
    }

    public void setNomTypeSoin(String nomTypeSoin) throws Exception {
        if (nomTypeSoin == null||nomTypeSoin.length()==0) {
            throw new Exception("Nom Type Soin ne peut pas être null");
        }
        this.nomTypeSoin = nomTypeSoin;
    }

    public Dent[] getPriorite() {
        return priorite;
    }

    public void setPriorite(Dent[] priorite) throws Exception {
        if (priorite == null) {
            throw new Exception("Priorité ne peut pas être null");
        }
        this.priorite = priorite;
    }
}

