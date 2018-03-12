<%--
  Created by IntelliJ IDEA.
  User: Adri
  Date: 20/11/2016
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cesta</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="script3.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#res").text("");
            var cookie = document.cookie;
            $.get("/Addetakemon2", {cookie:cookie},function(responseJson) {
                var $ul = $("<ul>").appendTo($("#res"));
                $.each(responseJson, function(index, item) {
                    $("<li>").text("Etakemon numero: "+item).appendTo($ul)});
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
        <ul class="nav navbar-nav">
            <li><a href="Lista.jsp" >Lista</a></li>
            <li class="active"><a href="Cazados.jsp" >Cazados</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <h1>Cazados</h1>
        <p id="res"></p>
        <footer id="foot01"></footer>
    </div>

</div>
</body>
</html>
