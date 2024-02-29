<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26/02/2024
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                </tr>
                <tr>
                    <th>Player1</th>
                    <th>0</th>
                    <th>0</th>
                    <th>0</th>
                </tr>
                <tr>
                    <th>Player2</th>
                    <th>0</th>
                    <th>0</th>
                    <th>0</th>
                </tr>
            </table>
            <form method="post" >
                <input type="submit" value="Player 1">
                <a href="https://github.com/thelastofus3"><img src="assets/thelastofusLogo.png" alt="thelastofus png"></a>
                <input type="submit" value="Player 2">
            </form>
        </section>
    </main>
</div>
</body>
</html>
