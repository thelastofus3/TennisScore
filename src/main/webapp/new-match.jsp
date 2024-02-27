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
    <title>Tennis score board</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/new-match.css">
</head>
<body>
<div class="menu">
    <main>
        <h2>New Match</h2>
        <section>
            <form method="post" action="https://www.google.com/">
                <label for="first-player">Player 1 name: <input id="first-player" type="text" name="first-player" required></label>
                <label for="second-player">Player 2 name: <input id="second-player" type="text" name="second-player" required></label>
                <input type="submit" value="Submit">
            </form>
        </section>
    </main>
</div>
</body>
</html>
