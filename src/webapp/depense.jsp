<%@ page import="java.util.List" %>
<%@ page import="Societe.entite.Prevision" %>
<%@ page import="Societe.DAO.ServletDAO" %>
<% List<Prevision> list = (List<Prevision>) request.getAttribute("list"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>depense</title>
</head>
<body>
    <div class="container">
        <a href="depensefrom"><button>Liste</button></a>
        <form action="depensefrom" method="post">
            <div>
                <label for="">Prevision</label>
                <select name="idprevision" id="">
                    <% for(Prevision pr : list){ %>
                        <option value="<%= pr.getId() %>"><%= pr.getLibelle() %></option>
                    <% } %>
                </select>
            </div>
            <div>
                <label for="">Montant</label>
                <input type="number" name="montant" id="" min="100" required>
            </div>
            <button type="submit">Valider</button>
        </form>
    </div>
</body>
</html>