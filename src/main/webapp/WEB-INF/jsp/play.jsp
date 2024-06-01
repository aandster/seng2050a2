<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="game" scope="request" type="me.aandster.seng2050a2.model.GameBean"/>

<html>
<head>
    <title>GTW | Play Game</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
<h1>Guess the Word</h1>
<h2>Play</h2>
<p>Welcome back, <jstl:out value="${sessionScope.username}"/>!</p>
<div id="game">
    <div id="roundTitle">
        <h3>Round <jstl:out value="${game.roundNumber}"/></h3>
    </div>
    <br/>
    <div id="maskedWord">
        <jstl:forEach var="ch" items="${game.maskedWord}">
            <span class="maskedWordChar">
                <jstl:out value="${ch}"/>
            </span>
        </jstl:forEach>
    </div>
    <br/>
    <jstl:if test="${game.totalGuesses > 0}">
        <div id="previousGuesses">
            <p>Total Guesses: <jstl:out value="${game.totalGuesses}"/></p>
            <p>Previous guesses:
                <jstl:forEach var="ch" items="${game.recentGuesses}">
            <span class="previousGuessChar">
                <jstl:out value="${ch}"/>
            </span>
                </jstl:forEach>
            </p>
        </div>
    </jstl:if>
    <br/>
    <div class="errorMessage">
        <jstl:out value="${param.errorMessage}"/>
    </div>
    <div id="nextGuessForm">
        <form action="guess" method="POST" id="guessForm" onsubmit="return true;">
            <label for="guessForm_guess"><b>Guess:</b></label>
            <input type="text" name="guess" id="guessForm_guess" maxlength="1" required>
            <input type="submit" value="Submit Guess">
        </form>
        <div class="game_control">
            <button onclick="window.location.href='${pageContext.request.contextPath}/play?create_new_game=true';">
                Start New Game
            </button>
            <button onclick="window.location.href='${pageContext.request.contextPath}/logout';">
                Log Out
            </button>
        </div>
    </div>
</div>
</body>
</html>
