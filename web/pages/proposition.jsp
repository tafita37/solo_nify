<%@page import="model.dent.*"%>
<%@page import="model.soin.*"%>
<%
    Dent[] listeDent=(Dent[]) request.getAttribute("dentTraitable");
    Consultation consultation=(Consultation) request.getAttribute("consultation");
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
    Choix : <% out.println(consultation.getChoix().getNomTypeSoin()); %>
    <br>
    Budget : <% out.println(consultation.getBudgetPersonne()); %>
    <table border="1">
        <tr>
            <th>
                Nom de la dent
            </th>
            <th>
                Etat
            </th>
            <th>
                Details
            </th>
        </tr>
        <%
            for(int i=0; i<listeDent.length; i++) {
                if(listeDent[i]!=null) {
                    %>
                    <tr>
                        <td>
                            <% out.println(listeDent[i].getVraiNom()); %>
                        </td>
                        <td>
                            <% out.println(listeDent[i].getEtat().getNomEtatDent()); %>
                        </td>
                        <td>
                            <a href=<% out.println("detailProposition.htm?id_consultation="+consultation.getIdConsultation()+"&&numero_dent="+listeDent[i].getNumeroDent()); %>>
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