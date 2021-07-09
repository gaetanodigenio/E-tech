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

@WebServlet("/Prodotto")
public class ProdottoServlet extends HttpServlet {
    private final ProdottoDAO prodottoDAO=new ProdottoDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Prodotto prodotto=prodottoDAO.doRetrieveById(id);
        if(prodotto==null)
            throw new MyException("Prodotto non trovato");
        request.setAttribute("prodotto",prodotto);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
        dispatcher.forward(request,response);

    }
}
