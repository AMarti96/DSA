<%--
  Created by IntelliJ IDEA.
  User: Adri
  Date: 06/11/2016
  Time: 13:41
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
</head>
<body>
<div class = "jumbotron">
    <h1 class = "text-center"> Resultados</h1>
    <div class = "contanier">
        <body>
        <c:forEach items ="${fibo}" var = "fibo">
            <h2 class="text-center">${fibo}</h2>
        </c:forEach>
        </body>
    </div>
</div>

</body>
</html>
