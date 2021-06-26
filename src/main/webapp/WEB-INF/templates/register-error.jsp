<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Komunikat</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <link href="static/css/message.css" rel="stylesheet" />
</head>

</head>
<body>
<div class="container return">
    <button type="submit" class="btn"><a href="index.jsp">Strona Główna</a></button>
</div>

<div class="container">
    <div class="row col-md-10 col-md-offset-3">
        <div class="card card-body text-center">

            <p><%= request.getParameter("errors") %></p>
        </div>
    </div>
</div>
</body>
</html>