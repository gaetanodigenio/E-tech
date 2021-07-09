<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.nome}"/>
</jsp:include>
<section style="margin-left: 20px;">
    <h1>Account registrati</h1>

    <c:forEach items="${utenti}" var="account">
    <h3>Dati personali:</h3>
    <ul style="margin-bottom: 30px;">
        <li><h4>Id: ${account.id}</h4></li>
        <li><h4>Nome: ${account.nome}</h4></li>
        <li><h4>Username: ${account.username}</h4></li>
        <li><h4>Email: ${account.email}</h4></li>
        <li><h4>Admin: ${account.admin}</h4></li>
    </ul>
    </c:forEach>
    <button style="padding: 10px;margin-bottom: 10px;"> <a href="MyOrders">Ordini</a></button>



</section>
<%@include file="footer.html"%>