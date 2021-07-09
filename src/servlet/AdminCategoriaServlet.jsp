package servlet;

import model.Categoria;
import model.CategoriaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminCategoria")
public class AdminCategoriaServlet extends HttpServlet {
    private final static CategoriaDAO categoriaDAO=new CategoriaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyException.checkAdmin(request);
        String ids=request.getParameter("id");
        if(ids!=null){
            List<Categoria> categorie=(List<Categoria>) getServletContext().getAttribute("categorie");
            Categoria categoria=null;
            if(!ids.isEmpty()){
                int id=Integer.parseInt(ids);
                categoria=categorie.stream().filter(c->c.getId()==id).findAny().get();
            } //cancellazione
            if(request.getParameter("rimuovi") !=null){
                categoriaDAO.doDelete(categoria.getId());
                categorie.remove(categoria);
                request.setAttribute("notifica","Categoria rimossa con successo");
            }else{ // aggiunta o modifica
                String nome=request.getParameter("nome");
                String descrizione=request.getParameter("descrizione");
                if(nome!=null && descrizione!=null){
                    if(categoria==null){ //aggiunta
                        categoria=new Categoria();
                        categoria.setNome(nome);
                        categoria.setDescrizione(descrizione);
                        categoriaDAO.doSave(categoria);
                        categorie.add(categoria);
                        request.setAttribute("notifica","Categoria aggiunta con successo");
                    }else{ //modifica
                        categoria.setNome(nome);
                        categoria.setDescrizione(descrizione);
                        categoriaDAO.doUpdate(categoria);
                        request.setAttribute("notifica","Categoria modificata con successo");
                    }
                }
                request.setAttribute("categoria",categoria);
            }
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/admincategoria.jsp");
        dispatcher.forward(request,response);
    }
}
