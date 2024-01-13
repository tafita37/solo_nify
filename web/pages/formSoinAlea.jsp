<%@page import="model.soin.*"%>
<%@page import="model.people.*"%>
<%@page import="model.dent.*"%>
<%
    Object[] listeTypeSoin=(Object[]) request.getAttribute("allTypeSoin");
    Object[] listePersonne=(Object[]) request.getAttribute("allPersonne");
    Object[] listeDent=(Object[]) request.getAttribute("allDent");
    Object[] listeEtatDent=(Object[]) request.getAttribute("allEtatDents");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h2>
        Formulaire de consultation
    </h2>
    <form action="traitementNewConsultationAlea.htm" method="post">
        <select name="id_type_soin" id="">
            <option value="">
                Choisissez votre type de soin
            </option>
            <%
                for(int i=0; i<listeTypeSoin.length; i++) {
                    TypeSoin typeSoin=(TypeSoin) listeTypeSoin[i];
                    %>
                    <option value=<% out.println(typeSoin.getIdTypeSoin()); %>>
                        <% out.println(typeSoin.getNomTypeSoin()); %>
                    </option>
                <% }
            %>
        </select>
        <br>
        <input type="date" name="date_consultation" id="">
        <br>
        <input type="time" name="heure_consultation" id="">
        <br>
        <select name="id_personne" id="">
            <option value="">
                Choisissez une personne
            </option>
            <%
                for(int i=0; i<listePersonne.length; i++) {
                    Personne personne=(Personne) listePersonne[i];
                    %>
                    <option value=<% out.println(personne.getIdPersonne()); %>>
                        <% out.println(personne.getCompleteName()); %>
                    </option>
                <% }
            %>
        </select>
        <br>
        <h2>
            Etat des dents
        </h2>
        <input type="text" name="numero_dent" id="" placeholder="Dents">
        <br>
        <input type="text" name="note" id="" placeholder="Etat">
        <br>
        <input type="number" name="budget" id="" placeholder="Budget">
        <br>
        <input type="submit" value="Consulter">
    </form>
</body>
</html>