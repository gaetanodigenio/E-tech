<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.nome}"/>
</jsp:include>

<section>
    <div style="padding: 20px;text-align: center;">
        <div>
            <img src="images/${prodotto.immagine}" width="250" height="250">  <!--Mostra immagine prodotto-->
        </div>

        <div>
            <h3>${prodotto.nome}</h3>
            ${prodotto.descrizione}              <!--Mostra descrizione prodotto-->
        </div><br>

        <div>
            <c:if test="${utente.admin}">          <!-- Se admin loggato, mostra modifica e rimuovi-->
                <form action="AdminProdotto" method="post">
                    <input type="hidden" name="id" value="${prodotto.id}">
                    <input type="submit" value="Modifica" style="padding: 10px;">
                    <input type="submit" name="rimuovi" value="Rimuovi" style="padding: 10px;">
                </form>
            </c:if>
            <p>Categorie:    <!--Mostra categorie a cui appartiene il prodotto-->
                <c:forEach items="${prodotto.categorie}" var="categoria" varStatus="status">  <!--Dopo l'ultima categoria non deve stampare virgola-->
                    <a href="Categoria?id=<c:out value="${categoria.id}"/>"><c:out value="${categoria.nome}" /></a><c:if test="${not status.last}">, </c:if>
                </c:forEach>
            </p>


            <h4>Prezzo: ${prodotto.prezzoEuro} &euro;</h4> <br>  <!--Prezzo prodotto-->

            <form action="Carrello" method="post">
                <label><strong>Quantità:</strong></label>              <!--Mostra quantità da selezionare per quel prodotto-->
                <select name="addNum">
                    <c:forEach begin="1" end="25" varStatus="loop">
                        <option value="${loop.index}" style="padding: 10px;float: left;">${loop.index}</option>
                    </c:forEach>
                </select>


                <input type="hidden" name="prodId" value="${prodotto.id}">
                <input type="submit" value="Aggiungi al carrello" style="padding: 10px;float: right;">
            </form>
        </div>

    </div>
</section>
<%@include file="footer.html"%>
