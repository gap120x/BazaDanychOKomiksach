<%@ page import="ti.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List, ti.model.User,ti.dao.UserDao" %>
<%

    UserDao userDao = new UserDao();
    List<User> user=null;
    user = userDao.getAll();
%>
<content>


    <h2 class="text-center" id="userList"><a href="index.jsp?webpage=addUser"><button type="button" id="dodajkomiksbutton" class="btn btn-success btn-xs"><i class="fa fa-plus-circle"> Dodaj nowego użytkownika </i></button></a></h2>


    <div class="show">
        <h2 class="text-center" >Lista użytkowników</h2>
        <table id="users" class="table table-active">
            <tr>
                <th scope="col" class="text-center">Nazwa użytkownika</th>
                <th scope="col" class="text-center">Email</th>
                <th scope="col" class="text-center">Typ użytkownika</th>
                <th scope="col" class="text-center" colspan="2">Operacje</th>
            </tr>
            <% for(int i=0;i<user.size();i++) { %>

            <tr>
                <td scope="col" class="text-center"><%=user.get(i).getUsername()%></td>
                <td scope="col" class="text-center"><%=user.get(i).getEmail()%></td>
                <td scope="col" class="text-center"><%
                    String role = "";
                    switch(user.get(i).getRole()){
                        case 1:
                            role = "standardowy";
                            break;
                        case 2:
                            role = "admin";
                            break;

                    }

                %>
                    <%=role%>
                </td>


                <td valign="top" class="text-center">
                    <a href="index?getaction=editUser&id=<%=user.get(i).getId()%>"><button type="button" id="operacjeadmin" class="btn btn-info btn-xs comicBtn"><i class="fa fa-edit"></i> Edytuj </button></a>
                    <a href="index?getaction=delete&id=<%=user.get(i).getId()%>"><button style="display:inline-block;" type="button" id="operacjeadmin" class="btn btn-danger btn-xs comicBtn"><i class="fa fa-trash"></i> Usuń </button></a>
                   <% if(user.get(i).getEnabled()){%>
                    <a href="index?getaction=block&id=<%=user.get(i).getId()%>"><button style="display:inline-block;" type="button" id="operacjeadmin" class="btn btn-warning btn-xs comicBtn"><i class="fa fa-lock"> Zablokuj </i></button></a>
                    <%} else {%>
                    <a href="index?getaction=unlock&id=<%=user.get(i).getId()%>"><button style="display:inline-block;" type="button" id="operacjeadmin" class="btn btn-warning btn-xs comicBtn"><i class="fa fa-unlock-alt"> Odblokuj </i></button></a>
                    <%}%>
                </td>
            </tr>
           <%}%>
        </table>
    </div>
</content>