<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h2>Results</h2>
<div id="game_control">

</div>
<p>Congratulations, <jstl:out value="${sessionScope.username}"/>! You have solved the game.</p>
<div id="game">
    <p id="secretWord">
        The word was:
        <b>
            <jstl:forEach items="${game.maskedWord}" var="val">
                <jstl:out value="${val}"/>
            </jstl:forEach>
        </b>
    </p>
    <p>
        You solved the game in <b><jstl:out value="${game.roundNumber}"/></b> rounds.
    </p>
    <p id="finalScore">
        Your score is: <b><jstl:out value="${game.score}"/></b>
    </p>
    <div class="game_control">
        <button onclick="window.location.href='${pageContext.request.contextPath}/play?create_new_game=true';">
            Start New Game
        </button>
        <button onclick="window.location.href='${pageContext.request.contextPath}/logout';">
            Log Out
        </button>
    </div>
</div>
</body>
</html>
