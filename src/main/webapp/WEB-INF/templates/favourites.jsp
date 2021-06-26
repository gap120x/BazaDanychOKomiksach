<%@ page import="ti.dao.FavoritesDao" %>
<%@ page import="ti.model.Comic" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="currentUser" class="ti.model.User" scope="session"/>

<%

    FavoritesDao favoritesDao = new FavoritesDao();
    List<Comic> comics=null;
    comics = favoritesDao.getComicsByUserId(currentUser.getId());
%>

<content>


    </div>
    <h2 class="text-center">Lista Twoich ulubionych komiksów</h2>
    <table id="users" class="table table-active">
        <tr>
            <th scope="col" class="text-center">Okładka</th>
            <th scope="col" class="text-center">Tytuł</th>
            <th scope="col" class="text-center">Autorzy</th>
            <th scope="col" class="text-center">Wydawnictwo</th>
            <th scope="col" class="text-center">Data Wydania</th>
            <th scope="col" class="text-center" colspan="2">Operacje</th>
        </tr>


        <% for(int i=0;i<comics.size();i++) { %>
        <tr >

            <td valign="top" class="text-center" >
                <a href="bookDetails.html"><img src="usercontent/<%=comics.get(i).getImage()%>" width="100" height="150"/> </a>
            </td>
            <td valign="top" class="text-center">
                <%=comics.get(i).getTitle()%>
            </td>

            <td valign="top" class="text-center">
                <%=comics.get(i).getAuthor()%>
            </td>
            <td valign="top" class="text-center">
                <%=comics.get(i).getPublisher()%>
            </td>
            <td valign="top" class="text-center">
                <%=comics.get(i).getIssueDate()%>
            </td>

            <td valign="top" class="text-center">
                <a href=""> <button role="button" id="hearbutton" class="btn btn-outline-danger btn-lg">
                    <i class="fa fa-minus-circle"></i> Usuń </button> </a>
            </td>
        </tr>
        <%}%>

    </table>
    </div>
</content>