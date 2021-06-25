package ti.kontroler;

import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ti.dao.UserDao;
import ti.model.User;

@WebServlet("/register")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.sendRedirect("register.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");






        RequestDispatcher dispatcher = null;



        //check if username is free

        User usernameCheck = userDao.getUserByUsername(username);


        User emailCheck = userDao.getUserByEmail(email);

        if(usernameCheck == null  && emailCheck == null){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(1);
            System.out.print(user);


            userDao.saveUser(user);
            dispatcher = request.getRequestDispatcher("register-success.jsp");

        }
        else{

            String errors = "";

            errors+="Register failed, please correct the following errors and retry: <br/>";

            if(usernameCheck != null){
                errors += "Username " + username + " is already taken, please choose a different username <br/> ";
            }

            if(emailCheck != null){
                errors += "Email " + email + " is already taken, please choose a different e-mail";
            }



            dispatcher = request.getRequestDispatcher("register-error.jsp?errors="+errors);



        }



        dispatcher.forward(request, response);
    }
}