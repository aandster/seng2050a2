<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>GTW | Start Game</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
<h1>Guess the Word</h1>
<h2>Start Page</h2>
<p>Complete the form to start playing!</p>
<div id="startGame">
    <form id="startGame_form" action="start" method="POST" onsubmit="return validateStartForm();">

        <%-- Username --%>
        <label for="startGame_form_username">Username</label>
        <input type="text" id="startGame_form_username" name="username" required/>
        <br/>

        <%-- Create or Resume --%>
        <%-- Radio button: resume saved game --%>
        <input type="checkbox" id="startGame_form_mode" name="create_new_game"/>
        <label for="startGame_form_mode">Delete previous saved game (if it exists) and start a new game.</label>
        <br/>

    <%-- &lt;%&ndash; Radio button: create new game&ndash;%&gt;
        <input type="radio" id="startGame_form_mode_button2" name="create_new_game" value="${true}"/>
        <label for="startGame_form_mode_button2">Abandon saved game and start fresh</label>&ndash;%&gt;--%>

        <%-- Submit Button --%>
        <br/>
        <button type="submit">Start Game</button>
        <br/>
    </form>
</div>
</body>
</html>
