<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26/02/2024
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Match</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/match-score.css">
</head>
<body>
<div class="menu">
    <main>
        <h2>Score</h2>
        <section>
            <table>
                <tr>
                    <th>PLAYER</th>
                    <th>SETS</th>
                    <th>GAMES</th>
                    <th>POINTS</th>
<%--                    <c:if test="${not empty match.matchScore.tieBreakPoints and (match.matchScore.tieBreakPoints.get(0) > 0 || match.matchScore.tieBreakPoints.get(1) > 0)}">--%>
<%--                        <th>TIEBREAK</th>--%>
<%--                    </c:if>--%>
                </tr>
                <tr>
                    <th>${match.playerOne.name}</th>
                    <th>${match.matchScore.sets.get(0)}</th>
                    <th>${match.matchScore.games.get(0)}</th>
                    <th>${match.matchScore.points.get(0).getValue()}</th>
<%--                    <c:if test="${match.matchScore.tieBreakPoints.get(0) > 0 || match.matchScore.tieBreakPoints.get(1) > 0}">--%>
<%--                    <th>${match.matchScore.tieBreakPoints.get(0)}</th>--%>
<%--                    </c:if>--%>
                </tr>
                <tr>
                    <th>${match.playerTwo.name}</th>
                    <th>${match.matchScore.sets.get(1)}</th>
                    <th>${match.matchScore.games.get(1)}</th>
                    <th>${match.matchScore.points.get(1).getValue()}</th>
<%--                    <c:if test="${match.matchScore.tieBreakPoints.get(0) > 0 || match.matchScore.tieBreakPoints.get(1) > 0}">--%>
<%--                        <th>${match.matchScore.tieBreakPoints.get(1)}</th>--%>
<%--                    </c:if>--%>
                </tr>
            </table>
            <form method="post">
                <input type="submit" name="action" value="Player_1">
                <a href="https://github.com/thelastofus3"><img src="assets/thelastofusLogo.png" alt="thelastofusGameLogo"></a>
                <input type="submit" name="action" value="Player_2">
            </form>
        </section>
    </main>
</div>
</body>
</html>
