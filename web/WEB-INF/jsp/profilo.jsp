<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.nome}"/>
</jsp:include>
<section>
    <h1>Il mio Account</h1>
    <h3>Dati personali:</h3>
    <ul>
        <li><h4>Id: ${utente.id}</h4></li>
        <li><h4>Nome: ${utente.nome}</h4></li>
        <li><h4>Username: ${utente.username}</h4></li>
        <li><h4>Email: ${utente.email}</h4></li>
        <li><h4>Admin: ${utente.admin}</h4></li>
    </ul>
    <button><a href="MyOrders">I miei Ordini</a></button>



</section>
<%@include file="footer.html"%>