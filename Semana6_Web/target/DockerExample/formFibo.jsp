<%--
  Created by IntelliJ IDEA.
  User: Adri
  Date: 19/10/2016
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href = "estilo.css" rel = "stylesheet">
<head>
    <title>Formulario Fibonacci</title>
</head>
<body>
<h1> Fibonacci </h1>
<a>
<form action="/MyServlet" method="get">
    Elige el número de elementos de la sucesión:<br>
    <input type="number" name="num"><br>
    <input type="submit" value="Aceptar">
</form>
</a>
</body>
</html>
