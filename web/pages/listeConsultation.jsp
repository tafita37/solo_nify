<%@page import="model.soin.*"%>
<%@page import="model.people.*"%>
<%
    Consultation[] listeConsultation=(Consultation[]) request.getAttribute("listeConsultation");
    Object[] listePersonne=(Object[]) request.getAttribute("allPersonne");
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
    <form action="allConsultation.htm" method="post">
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
        <input type="submit" value="Valider">
    </form>
    <table border="1">
        <tr>
            <th>
                Numero de consultation
            </th>
            <th>
                Date et heure
            </th>
            <th>
                Client
            </th>
            <th>
                Details
            </th>
        </tr>
        <%
            if(listeConsultation!=null) {
                for(int i=0; i<listeConsultation.length; i++) {
                    %>
                    <tr>
                        <td>
                            <%
                                out.println(listeConsultation[i].getIdConsultation());
                            %>
                        </td>
                        <td>
                            <%
                                out.println(listeConsultation[i].getDateConsultation());
                            %>
                        </td>
                        <td>
                            <%
                                out.println(listeConsultation[i].getPersonne().getCompleteName());
                            %>
                        </td>
                        <td>
                            <a href="showProposition.htm?id_consultation=<% out.println(listeConsultation[i].getIdConsultation()); %>">
                                Details
                            </a>
                        </td>
                    </tr>
                <% }
            }
        %>
    </table>
</body>
</html>