package servlet;

import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VerificaUsername")
public class VerificaUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private final UtenteDAO utenteDAO=new UtenteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        response.setContentType("text/xml");
        if(username !=null && username.length()>=5 && username.matches("^[0-9a-zA-Z]+$") && utenteDAO.doRetrieveByUsername(username)==null){
            response.getWriter().append("<ok/>");
        }else{
            response.getWriter().append("<no/>");
        }
    }
}
