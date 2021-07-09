<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (categoria == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Categoria Admin"/>
</jsp:include>
<section style="margin-left: 10px;">
    <h1 style="margin:20px 30px 30px 30px;">${operazione} categoria</h1>
    <h5>${notifica}</h5>
    <c:if test="${param.rimuovi == null}">
        <form action="AdminCategoria" method="post">
            <input type="hidden" name="id" value="${categoria.id}">
            <label><strong>Nome:</strong></label>
            <input type="text" name="nome" value="${categoria.nome}" required><br><br>
            <label style="margin-top: 10px;"><strong>Descrizione: </strong></label>
            <textarea name="descrizione">${categoria.descrizione}</textarea><br>
            <input type="submit" value="${operazione}" style="padding: 10px;margin-left: 500px;margin-bottom: 50px; margin-top: 50px;">
            <c:if test="${categoria != null}">
                <input type="submit" name="rimuovi" value="Rimuovi" style="padding: 10px;">
            </c:if>
        </form>
    </c:if>
</section>

<%@include file="footer.html"%>
