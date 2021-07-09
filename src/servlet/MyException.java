package servlet;

import model.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class MyException extends ServletException {
    public MyException() {}

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public MyException(Throwable rootCause) {
        super(rootCause);
    }

    public static void checkAdmin(HttpServletRequest request) throws MyException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || !utente.isAdmin()) {
            throw new MyException("Utente non autorizzato");
        }
    }
}
