<%--
  Created by IntelliJ IDEA.
  User: Adri
  Date: 06/11/2016
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculadora</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<a>
    <form action="/MyServlet2" method="post">

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
