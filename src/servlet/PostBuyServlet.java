package servlet;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@WebServlet("/PostBuy")
public class PostBuyServlet extends HttpServlet {
    private static OrdineDAO ordineDAO=new OrdineDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Utente utente= (Utente) session.getAttribute("utente");
        if(utente==null){
            utente = new Utente();
            session.setAttribute("utente", utente);
        }

        Carrello carrello = (Carrello) session.getAttribute("carrello");


        //lista nomi prodotti acquistati e lista numero prodotti acquistati
        StringBuffer prodottiAcquistati =new StringBuffer();
        StringBuffer numeroProdottiAcquistati=new StringBuffer();
        for(Carrello.ProdottoQuantita c:carrello.getProdotti()){
            prodottiAcquistati.append(c.getProdotto().getNome()+" ");
            numeroProdottiAcquistati.append("x"+c.getQuantita()+" ");
        }
        String listaProdotti=prodottiAcquistati.toString();
        String numeroProdotti=numeroProdottiAcquistati.toString();


        //salvo ordine nel database
        Ordine ordine=new Ordine();
        ordine.setDataOrdine(Timestamp.from(Instant.now()));
        ordine.setTotaleOrdine(carrello.getPrezzoTotEuro());
        ordine.setIdUtente(utente.getId());
        ordine.setNomeUtente(utente.getUsername());
        ordine.setListaProdotti(listaProdotti);
        ordine.setNumeroProdotti(numeroProdotti);
        ordineDAO.doSave(ordine);


        session.removeAttribute("carrello");

        //elimina tutti i prodotti dal carrello quando effettuato l'acquisto
        //List<Carrello.ProdottoQuantita> toRemove = new ArrayList();
        //for (Carrello.ProdottoQuantita prodotto : carrello.getProdotti()) {
          //  toRemove.add(prodotto);
        //}

       // carrello.getProdotti().removeAll(toRemove);


        
        session.setAttribute("ordine",ordine);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/postBuy.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
