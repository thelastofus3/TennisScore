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
    <title>Matches</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/matches.css">
</head>
<body>
<div class="menu">
    <main>
        <h2>Finished Matches</h2>
        <form method="get" action="https://google.com" class="name-find">
            <fieldset>
            <input type="submit" value="Home" class="home">
            </fieldset>
        </form>
        <section>
            <form method="get" action="https://google.com" class="name-find">
                <fieldset>
                    <label for="search">Name:</label>
                    <input id="search" name="search" type="text" class="search-field">
                    <input type="submit" value="Search" class="search-clear">
                    <input type="submit" value="Clear" class="search-clear">
                </fieldset>
            </form>
            <div>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>PLAYER ONE</th>
                        <th>PLAYER TWO</th>
                        <th>WINNER</th>
                    </tr>
                    <tr>
                        <th>0</th>
                        <th>Zena</th>
                        <th>Gena</th>
                        <th>Gena</th>
                    </tr>
                    <tr>
                        <th>0</th>
                        <th>Zena</th>
                        <th>Gena</th>
                        <th>Gena</th>
                    </tr>
                    <tr>
                        <th>0</th>
                        <th>Zena</th>
                        <th>Gena</th>
                        <th>Gena</th>
                    </tr>
                    <tr>
                        <th>0</th>
                        <th>Zena</th>
                        <th>Gena</th>
                        <th>Gena</th>
                    </tr>
                    <tr>
                        <th>0</th>
                        <th>Zena</th>
                        <th>Gena</th>
                        <th>Gena</th>
                    </tr>
                </table>
            </div>
        </section>
    </main>
</div>
</body>
</html>
