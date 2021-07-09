<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Grazie da e-Tech"/>
</jsp:include>
<section>
    <h1>Grazie per il tuo acquisto ${utente.nome}</h1>
    <h3>Riepilogo ordine: </h3>
    <ul>
        <li>Data acquisto: ${ordine.dataOrdine}</li>
        <li>Totale ordine: ${ordine.totaleOrdine}</li>
    </ul>
    <button><a href="AllProducts">Torna alla home</a></button>



</section>
<%@ include file="footer.html"%>