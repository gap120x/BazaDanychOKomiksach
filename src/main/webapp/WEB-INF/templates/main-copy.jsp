<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<content>
  <div id="books" class="show">
    <form method="post" id="wyszukiwarka" action="">
      <div class="input-group">

        <select  class="form-control" >
          <option id="aaa" value="tytul">Tytuł</option>
          <option value="autorzy">Autorzy</option>
          <option value="wydawnictwo">Wydawnicto</option>
          <option value="wydawnictwo">Data Wydania</option>
        </select>
        <input type="text" class="form-control" placeholder="Wyszukaj">
        <div class="input-group-append">
          <button class="btn btn-secondary" type="submit">
            <i class="fa fa-search"></i>
          </button>
    </form>

  </div>

  </div>

  <h2 class="text-center"><a href="index.jsp?webpage=addComic"><button type="button" id="dodajkomiksbutton" class="btn btn-success btn-xs"><i class="fa fa-plus-circle"> Dodaj nowy komiks </i></button></a></h2>

  <h2 class="text-center">Lista komiksów</h2>

  <table id="users" class="table table-active">


    <tr>
      <th scope="col" class="text-center">Okładka</th>
      <th scope="col" class="text-center">Tytuł</th>
      <th scope="col" class="text-center">Autorzy</th>
      <th scope="col" class="text-center">Wydawnictwo</th>
      <th scope="col" class="text-center">Data Wydania</th>
      <th scope="col" class="text-center" colspan="2">Operacje</th>
    </tr>
    <tr >

      <td valign="top" class="text-center" >
        <a href="bookDetails.html"><img src="static/gfx/lucyfer.jpg" width="100" height="150"/> </a>
      </td>
      <td valign="top" class="text-center">
        Lucyfer. Tom 1
      </td>
      <td valign="top" class="text-center">
        Peter Gross,Dean Ormston i Scott Hampton
      </td>
      <td valign="top" class="text-center">
        Egmont
      </td>
      <td valign="top" class="text-center">
        2021
      </td>

      <td valign="top" class="text-center">
        <a href="" class="favouriteComicBtn"> <button type="button" id="hearbutton" class="btn btn-outline-danger btn-lg">  <i id="heart" class="fa fa-heart"></i> Ulubione</button> </a>
        <button onclick="location.href='editComic.html';" type="button" id="operacjeadmin" class="btn btn-info btn-xs comicBtn"><i class="fa fa-edit"></i> Edytuj </button>
        <button style="display:inline-block;" type="button" id="operacjeadmin" class="btn btn-danger btn-xs comicBtn"><i class="fa fa-trash"></i> Usuń </button>
      </td>
    </tr>
    <tr >

      <td valign="top" class="text-center" >
        <a href="bookDetails.html"><img src="static/gfx/muminki.jpg" width="100" height="150"/> </a>
      </td>
      <td valign="top" class="text-center">
        Muminki. Tom 1
      </td>
      <td valign="top" class="text-center">
        Tove Jansson
      </td>
      <td valign="top" class="text-center">
        Egmont
      </td>
      <td valign="top" class="text-center">
        2021
      </td>
      <td valign="top" class="text-center">
        <a href="" class="favouriteComicBtn"> <button type="button" id="hearbutton" class="btn btn-outline-danger btn-lg">  <i id="heart" class="fa fa-heart"></i> Ulubione</button> </a>
        <button type="button" id="operacjeadmin" class="btn btn-info btn-xs comicBtn"><i class="fa fa-edit"></i> Edytuj </button>
        <button style="display:inline-block;" type="button" id="operacjeadmin" class="btn btn-danger btn-xs comicBtn"><i class="fa fa-trash"></i> Usuń </button>
      </td>
    </tr>

  </table>
  </div>
</content>