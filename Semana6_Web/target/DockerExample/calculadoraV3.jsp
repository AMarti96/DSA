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
    <script>
        $(document).ready(function () {
            $("button").click(function () {
                var operador1 = $("#operador1").val();
                var operador2 = $("#operador2").val();
                var operacion = $("#operacion :selected").text();

                $.post("/MyServlet3" , {
                    operador1 : operador1,
                    operador2 : operador2,
                    operacion : operacion
                },function (data, status) {
                    alert(data);
                });
            });
        });
    </script>
</head>
<body>
<a>
    <h3> Operador uno: </h3>
        <input type="number" id = "operador1" step="any"><br>

        <h3> Operador dos:</h3>
        <input type="number" id = "operador2" step="any"><br>
        <br/>

        <select name="operacion" id = "operacion" >
            <option id="suma">Sumar</option>
            <option id="resta">Restar</option>
            <option id="multiplicacion">Multiplicar</option>
            <option id="division">Dividir</option>
        </select>
        <br/>
        <br/>
        <button id="Aceptar">Aceptar</button>
</a>

</body>
</html>