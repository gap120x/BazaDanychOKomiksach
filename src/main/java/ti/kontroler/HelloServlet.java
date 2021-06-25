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

        User dbUser = userDao.getUserByUsername(username);


        if(dbUser != null){
            dispatcher = request.getRequestDispatcher("register-usernameTaken.jsp");
            System.out.print(dbUser);
        }
        else{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            System.out.print(user);


            userDao.saveUser(user);
            dispatcher = request.getRequestDispatcher("register-success.jsp");
        }





        dispatcher.forward(request, response);
    }
}