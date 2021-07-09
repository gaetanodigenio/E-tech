<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore ${requestScope['javax.servlet.error.']}"/>
</jsp:include>
<section>
    <h1>Errore ${requestScope['javax.servlet.error.exception']}</h1>
</section>
<%@ include file="footer.html"%>
