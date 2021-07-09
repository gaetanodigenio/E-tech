<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Ordini personali"/>
</jsp:include>
<section style="margin-left: 15px;list-style-type: none">
    <p>Ordini</p>
    <c:if test="${empty ordini}">
        <p>Nessun ordine effettuato dall'utente.</p>
    </c:if>
    <ul>
    <c:forEach items="${ordini}" var="ordine">
        <ol style="list-style-type: none">
            <li><strong>Ordine n. ${ordine.idOrdine}</strong></li>
            <li>Id utente ${ordine.idUtente}</li>
            <li>Nome utente ${ordine.nomeUtente}</li>
            <li>Eseguito in data ${ordine.dataOrdine}</li>
            <li>Prodotti acquistati: ${ordine.listaProdotti}</li>
            <li>Quantità ${ordine.numeroProdotti}</li>
            <li>Totale ${ordine.totaleOrdine}</li>
        </ol></br>
    </c:forEach>
    </ul>

</section>
<%@include file="footer.html"%>