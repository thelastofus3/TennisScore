<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 11/03/2024
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Winner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/winner.css">
</head>
<body>
<div class="menu">
    <main>
        <h2>Score</h2>
        <section>
            <table>
                <tr>
                    <th>PLAYERS</th>
                    <th>SETS</th>
                </tr>
                <tr>
                    <th>${match.playerOne.name}</th>
                    <th>${match.matchScore.sets.get(0)}<c:if test="${match.matchScore.sets.get(0) == 2}">&#x1F3C6;</c:if></th>
                </tr>
                <tr>
                    <th>${match.playerTwo.name}<c:if test="${match.matchScore.sets.get(1) == 2}">&#x1F3C6;</c:if></th>
                    <th>${match.matchScore.sets.get(1)}</th>
                </tr>
            </table>
            <form method="get" action="/index">
                <input type="submit" value="HOME">
            </form>
        </section>
    </main>
</div>
</body>
</html>
