package ti.kontroler;

import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import ti.dao.ComicDao;
import ti.dao.FavoritesDao;
import ti.dao.UserDao;
import ti.model.Comic;
import ti.model.Favorites;
import ti.model.User;
import ti.util.Parser;

import static java.lang.Integer.parseInt;

@WebServlet("/index")
@MultipartConfig
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;
    private ComicDao comicDao;
    private FavoritesDao favoritesDao;
    public HttpSession sesja;

    public void init() {
        userDao = new UserDao();
        comicDao = new ComicDao();
        favoritesDao = new FavoritesDao();

        User adminCheck = userDao.getUserByUsername("admin");
        User userCheck = userDao.getUserByUsername("user");

        if(adminCheck == null){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(Parser.bCryptPasswordEncoder().encode("admin"));
            admin.setEmail("admin@comics.com");
            admin.setRole(2);
            userDao.saveUser(admin);
        }

        if(userCheck == null){
            User standardUser = new User();
            standardUser.setUsername("user");
            standardUser.setPassword(Parser.bCryptPasswordEncoder().encode("user"));
            standardUser.setEmail("user@interia.pl");
            standardUser.setRole(1);
            userDao.saveUser(standardUser);
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        sesja =request.getSession();
        User currentUser =(User) sesja.getAttribute("currentUser");

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

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
        else if(action.equals("saveEditedUser"))
        {
            saveEditedUser(request,response);
        }
        else if(action.equals("saveComic"))
        {
            saveComic(request,response);

        }
        else if(action.equals("saveEditedComic"))
        {
            saveEditedComic(request,response);
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
            String logout = "Uzytkownik poprawnie wylogowany";
            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+logout);
            dispatcher.forward(request, response);
        }
        else if(getAction.equals("delete"))
        {
            int idusera = parseInt(request.getParameter("id"));
            userDao.deleteUserById(idusera);
            response.sendRedirect("index.jsp?webpage=showUsers");
        }
        else if(getAction.equals("block"))
        {
            int idusera = parseInt(request.getParameter("id"));
            User user = userDao.getUserById(idusera);
            user.setEnabled(false);
            userDao.updateUser(user);
            response.sendRedirect("index.jsp?webpage=showUsers");
        }
        else if(getAction.equals("unlock"))
        {
            int idusera = parseInt(request.getParameter("id"));
            User user = userDao.getUserById(idusera);
            user.setEnabled(true);
            userDao.updateUser(user);
            response.sendRedirect("index.jsp?webpage=showUsers");

        }
        else if(getAction.equals("editUser"))
        {
            int idusera = parseInt(request.getParameter("id"));
            User user = userDao.getUserById(idusera);


            request.setAttribute("user",user);
            RequestDispatcher dispatcher2 = null;
            dispatcher2 = request.getRequestDispatcher("index.jsp?webpage=editUser");
            dispatcher2.forward(request, response);

        }
        else if(getAction.equals("addFavourite")){
            User user = (User) sesja.getAttribute("currentUser");
            int idComic = parseInt(request.getParameter("id"));
            Comic comic = comicDao.getComicById(idComic);
            Favorites favorites = new Favorites();
            favorites.setUser(user);
            favorites.setComic(comic);

            favoritesDao.saveFavourites(favorites);

            RequestDispatcher dispatcher2 = null;
            dispatcher2 = request.getRequestDispatcher("index.jsp");
            dispatcher2.forward(request, response);
        }
        else if(getAction.equals("deleteFavourite")){
            User user = (User) sesja.getAttribute("currentUser");
            int idComic = parseInt(request.getParameter("id"));
            Comic comic = comicDao.getComicById(idComic);
            favoritesDao.deleteFromFavourite(user,comic);

            RequestDispatcher dispatcher2 = null;
            dispatcher2 = request.getRequestDispatcher("index.jsp?webpage=favourites");
            dispatcher2.forward(request, response);
        }
        else if(getAction.equals("editComic"))
        {
            Integer id = parseInt(request.getParameter("id"));
            Comic comic = comicDao.getComicById(id);


            request.setAttribute("comic",comic);
            RequestDispatcher dispatcher2 = null;
            dispatcher2 = request.getRequestDispatcher("index.jsp?webpage=editComic");

            dispatcher2.forward(request, response);

        }
        else if(getAction.equals("delFavourite")){

            int idComic = parseInt(request.getParameter("id"));
            Comic comic = comicDao.getComicById(idComic);
            comicDao.deleteComicById(idComic);
            response.sendRedirect("index.jsp");

        }

    }

    private void saveEditedComic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer pageCount = null;
        Integer issueDate = null;

        RequestDispatcher dispatcher = null;

        String error = "";

        int id = parseInt(request.getParameter("id"));
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String language = request.getParameter("language");


        if(Parser.isStringInt(request.getParameter("pageCount"))){
            pageCount = parseInt(request.getParameter("pageCount"));
        }
        else{
            error="Wprowadzono nieprawidłową liczbę stron komiksu! Wartość musi być liczbą!";


            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+error);

            dispatcher.forward(request, response);
        }

        String cover = request.getParameter("cover");
        if(Parser.isStringInt(request.getParameter("issueDate"))){
            issueDate = parseInt(request.getParameter("issueDate"));
        }
        else{
            error="Wprowadzono nieprawidłowy rok wydania komiksu! Wartość musi być liczbą!";


            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+error);

            dispatcher.forward(request, response);
        }

        String publisher = request.getParameter("publisher");
        String description = request.getParameter("description");
        String title = request.getParameter("title");

        System.out.println( "\n" + id+ "\n");

        Comic newComic = comicDao.getComicById(id);


        newComic.setAuthor(author);
        newComic.setCategory(category);
        newComic.setLanguage(language);
        newComic.setPageCount(pageCount);
        newComic.setCover(cover);
        newComic.setIssueDate(issueDate);
        newComic.setPublisher(publisher);
        newComic.setDescription(description);
        newComic.setTitle(title);

        Part fileData = request.getPart("image");
        String fileName =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        fileName += Paths.get(fileData.getSubmittedFileName()).getFileName().toString();

        newComic.setImage(fileName);



        InputStream fileContent = fileData.getInputStream();

        //System.out.println(fileName);
        //System.out.println(fileContent.available());


        String path = getServletContext().getRealPath("/");

        Path outputPath = Paths.get(path,"usercontent",fileName);
        //System.out.println(request.getContextPath());


        //Files.createDirectories(outputPath.getParent());
        Files.createFile(outputPath);



        FileOutputStream output = new FileOutputStream(outputPath.toString());



        System.out.println(fileContent.available());

        while(fileContent.available() > 0){
            output.write(fileContent.read());
        }


        comicDao.updateComic(newComic);



        String info = "Komiks zaktualizowany!";


        //RequestDispatcher dispatcher = null;

        dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-success.jsp?errors="+info);

        dispatcher.forward(request, response);
    }



    private void saveComic(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String language = request.getParameter("language");
        Integer pageCount = null;
        Integer issueDate = null;
        RequestDispatcher dispatcher = null;

        String error = "";

        if(Parser.isStringInt(request.getParameter("pageCount"))){
            pageCount = parseInt(request.getParameter("pageCount"));
        }
        else{
            error="Wprowadzono nieprawidłową liczbę stron komiksu! Wartość musi być liczbą!";


            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+error);

            dispatcher.forward(request, response);
        }

        String cover = request.getParameter("cover");

        if(Parser.isStringInt(request.getParameter("issueDate"))){
            issueDate = parseInt(request.getParameter("issueDate"));
        }
        else{
            error="Wprowadzono nieprawidłowy rok wydania komiksu! Wartość musi być liczbą!";


            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+error);

            dispatcher.forward(request, response);
        }

        String publisher = request.getParameter("publisher");
        String description = request.getParameter("description");
        String title = request.getParameter("title");



        Comic newComic = new Comic();

        newComic.setAuthor(author);
        newComic.setCategory(category);
        newComic.setLanguage(language);
        newComic.setPageCount(pageCount);
        newComic.setCover(cover);
        newComic.setIssueDate(issueDate);
        newComic.setPublisher(publisher);
        newComic.setDescription(description);
        newComic.setTitle(title);




        //image processing






        Part fileData = request.getPart("image");
        String fileName =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        fileName += Paths.get(fileData.getSubmittedFileName()).getFileName().toString();

        newComic.setImage(fileName);

        InputStream fileContent = fileData.getInputStream();

        //System.out.println(fileName);
        //System.out.println(fileContent.available());


        String path = getServletContext().getRealPath("/");



        Path outputPath = Paths.get(path,"usercontent",fileName);
        //System.out.println(request.getContextPath());
        System.out.println(outputPath);


        //Files.createDirectories(outputPath.getParent());
        Files.createFile(outputPath);



        FileOutputStream output = new FileOutputStream(outputPath.toString());



        System.out.println(fileContent.available());

        while(fileContent.available() > 0){
            output.write(fileContent.read());
        }

        //File file = new File(".");
        //Arrays.stream(file.list()).forEach(System.out::println);



        //request.getParts().stream().forEach(part -> System.out.println(part.getName()));



        comicDao.saveComic(newComic);

        String info = "Komiks został pomyślnie dodany!";


       // RequestDispatcher dispatcher = null;

        dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-success.jsp?errors="+info);

        dispatcher.forward(request, response);

    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role1 = request.getParameter("role");
        String enabled1 = request.getParameter("enabled");
        Boolean enabled;
        if (role1 == null) {
            role1 = "1";
        }
        if (enabled1 == null) {
            enabled1 = "1";
        }
        if (enabled1.equals("1")) {
            enabled = true;
        } else {
            enabled = false;
        }
        Integer role = parseInt(role1);


        RequestDispatcher dispatcher = null;
        String errors = "Rejestracja nie powiodła sie! <br/>";

        if (username == "" || password == "" || email == "") {

            errors += "Nie wypełniono wszystkich pól. Wszystkie pola są obowiązkowe! <br/>";
            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors=" + errors);
        } else {



        //check if username is free

        User usernameCheck = userDao.getUserByUsername(username);


        User emailCheck = userDao.getUserByEmail(email);

        if (usernameCheck == null && emailCheck == null && Parser.isValidEmail(email)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(Parser.bCryptPasswordEncoder().encode(password));
            user.setEmail(email);
            user.setRole(role);
            user.setEnabled(enabled);
            System.out.print(user);


            userDao.saveUser(user);
            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-success.jsp");

        } else {


            if(usernameCheck != null){
                errors += "Użytkownik o nazwie " + username + " jest już zarejestorwany, wybierz inną nazwę użytkownika <br/> ";
            }

            if(emailCheck != null){
                errors += "Email " + email + " jest już zarejestrowany, wykorzystaj inny e-mail <br/>";
            }

            if(!Parser.isValidEmail(email)){
                errors+="Niepoprawny adres email! <br/>";

            }


            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors=" + errors);


        }
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
            if(Parser.isCorrectPasswd(usernameCheck, password))
            {
               if(usernameCheck.getEnabled()) {
                   sesja.setAttribute("currentUser", usernameCheck);
                   dispatcher = request.getRequestDispatcher("index.jsp");
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
    private void saveEditedUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role1 = request.getParameter("role");
        String enabled1= request.getParameter("enabled");
        Boolean enabled;
        if(role1 ==null) { role1="1";}
        if(enabled1==null){enabled1="1";}
       if(enabled1.equals("1"))
       {
           enabled = true;
       }
       else
       {
           enabled =false;
       }
        Integer role = parseInt(role1);
        RequestDispatcher dispatcher = null;


        //check if username is free
        User myUser = userDao.getUserById(id);
        User usernameCheck = userDao.getUserByUsername(username);
        User emailCheck = userDao.getUserByEmail(email);

        if(usernameCheck==null){ usernameCheck=myUser;}
        if(emailCheck==null){emailCheck=myUser;}

        if(usernameCheck.getUsername().equals(myUser.getUsername())  && emailCheck.getEmail().equals(myUser.getEmail()) && Parser.isValidEmail(email)){
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            if(password==""){
                user.setPassword(myUser.getPassword());
            }else{
                user.setPassword(Parser.bCryptPasswordEncoder().encode(password));
            }

            user.setEmail(email);
            user.setRole(role);
            user.setEnabled(enabled);

            userDao.updateUser(user);
            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-success.jsp");

        }
        else{

            String errors = "Edycja użytkownika nie powiodła się! <br/>";


            if(usernameCheck != null && !(usernameCheck.getUsername().equals(myUser.getUsername()))){
                errors += "Użytkownik " + username + " jest już zarejestrowany, wybierz inną nazwę użytkownika! <br/> ";
            }

            if(emailCheck != null && !(emailCheck.getEmail().equals(myUser.getEmail()))){
                errors += "Email " + email + " jest już zajęty. Wykorzystaj inny adres email <br/>";
            }

            if(!Parser.isValidEmail(email)){
                errors += "Niepoprawny adres email! <br/>";
            }



            dispatcher = request.getRequestDispatcher("WEB-INF/templates/register-error.jsp?errors="+errors);



        }
        dispatcher.forward(request, response);
    }
}