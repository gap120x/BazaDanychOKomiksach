package ti.kontroler;

import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import ti.dao.UserDao;
import ti.model.User;

@WebServlet("/index")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;
    public HttpSession sesja;
    public void init() {
        userDao = new UserDao();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        sesja =request.getSession();
        User currentUser =(User) sesja.getAttribute("currentUser");

        if(currentUser==null) {


            currentUser= new User();
            sesja.setAttribute("currentUser",currentUser);
        }
        String action = request.getParameter("action");
        if(action==null) action="";
        if(action.equals("register"))
        {
            register(request, response);
        }
        else if(action.equals("login")){

            login(request,response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sesja =request.getSession();
        User currentUser =(User) sesja.getAttribute("currentUser");

        if(currentUser==null) {


            currentUser= new User();
            sesja.setAttribute("currentUser",currentUser);
        }
        String getAction = request.getParameter("getaction");
        if(getAction==null) getAction="";
        if(getAction.equals("logout")){
            currentUser = new User();
            sesja.setAttribute("currentUser", currentUser);
            RequestDispatcher dispatcher = null;
            String logout = "Uzytkownik Poprawnie Wylogowany";
            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+logout);
            dispatcher.forward(request, response);
        }
        else if(getAction.equals("delete"))
        {
            int idusera = Integer.parseInt(request.getParameter("id"));
            userDao.deleteUserById(idusera);
            response.sendRedirect("index.jsp?webpage=showUsers");
        }
        else if(getAction.equals("block"))
        {
            int idusera = Integer.parseInt(request.getParameter("id"));
            User user = userDao.getUserById(idusera);
            user.setEnabled(false);
            userDao.updateUser(user);
            response.sendRedirect("index.jsp?webpage=showUsers");
        }
        else if(getAction.equals("unlock"))
        {
            int idusera = Integer.parseInt(request.getParameter("id"));
            User user = userDao.getUserById(idusera);
            user.setEnabled(true);
            userDao.updateUser(user);
            response.sendRedirect("index.jsp?webpage=showUsers");

        }
        //response.sendRedirect("register.jsp");
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
            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-success.jsp");

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



            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+errors);



        }

        dispatcher.forward(request, response);
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errors = "";

        RequestDispatcher dispatcher = null;



        User usernameCheck = userDao.getUserByUsername(username);


        if(usernameCheck != null){
            if(usernameCheck.getPassword().equals(password))
            {
               if(usernameCheck.getEnabled()) {
                   sesja.setAttribute("currentUser", usernameCheck);
                   dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-success.jsp");
               }
               else
               {
                   errors +="Użytkownik został zablokowany<br/> ";
                   dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+errors);
               }
            }
            else //nieprawidlowe haslo
            {
                errors +="Nieprawidłowe Hasło<br/> ";
                dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+errors);
            }
        }
        else // nieprawidlowy login
        {
            errors +="Nieprawidłowy Login<br/>";
            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+errors);
        }
        dispatcher.forward(request, response);
    }
}