<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 26/02/2024
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <section>
            <form method="get" action="/matches" class="name-find">
                <fieldset>
                    <label for="search">Name:</label>
                    <input id="search" name="filter_by_player_name" type="text" class="search-field" placeholder="Enter player name">
                    <input type="submit" value="Search" class="search-clear">
                    <input type="submit" value="Clear" class="search-clear">
                </fieldset>
            </form>
            <form method="get" action="/index" class="name-find">
                <input type="submit" value="Home" class="home-button">
            </form>
            <div>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>PLAYER ONE</th>
                        <th>PLAYER TWO</th>
                        <th>WINNER</th>
                    </tr>
                    <c:forEach var="match" items="${matches}">
                        <tr>
                            <th>${match.id}</th>
                            <th>${match.playerOne.name}</th>
                            <th>${match.playerTwo.name}</th>
                            <th>${match.winner.name}</th>
                        </tr>
                    </c:forEach>
                    <c:set var="emptyRows" value="${5 - fn:length(matches)}" />
                    <c:forEach begin="1" end="${emptyRows}">
                        <tr>
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                        </tr>
                    </c:forEach>
                </table>
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${page != 1}">
                            <li class="page-item"><a class="page-link" href="matches?page=${page - 1}&filter_by_player_name=${filter_by_player_name}">Previous</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${noOfPage}" var="i">
                            <c:choose>
                                <c:when test="${page eq i}">
                                    <li class="page-item"><a class="page-link" href="matches?page=${i}&filter_by_player_name=${filter_by_player_name}">${i}</a></li>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${page lt noOfPage}">
                            <li class="page-item"><a class="page-link" href="matches?page=${page+1}&filter_by_player_name=${filter_by_player_name}">Next</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </section>
    </main>
</div>
</body>
</html>
