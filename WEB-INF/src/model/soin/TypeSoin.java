package model.soin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import annotation.FieldMapping;
import annotation.PrimaryKey;
import annotation.Table;
import database.ConnexionBdd;
import model.dent.Dent;

@Table(tableName="type_soin", idTable = "id_type_soin", prefixe = "TPS", sequence = "id_type_soin", nbNumerique = 6)
public class TypeSoin {
    @PrimaryKey
    @FieldMapping(columnName = "id_type_soin")
    private String idTypeSoin;
    @FieldMapping(columnName = "nom_type_soin")
    private String nomTypeSoin;

    public TypeSoin() {}

    public TypeSoin(String idTypeSoin, String nomTypeSoin) throws Exception {
        setIdTypeSoin(idTypeSoin);
        setNomTypeSoin(nomTypeSoin);
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

    public int countPriorite(Connection con)
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
            String sql="select count(*) as nb from v_priorite_asc where id_type_soin=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getIdTypeSoin());
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

    public Dent[] getPriorite(Connection con)
    throws Exception {
        Dent[] result=null;
        boolean jAiOuvert=false;
        if(con==null) {
            jAiOuvert=true;
            con=ConnexionBdd.connexionPostgress("postgres", "AnaTaf37", "nify");
        }
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            result=new Dent[this.countPriorite(con)];
            String sql="select*from v_priorite_asc where id_type_soin=?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1, this.getIdTypeSoin());
            resultSet=preparedStatement.executeQuery();
            int i=0;
            while(resultSet.next()) {
                result[i]=new Dent(resultSet.getInt("numero_dent"), resultSet.getString("nom_dent"), resultSet.getDouble("cout_reparation"), resultSet.getDouble("cout_remplacement"));
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
}

