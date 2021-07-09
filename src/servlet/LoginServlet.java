package servlet;

import model.Login;
import model.LoginDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private final UtenteDAO utenteDAO=new UtenteDAO();
    private final LoginDAO loginDAO = new LoginDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Utente utente=null;
        if(username!=null && password!=null)
            utente=utenteDAO.doRetrieveByUsernamePassword(username,password);
        if(utente==null)
            throw new MyException("Utente e/o password non validi.");
        request.getSession().setAttribute("utente",utente);
        Login login = new Login();
        login.setIdUtente(utente.getId());
        login.setToken(UUID.randomUUID().toString());
        login.setTime(Timestamp.from(Instant.now()));

        loginDAO.doSave(login);

        Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
        cookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
        response.addCookie(cookie);

        String dest = request.getHeader("referer");
        if (dest == null || dest.contains("/Login") || dest.trim().isEmpty()) {
            dest = ".";
        }
        response.sendRedirect(dest);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
