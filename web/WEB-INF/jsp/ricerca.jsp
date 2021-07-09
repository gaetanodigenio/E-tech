<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Ricerca"/>
</jsp:include>
<section style="margin-left: 20px;">
    <div>
        <c:forEach items="${prodotti}" var="prodotto">
            <h3 style="text-align: left;">
                <a href="Prodotto?id=${prodotto.id}">${prodotto.nome}</a>
            </h3><br>
            <div style="padding: 10px;text-align: left;margin-left: 20px;">
                <a href="Prodotto?id=${prodotto.id}"><img src="images/${prodotto.id}.jpg" width="250px" height="250px"></a>
            </div>
            <div>

            <p style="text-align: center;width: 50%;">${prodotto.descrizione}</p>
            <h4>Prezzo:${prodotto.prezzoEuro}&euro;</h4><br><br>
            </div>
        </c:forEach>
        <c:if test="${empty prodotti}">
            <div col="1">La ricerca non ha prodotto nessun risultato.</div>
        </c:if>
    </div>
</section>
<%@include file="footer.html"%>