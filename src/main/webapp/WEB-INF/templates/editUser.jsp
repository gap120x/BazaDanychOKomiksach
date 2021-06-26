<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ti.model.User"%>
<%User user = (User) request.getAttribute("user");
%>
<content>
  <h1 id="naglowek" class="text-center">Edycja Użytkownika</h1>

  <div class="registration-form">
    <form name="dodajUsera" method="post" action="index?action=saveEditedUser" >
      <div class="form-icon">
        <span><i class="icon icon-user"></i></span>
      </div>
      <input type="hidden" name="id" value="<%=user.getId()%>">
      <div class="form-group">
        <input type="text" class="form-control item" id="nazwaUsera" name="username" value="<%=user.getUsername()%>" placeholder="Login">
      </div>
      <div class="form-group">
        <input type="password" class="form-control item" id="hasło" name="password" value="<%=user.getPassword()%>" placeholder="Hasło">
      </div>
      <div class="form-group">
        <input type="text" class="form-control item" id="email" name="email" value="<%=user.getEmail()%>" placeholder="Email">
      </div>

      <div class="form-group">
        <select class="form-control" id="typUzytkownika_select" name="role">
          <option value="2" <%if(user.getRole()==2){%>selected<%}%>>administrator</option>
          <option value="1"<%if(user.getRole()==1){%>selected<%}%>>standardowy</option>
        </select>
      </div>
      <div class="form-group">
        <select class="form-control" id="czyZablokowany_select" name="enabled">
          <option value="1" <%if(user.getEnabled()){%>selected<%}%>>Odblokowany</option>
          <option value="0" <%if(!user.getEnabled()){%>selected<%}%>>Zablokowany</option>
        </select>
      </div>

      <div class="form-group">
        <button type="submit" class="btn btn-block create-account submit">Zapisz zmiany</button>
      </div>
    </form>

  </div>

</content>