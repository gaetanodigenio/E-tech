package servlet;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Profilo")
public class ProfiloServlet extends HttpServlet {
    final static UtenteDAO utenteDAO=new UtenteDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Utente utente=(Utente) session.getAttribute("utente");
        String dest;
        ArrayList<Utente> utenti=new ArrayList<>();
        if(utente.isAdmin()){
            dest="WEB-INF/jsp/adminprofilo.jsp";
            utenti=utenteDAO.doRetrieveAll();
            request.setAttribute("utenti",utenti);
        }else{
            dest="WEB-INF/jsp/profilo.jsp";
        }

        RequestDispatcher dispatcher=request.getRequestDispatcher(dest);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
