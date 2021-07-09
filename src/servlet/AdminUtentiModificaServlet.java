package servlet;

import model.CategoriaDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.ProfiloServlet.utenteDAO;

@WebServlet("/AdminUtentiModifica")
public class AdminUtentiModificaServlet extends HttpServlet {
    private final UtenteDAO utenteDAO=new UtenteDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyException.checkAdmin(request);
        String ids=request.getParameter("id");

            if(request.getParameter("rimuovi")!=null){
                utenteDAO.doDelete(Integer.parseInt(ids));
                request.setAttribute("notifica", "Utente rimosso con successo.");

            }
            

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/adminUtentiModifica.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
