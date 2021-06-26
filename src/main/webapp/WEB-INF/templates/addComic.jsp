<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<content>
    <h1 id="naglowek" class="text-center">Dodawanie komiksu</h1>

    <form id="editComic" action="index?action=saveComic" method="post" enctype="text/plain">
        <div>
            <div class="row heading text-center">
                <div class="col-md-12">
                    <ul class="list-group text-center">

                        <div class="d-flex justify-content-center">
                            <input type="text" class="form-control" id="comicName" placeholder="Tytuł" style="width:200px">


                        </div>
                        <div class="d-flex justify-content-center">


                            <img id="coverImg" src="static/gfx/add.png" width="300" height="400" alt="okładka"/>


                        </div>
                        <div class="d-flex justify-content-center">
                            <p class="btn btn-info btn-xs comicBtn">

                                <i class="fa fa-edit"></i> Zmień okładkę </br>


                            <input type="file" id="cover" name="cover" onchange="document.getElementById('coverImg').src = window.URL.createObjectURL(this.files[0])"s/>


                            </p>


                        </div>


                    </ul>
                </div>
            </div>
        </div>

        <div class="container">

            <div class="row" style="height:100%">
                <div class="col-md-8">
                    <table class="table table-active" style="height:100%">
                        <tr class="text-center">
                            <td>Autor</td>
                            <td class="form-group">
                                <input type="text"class="form-control" id="comicAutor" placeholder="Autor">
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td>Kategoria</td>
                            <td class="form-group">
                                <input type="text"class="form-control" id="comicCategory" placeholder="Kategoria">
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td>Język</td>
                            <td class="form-group">
                                <input type="text"class="form-control" id="comicLanguage" placeholder="Język">
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td>Liczba stron</td>
                            <td class="form-group">
                                <input type="text"class="form-control" id="comicPages" placeholder="Liczba stron">
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td>Oprawa</td>
                            <td class="form-group">
                                <input type="text"class="form-control" id="comicCover" placeholder="Miękka">
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td>Rok wydania</td>
                            <td class="form-group">
                                <input type="text"class="form-control" id="comicYear" placeholder="2021">
                            </td>
                        </tr>
                        <tr class="text-center">
                            <td>Wydawnictwo</td>
                            <td class="form-group">
                                <input type="text"class="form-control" id="comicPublishing" placeholder="Wydawnictwo">
                            </td>
                        </tr>
                    </table>

                </div>
                <div class="col-md-4 text-center">
                    <table class="table table-active" style="height:100%">
                        <tr class="text-center" style="height:20px">
                            <td>Opis</td>
                        </tr>
                        <tr class="text-center">
                            <td>
							<textarea name="description" class="h-100 w-100" form="editComic">
							</textarea>
                            </td>

                        </tr>
                    </table>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <button style="display:inline-block;margin:5px" type="submit" form ="editComic" class="btn btn-warning btn-xs comicBtn"><i class="fa fa-lock" aria-hidden="true"> Zapisz zmiany </i></button>

            </div>
    </form>
    </div>
    <div class="container">
    </div>

</content>