package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Categoria;
import model.Prodotto;
import model.ProdottoDAO;

@MultipartConfig
@WebServlet("/AdminProdotto")
public class AdminProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    private static final String CARTELLA_UPLOAD = "images";


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MyException.checkAdmin(request);

        String idstr = request.getParameter("id");
        if (idstr != null) {
            if (request.getParameter("rimuovi") != null) {
                prodottoDAO.doDelete(Integer.parseInt(idstr));
                request.setAttribute("notifica", "Prodotto rimosso con successo.");
            } else {
                Prodotto prodotto;
                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");
                String prezzoCent = request.getParameter("prezzoCent");
                if (nome != null && descrizione != null && prezzoCent != null) {
                    prodotto = new Prodotto();
                    prodotto.setNome(nome);
                    prodotto.setDescrizione(descrizione);
                    prodotto.setPrezzoCentesimi(Long.parseLong(prezzoCent));

                    //prendo immagine
                    Part filePart = request.getPart("file");
                    boolean fileSubmitted=(filePart!=null && filePart.getSize()>0);
                    if(fileSubmitted) {
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                        String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                        Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));


                        // se un file con quel nome esiste già, gli cambia nome
                        for (int i = 2; Files.exists(pathDestinazione); i++) {
                            destinazione = CARTELLA_UPLOAD + File.separator + i + "_" + fileName;
                            pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
                        }
                        InputStream fileInputStream = filePart.getInputStream();
                        // crea CARTELLA_UPLOAD, se non esiste
                        Files.createDirectories(pathDestinazione.getParent());
                        // scrive il file
                        Files.copy(fileInputStream, pathDestinazione);
                        prodotto.setImmagine(fileName);
                    }

                    String[] categorie = request.getParameterValues("categorie");
                    prodotto.setCategorie(categorie != null ? Arrays.stream(categorie).map(id -> {
                        Categoria c = new Categoria();
                        c.setId(Integer.parseInt(id));
                        return c;
                    }).collect(Collectors.toList()) : Collections.emptyList());

                    if (idstr.isEmpty()) { // aggiunta nuovo prodotto
                        prodottoDAO.doSave(prodotto);
                        request.setAttribute("notifica", "Prodotto aggiunto con successo.");
                    } else { // modifica prodotto esistente
                        prodotto.setId(Integer.parseInt(idstr));
                        if(!fileSubmitted){
                        Prodotto old=prodottoDAO.doRetrieveById(prodotto.getId());
                        prodotto.setImmagine(old.getImmagine());
                        }
                        prodottoDAO.doUpdate(prodotto);
                        request.setAttribute("notifica", "Prodotto modificato con successo.");
                    }
                } else {
                    int id = Integer.parseInt(idstr);
                    prodotto = prodottoDAO.doRetrieveById(id);
                }
                request.setAttribute("prodotto", prodotto);
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminprodotto.jsp");
        requestDispatcher.forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
