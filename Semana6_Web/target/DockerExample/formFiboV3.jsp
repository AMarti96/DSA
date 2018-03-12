<%--
  Created by IntelliJ IDEA.
  User: Adri
  Date: 06/11/2016
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Fibonacci</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("button").click(function () {
                var sucesiones = $("#sucesiones").val();
                $("#res").text(""); //para resetear el text
                $.get("/MyServlet3" , {sucesiones : sucesiones},function (responseJson) {
                    var $ul = $("<ul>").appendTo($("#res"));
                    $.each(responseJson, function (index, item) {
                        $("<li>").text(item).appendTo($ul);
                    });
                });
            });
        });
    </script>
</head>
<body>
<div class = "jumbotron">
    <h1 class = "text-center"> Fibonacci</h1>
    <div class = "container">
        <body>
            Escribe la cantidad de succesiones: <br>
            <input type="number" id="sucesiones"><br>
            <button> Aceptar 2</button><br>
        </body>
        <h2 id = "res"> </h2>
    </div>
</div>

</body>
</html>
