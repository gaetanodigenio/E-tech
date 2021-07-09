<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>

<section>
<div class="products">
    <p align="center"><strong>Prodotti consigliati</strong></p>

        <c:forEach items="${prodotti}" var="prodotto">
            <div style="text-align: center;padding:10px;">
                <h3><a href="Prodotto?id=${prodotto.id}">${prodotto.nome}</a></h3>
                <a href="Prodotto?id=${prodotto.id}"><img src="images/${prodotto.immagine}" width="230" height="230"></a>
                <h4>Prezzo: ${prodotto.prezzoEuro} &euro;</h4>
            </div>
        </c:forEach>

</div>
</section>
<%@ include file="footer.html"%>








