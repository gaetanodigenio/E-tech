<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>
<section>
    <h1 style="text-align: center;padding: 15px;">Carrello</h1>
    <div style="padding: 10px;">

        <c:forEach items="${carrello.prodotti}" var="pq">
            <h3 style="text-align: center;margin: 0 300px 0 300px;">
                <a href="Prodotto?id=${pq.prodotto.id}">${pq.prodotto.nome}</a>
            </h3>
            <div style="text-align: center;">
                <a href="#"><img src="images/${pq.prodotto.immagine}" width="250" height="250" style="margin-top: 10px;"></a>
            </div>


            <div style="text-align: left;margin: 0 300px 0 300px;">

                <p>${pq.prodotto.descrizione}</p>
                <h5>Quantità: ${pq.quantita}, Prezzo unit.: ${pq.prodotto.prezzoEuro} &euro;, Prezzo tot.: ${pq.prezzoTotEuro} &euro;</h5>
                <form action="Carrello" method="post">
                    <input type="hidden" name="prodId" value="${pq.prodotto.id}">
                    <input type="hidden" name="setNum" value="0">
                    <input type="submit" value="Rimuovi" style="padding: 10px;">
                </form><br>
                    <form action="Carrello" method="post">
                        <label>Modifica quantità:</label>              <!--Mostra quantità da selezionare per quel prodotto-->
                        <select name="setNum">
                            <c:forEach begin="1" end="25" varStatus="loop">
                                <option value="${loop.index}">${loop.index}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="prodId" value="${pq.prodotto.id}">
                        <input type="submit" value="Modifica quantità" style="padding: 10px;"><br>
                    </form><br>
            </div>
        </c:forEach>
        <c:if test="${empty carrello.prodotti}">
            <div col="1">Nessun prodotto nel carrello</div>
        </c:if>
    </div>
</section>
<c:if test="${not empty carrello.prodotti}">
    <section>
        <grid>
            <div col="1/3">
                <h2 style="padding: 10px;">Totale: ${carrello.prezzoTotEuro} &euro;</h2>
            </div>
            <c:if test="${utente!=null}">
            <div col="1/3">
                <form action="PostBuy" method="post">
                    <input type="submit" value="Completa acquisto" style="padding: 10px;margin:10px 0 20px 30px;">
                </form>
            </div>
            </c:if>
        </grid>
    </section>
</c:if>
<%@include file="footer.html"%>
