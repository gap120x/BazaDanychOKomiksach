<%@ page import="ti.dao.ComicDao" %>
<%@ page import="ti.model.Comic" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="currentUser" class="ti.model.User" scope="session"/>

<%

  ComicDao comicDao = new ComicDao();
  List<Comic> comics=null;
  comics = comicDao.getAll();
%>

<content>


  </div>

  <%if(currentUser.getRole()==2){%>
  <h2 class="text-center"><a href="index.jsp?webpage=addComic"><button type="button" id="dodajkomiksbutton" class="btn btn-success btn-xs"><i class="fa fa-plus-circle"> Dodaj nowy komiks </i></button></a></h2>
<%}%>
  <h2 class="text-center">Lista komiksów</h2>

  <table id="users" class="table table-active">

    <tr>
      <th scope="col" class="text-center">Okładka</th>
      <th scope="col" class="text-center">Tytuł</th>
      <th scope="col" class="text-center">Autorzy</th>
      <th scope="col" class="text-center">Wydawnictwo</th>
      <th scope="col" class="text-center">Data Wydania</th>
      <%if(currentUser.getRole()>0){%>
      <th scope="col" class="text-center" colspan="2">Operacje</th>
      <%}%>
    </tr>


    <% for(int i=0;i<comics.size();i++) { %>
    <tr>
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
        <%if(currentUser.getRole()==1){%>
        <a href="index?getaction=addFavourite&id=<%=comics.get(i).getId()%>" class="favouriteComicBtn"> <button type="button" id="hearbutton" class="btn btn-outline-danger btn-lg">  <i id="heart" class="fa fa-heart"></i> Ulubione</button> </a>
       <%
        }
         if (currentUser.getRole() == 2) {


       %>

        <a href="index?getaction=editComic&id=<%=comics.get(i).getId()%>"><button type="button" id="operacjeadmin" class="btn btn-info btn-xs comicBtn"><i class="fa fa-edit"></i> Edytuj </button></a>
        <a href="index?getaction=delFavourite&id=<%=comics.get(i).getId()%>" ><button style="display:inline-block;" type="button" id="operacjeadmin" class="btn btn-danger btn-xs comicBtn"><i class="fa fa-trash"></i> Usuń </button> </a>
      </td>
<%}%>

    </tr>


    <%}%>




  </table>
  </div>
</content>