<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.nome}"/>
</jsp:include>

<section style="text-align: left;padding:30px;margin: 0 300px 0 300px;">
    <c:if test="${!utente.admin}">
        <h1>${categoria.nome}</h1>
    </c:if>
    <c:if test="${utente.admin}">
        <form action="AdminCategoria" method="post">
            <h1>${categoria.nome}
                <input type="hidden" name="id" value="${categoria.id}">
                <input type="submit" value="Modifica" style="padding: 10px;">
                <input type="submit" name="rimuovi" value="Rimuovi" style="padding: 10px;">
            </h1>
        </form>
    </c:if>
    <p>${categoria.descrizione}</p>
    <grid>

        <c:forEach items="${prodotti}" var="prodotto">
            <div style="padding: 15px;text-align: left;">
                <h3>
                    <a href="Prodotto?id=${prodotto.id}">${prodotto.nome}</a>
                </h3>
                <a href="Prodotto?id=${prodotto.id}"><img src="images/${prodotto.immagine}" width="230" height="230"></a>
            </div>
            <div style="text-align: left;width: 50%;">

                <p>${prodotto.descrizione}</p>
                <h4>Prezzo: ${prodotto.prezzoEuro}&euro;</h4><br>
            </div>
        </c:forEach>


        <c:if test="${empty prodotti}">
            <div col="1">Nessun prodotto è presente in questa categoria.</div>
        </c:if>

    </grid>
</section>
<%@include file="footer.html"%>



