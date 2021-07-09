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

@WebServlet(name = "HomePageServlet" ,urlPatterns = "",loadOnStartup = 1)  //pagina principale, loadOnStartup viene caricata appena avviato tomcat
public class HomePageServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        CategoriaDAO categoriaDAO=new CategoriaDAO();
        List<Categoria> categorie= categoriaDAO.doRetrieveAll();
        getServletContext().setAttribute("categorie",categorie);
        super.init();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
        dispatcher.forward(request,response);
    }
}
