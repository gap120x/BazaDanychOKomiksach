<%@ page import="ti.model.Comic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Comic comic = (Comic) request.getAttribute("comic");


%>

<content>


    <div class="container text">
        <div class="row heading text-center">
            <div class="col-md-12">
                <ul class="list-group text-center">
                    <div class="text-center">
                        <h1> <%=comic.getTitle()%> </h1>

                        <img src="usercontent/<%=comic.getImage()%>" width="200" height="300"/>
                    </div>
                </ul>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <table class="table table-active">
                    <tr class="text-center">
                        <td>Autor</td>
                        <td><%=comic.getAuthor()%></td>
                    </tr>
                    <tr class="text-center">
                        <td>Kategoria</td>
                        <td><%=comic.getCategory()%></td>
                    </tr>
                    <tr class="text-center">
                        <td>Język</td>
                        <td><%=comic.getLanguage()%></td>
                    </tr>
                    <tr class="text-center">
                        <td>Liczba stron</td>
                        <td><%=comic.getPageCount()%></td>
                    </tr>
                    <tr class="text-center">
                        <td>Oprawa</td>
                        <td><%=comic.getCover()%></td>
                    </tr>
                    <tr class="text-center">
                        <td>Rok wydania</td>
                        <td><%=comic.getIssueDate()%></td>
                    </tr>
                    <tr class="text-center">
                        <td>Wydawnictwo</td>
                        <td><%=comic.getPublisher()%></td>
                    </tr>
                </table>

            </div>
            <div class="col-md-4 text-center">
                <p>
                    <%=comic.getDescription()%>

                </p>
            </div>
        </div>
    </div>

</content>