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
    <table border="1">
        <tr>
            <th>
                Dent
            </th>
            <th>
                Cout de traitement
            </th>
            <th>
                Type de traitement
            </th>
        </tr>
        <%
            for(int i=0; i<listeDent.length; i++) {
                if(listeDent[i]!=null) {
                    %>
                    <tr>
                        <td>
                            <%
                                out.println(listeDent[i].getVraiNom());
                            %>
                        </td>
                        <td>
                            <%
                                out.println(listeDent[i].getCoutTraitement());
                            %>
                        </td>
                        <td>
                            <%
                                out.println(listeDent[i].getTypeTraitement());
                            %>
                        </td>
                    </tr>
                    
                <% }
            }
        %>
    </table>
    Budget : <% out.println(consultation.getBudgetPersonne()+" ar"); %>
</body>
</html>