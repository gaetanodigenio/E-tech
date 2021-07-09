package servlet;

import model.Ordine;
import model.OrdineDAO;
import model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet("/MyOrders")
public class MyOrdersServlet extends HttpServlet {
    OrdineDAO ordineDAO=new OrdineDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Utente utente=(Utente) session.getAttribute("utente");
        String ids=request.getParameter("id");

        if(ids!=null){
            int id=Integer.parseInt(ids);
            ArrayList<Ordine> ordiniById=new ArrayList<>();
            ordiniById=ordineDAO.doRetrieveByUserId(id);
            request.setAttribute("ordini",ordiniById);
            RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/myorders.jsp");
            dispatcher.forward(request,response);
        }else{
        // se utente admin mostra tutti gli ordini del database, altrimenti solo i personali
        if(utente.isAdmin()){
            ArrayList<Ordine> ordiniUtenti=new ArrayList<>();
            ordiniUtenti=ordineDAO.doRetrieveAll();
            request.setAttribute("ordini",ordiniUtenti);
        }else{
            ArrayList<Ordine> ordiniUtente=new ArrayList<>();
            ordiniUtente=ordineDAO.doRetrieveByUserId(utente.getId());
            request.setAttribute("ordini",ordiniUtente);
        }

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/myorders.jsp");
        dispatcher.forward(request,response);

        }
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
