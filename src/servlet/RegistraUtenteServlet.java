package servlet;

import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistraUtente")
public class RegistraUtenteServlet extends HttpServlet {
    //controlli lato server
    private UtenteDAO utenteDAO=new UtenteDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("utente")!=null)
            throw new MyException("Utente loggato.");
        String username=request.getParameter("username");
        if(!(username !=null && username.length()>=5 && username.matches("[0-9a-zA-Z]+$"))){
            throw new MyException("Username non valido");
        }

        String password=request.getParameter("password");
        if(!(username!=null && password.length()>=8 && !password.toUpperCase().equals(password) && !password.toLowerCase().equals(password) && password.matches(".*[0-9].*"))){
            throw new MyException("Password non valida");
        }

        String passwordConferma=request.getParameter("passwordconferma");
        if(!password.equals(passwordConferma)){
            throw new MyException("Le due password inserite non coincidono");
        }

        String nome=request.getParameter("nome");
        if(!(nome!=null && nome.trim().length()>0 && nome.matches("[a-zAzZ|u00C0-|u00ff]+$"))){
            throw new MyException("Nome non valido");
        }

        String email=request.getParameter("email");
        if(!(email!=null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))){
            throw new MyException("Email non valida");
        }

        Utente utente=new Utente();
        utente.setUsername(username);
        utente.setNome(nome);
        utente.setPassword(password);
        utente.setEmail(email);
        utenteDAO.doSave(utente);
        request.getSession().setAttribute("utente",utente);

        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/jsp/RegistrazioneSuccesso.jsp");
        dispatcher.forward(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
