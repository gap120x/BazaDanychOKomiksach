<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="currentUser" class="ti.model.User" scope="session"/>
<header>
    <nav class="navbar navbar-light bg-light navbar-expand-xl">
        <a class="navbar-brand" href="index.html">Baza danych o komiksach</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainmenu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainmenu">
            <ul class="navbar-nav">
                <li class="navbar-item">
                    <a class="nav-link" href="/">Strona główna</a>
                </li>

<% if(currentUser.getRole() == 1){ %>
                <li class="navbar-item">
                    <a class="nav-link" href="favourite.html">Ulubione</a>
                </li>

                <%}%>


                <% if(currentUser.getRole() == 2){ %>

                <li class="navbar-item">
                    <a class="nav-link" href="index.jsp?webpage=showUsers">Zarządzanie użytkownikami</a>
                </li>

                <%}%>

                <% if(currentUser.getRole()>0){ %>
                <li class="navbar-item">
                    <a class="nav-link" href="editAccount.html">Edytuj konto</a>
                </li>
                <%}%>

                <% if(currentUser.getRole()<0 ){ %>
                <li class="navbar-item">
                    <a class="nav-link" href="index.jsp?webpage=register">Rejestracja</a>
                </li>
                <%}%>


                <% if(currentUser.getRole()<0 ){ %>
                <li class="navbar-item">
                    <a class="nav-link" href="index.jsp?webpage=login">Zaloguj się</a>
                </li>
                <%}%>

                <% if(currentUser.getRole() >0) {%>
                <li class="navbar-item">
                    <a class="nav-link" href="index?getaction=logout">Wyloguj</a>
                </li>
                <%}%>
            </ul>
        </div>

    </nav>

</header>