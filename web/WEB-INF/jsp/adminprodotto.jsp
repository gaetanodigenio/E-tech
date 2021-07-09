<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (prodotto == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${operazione} prodotto"/>
</jsp:include>

<section style="margin-left: 20px;">
    <h1>${operazione} prodotto</h1><br><br>
    <h5>${notifica}</h5>
    <c:if test="${param.rimuovi == null}">

        <form action="AdminProdotto" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${prodotto.id}">
            <label><strong>Categorie:</strong></label>
            <c:forEach items="${categorie}" var="categoria">
                <input type="checkbox" name="categorie" value="${categoria.id}" <c:if test="${prodotto.categorie.stream().anyMatch(c -> c.id == categoria.id).orElse(false)}">checked</c:if>><label>${categoria.nome}</label>
            </c:forEach>


            <br><br><label><strong>Nome:</strong></label>
            <input type="text" name="nome" value="${prodotto.nome}" required/><br><br>
            <label><strong>Descrizione:</strong></label>
            <textarea name="descrizione">${prodotto.descrizione}</textarea><br><br>
            <label><strong>Prezzo (cent):</strong></label>
            <input type="number" name="prezzoCent" value="${prodotto.prezzoCentesimi}"/><br><br>
            <label><strong>Immagine:</strong></label>

            File da caricare: <input type="file" name="file"/><br>

            <input type="submit" value="${operazione}" style="padding: 10px;margin: 20px 0 20px 20px;"/>

            <c:if test="${prodotto != null}">
                <input type="submit" name="rimuovi" value="Rimuovi" style="padding: 10px;margin: 20px 0 20px 20px;">
            </c:if>
        </form>

    </c:if>
</section>

<%@include file="footer.html"%>
