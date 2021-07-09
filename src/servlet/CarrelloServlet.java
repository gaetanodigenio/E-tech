package servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Carrello.ProdottoQuantita;
import model.ProdottoDAO;

@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }
        String prodIdStr = request.getParameter("prodId");
        if (prodIdStr != null) {
            int prodId = Integer.parseInt(prodIdStr); //salvo id prodotto

            String addNumStr = request.getParameter("addNum");
            if (addNumStr!=null) {  //caso aggiunta al carrello
                int addNum = Integer.parseInt(addNumStr);   //salvo numero prodotti aggiunti
                ProdottoQuantita prodQuant = carrello.get(prodId);
                if (prodQuant != null) {
                    prodQuant.setQuantita(prodQuant.getQuantita() + addNum); //se gia c'è un prodotto uguale al carrello, aumento quantità
                } else {
                    carrello.put(prodottoDAO.doRetrieveById(prodId), addNum);  //aggiungo prodotto al carrello con quantità scelta
                }
            } else {  //caso rimozione dal carrello o modifica quantità carrello
                String setNumStr = request.getParameter("setNum");
                if (setNumStr != null) {
                    int setNum = Integer.parseInt(setNumStr);
                    if (setNum <= 0) {
                        carrello.remove(prodId);
                    } else {
                        ProdottoQuantita prodQuant = carrello.get(prodId);
                        if (prodQuant != null) {
                            prodQuant.setQuantita(setNum);
                        } else {
                            carrello.put(prodottoDAO.doRetrieveById(prodId), setNum);
                        }
                    }
                }
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

