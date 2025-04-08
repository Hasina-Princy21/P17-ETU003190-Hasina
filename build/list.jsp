<%@ page import="java.util.List" %>
<%@ page import="Societe.entite.Prevision" %>
<%@ page import="Societe.entite.Depense" %>
<% List<Prevision> listP = (List<Prevision>) request.getAttribute("listP"); 
    List<Depense> listD = (List<Depense>) request.getAttribute("listD"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>list</title>
</head>
<body>
    <div class="container">
        <table border="1">
            <caption>Statistique</caption>
            <thead>
                <tr>
                    <th>Libelle</th>
                    <th>Montant</th>
                    <th>Depense</th>
                    <th>Reste</th>
                </tr>
            </thead>
            <tbody>
                <% for(Depense ld : listD){ 
                    int sum = 0; %>
                    <% for(Prevision lp : listP){ %>
                        <% if(ld.getIdPrevion() == lp.getId()){ %>
                            <% sum += ld.getMontant(); 
                            int reste = lp.getMontant() - sum ;%>
                            <tr>
                                <td><%= lp.getLibelle() %></td>
                                <td><%= lp.getMontant() %></td>
                                <td><%= ld.getMontant() %></td>
                                <td><%= reste %></td>
                            </tr>
                        <% } %>
                    <% } %>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>