<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div class="login-form">
    <form method="post" onsubmit="" action="index?action=login">
        <div class="form-icon">
            <span><i class="icon icon-user"></i></span>
        </div>
        <div class="form-group">
            <input type="text" class="form-control item" id="uname"  name="username" placeholder="Login" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control item" id="passwd" name="password"  placeholder="Hasło" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-block login-button">Zaloguj</button>
        </div>
        <label>Nie masz jeszcze konta?</label>
        <div class="form-group">
            <button type="submit" class="btn btn-block register-button submit"><a href="index.jsp?webpage=register">Zarejestruj się</a></button>
        </div>
    </form>

</div>