<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<content>
    <h1 id="naglowek" class="text-center">Dodawanie użytkownika</h1>

    <div class="registration-form">
        <form name="dodajUsera" method="post" action="index?action=register" >
            <div class="form-icon">
                <span><i class="icon icon-user"></i></span>
            </div>

            <div class="form-group">
                <input type="text" class="form-control item" id="nazwaUsera" name="username" placeholder="Login">
            </div>
            <div class="form-group">
                <input type="password" class="form-control item" id="hasło" name="password" placeholder="Hasło">
            </div>
            <div class="form-group">
                <input type="text" class="form-control item" id="email" name="email" placeholder="Email">
            </div>

            <div class="form-group">
                <select class="form-control" id="typUzytkownika_select" name="role">
                    <option value="2">administrator</option>
                    <option value="1">standardowy</option>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="czyZablokowany_select" name="enabled">
                    <option value="1">Odblokowany</option>
                    <option value="0">Zablokowany</option>
                </select>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-block create-account submit">Dodaj użytkownika</button>
            </div>
        </form>

    </div>

</content>