<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DSA</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="script3.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#btn1").click(function () {
                var name = $("#name").val();
                var password = $("#password").val();
                $("#res").text("");
                $.post("/RegisterDB", {name: name, password: password}, function (responseText) {
                    if(responseText=="Ya existe el usuario") {
                        $("#res").text(responseText);
                    }
                    else{
                        document.cookie = name;
                        window.location = "Lista.jsp";}

                });
            });
            $("#btn2").click(function () {
                var name = $("#name").val();
                var password = $("#password").val();
                $("#res").text("");
                $.get("/RegisterDB", {name: name, password: password}, function (responseText) {
                    if (responseText == "No puedes pasar") {
                        $("#res").text(responseText);
                    }
                    else {
                        window.location = "Lista.jsp";
                        document.cookie = name;
                        }

                });
            });

        });
    </script>



</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="https://media.makeameme.org/created/this-webpage-is.jpg">DSA</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <h1>DAO</h1>
        <div class="form-group">
            <label>Nombre</label>
            <%-- TIPO DE VARIABLE Y NOMBRE DE VARIABLE ( EL NOMBRE SE USA EN EL SERVLET)(STEP ANY PERMITE DECIMALES) --%>
            <input class="form-control" id="name" type="text">
        </div>

        <div class="form-group">
            <label>Password</label>
            <%-- TIPO DE VARIABLE Y NOMBRE DE VARIABLE ( EL NOMBRE SE USA EN EL SERVLET)(STEP ANY PERMITE DECIMALES) --%>
            <input class="form-control" id="password" type="password">
        </div>

        <%-- ESTILO DEL BOTON PARA REALIZAR EL SERVLET Y EL TEXTO QUE TIENE --%>
        <button id = "btn1" class="btn btn-primary center-block"> Registrarse </button>
        <button id = "btn2" class="btn btn-primary center-block"> Iniciar Sesion </button>
        <p id="res"></p>


    </div>
</div>

</body>
</html>
