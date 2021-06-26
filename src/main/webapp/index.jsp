<%@ page import="ti.util.Parser" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="currentUser" class="ti.model.User" scope="session"/>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Strona startowa</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.4.1/css/simple-line-icons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


    <link href="static/css/index.css" rel="stylesheet" />
    <link href="static/css/showBooks.css" rel="stylesheet" />
    <link href="static/css/navigation.css" rel="stylesheet" />
    <link href="static/css/register.css" rel="stylesheet" />
    <link href="static/css/login.css" rel="stylesheet" />
    <link rel="stylesheet" href="static/css/showUser.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>


<%
    String webpage =request.getParameter("webpage");

    if(currentUser.getRole()==1) {
        webpage = Parser.parse(webpage,"index;favourites");
    }
    else if(currentUser.getRole()==2) {
        webpage = Parser.parse(webpage,"index;showUsers;addUser;editUser");
    }
    else {
        webpage =  Parser.parse(webpage,"index;register;login;addComic");
    }
%>

<body>
<jsp:include page="/WEB-INF/templates/navbar.jsp"/>

<jsp:include page="/WEB-INF/templates/content.jsp">
    <jsp:param name="which" value="<%=webpage%>"/>
</jsp:include>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

<script src="js/bootstrap.min.js"></script>
</body>
</html>