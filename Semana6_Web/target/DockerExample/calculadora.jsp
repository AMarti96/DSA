<%--
  Created by IntelliJ IDEA.
  User: Adri
  Date: 19/10/2016
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculadora</title>
    <link href = "estilo.css" rel = "stylesheet">
</head>
<body>

<a>
<form action="/MyServlet" method="post">

<h3> Operador uno: </h3>
<input type="number" name="o1" step="any"><br>

<h3> Operador dos:</h3>
<input type="number" name="o2" step="any"><br>
    <br/>

    <select operacion="operacion" name="operacion">
        <option value="SUMA">Sumar</option>
        <option value="RESTA">Restar</option>
        <option value="MULTIPLICACION">Multiplicar</option>
        <option value="DIVISION">Dividir</option>
    </select>
    <br/>
    <br/>
    <input type="submit" value="Aceptar">

</form>
</a>
</body>
</html>
