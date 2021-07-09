package servlet;

import model.Categoria;
import model.CategoriaDAO;
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

@WebServlet("/Categoria")
public class CategoriaServlet extends HttpServlet {
    private final ProdottoDAO prodottoDAO=new ProdottoDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Categoria> categorie=(List<Categoria>) getServletContext().getAttribute("categorie");
        int id=Integer.parseInt(request.getParameter("id"));
        request.setAttribute("categoria",categorie.stream().filter(c->c.getId()==id).findAny().get());

        List<Prodotto> prodotti=prodottoDAO.doRetrieveByCategoria(id);
        request.setAttribute("prodotti",prodotti);

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/categoria.jsp");
        dispatcher.forward(request,response);
    }
}
