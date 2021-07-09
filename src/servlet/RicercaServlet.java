package servlet;

import model.Prodotto;
import model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Ricerca")
public class RicercaServlet extends HttpServlet {
    private final ProdottoDAO prodottoDAO=new ProdottoDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti=prodottoDAO.doRetrieveByNameOrDescription(request.getParameter("ricercatxt"));
        request.setAttribute("prodotti",prodotti);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/ricerca.jsp");
        dispatcher.forward(request,response);
    }
}
