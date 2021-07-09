<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>e-Tech - ${param.pageTitle}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="css/cssnuovo.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<nav>
    <ul>
        <div class="items">
            <li><a href="AllProducts">Home</a></li>
            <li><a style="background: orangered;">Categorie</a></li>
            <div class="menu">
                <c:forEach items="${categorie}" var="categoria">
                    <div class="menuitem">
                        <a href="Categoria?id=${categoria.id}">${categoria.nome}</a>
                    </div>
                </c:forEach>
            </div>
            <li><a href="Carrello">Carrello</a></li>

            <c:choose>
                <c:when test="${utente == null}">
                    <a style="background: orangered;height: 25px;margin-right: 5px;">Login</a>
                    <div class="loginform">
                            <card>
                                <form action="Login" method="post">
                                    <input type="text" name="username" placeholder="Username"><br>
                                    <input type="password" name="password" placeholder="Password"><br>
                                    <input type="submit" value="Login" style="padding: 5px;width: 200px;">
                                </form>
                            </card>
                        <div class="menuitem"><a href="Registrazione">Registrazione</a></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <a>${utente.admin ? 'Admin' : 'Account'}</a><div class="menu">
                    <c:if test="${utente.admin}">
                        <div class="menuitem"><a href="AdminCategoria">Aggiungi Categoria</a></div>
                        <div class="menuitem"><a href="AdminProdotto">Aggiungi Prodotto</a></div>
                        <div class="menuitem"><a href="MyOrders">Ordini</a></div>
                        <div class="menuitem"><a href="AdminUtenti">Utenti</a></div>
                        <hr style="margin:0px;">
                    </c:if>
                    <p style="color:red;">${utente.nome}</p>
                    <div class="menuitem"><a href="Profilo">Profilo</a></div>
                    <div class="menuitem"><a href="MyOrders">I miei ordini</a></div>
                    <div class="menuitem">
                        <card>
                            <form action="Logout">
                                <input type="submit" value="Logout" style="padding: 10px;">
                            </form>
                        </card>
                    </div>
                </div>
                </c:otherwise>
            </c:choose>
        </div>
        <li class="search-icon" style="float: right;">
            <form action="Ricerca" method="get">
                <input type="text" name="ricercatxt"  placeholder="Ricerca"  value="${param.q}">
                <button type="submit"><i class="fa fa-search"></i></button>
            </form>
        </li>

    </ul>
</nav>







<script>
    $('nav ul li.btn span').click(function(){
        $('nav ul div.items').toggleClass("show");
        $('nav ul li.btn span').toggleClass("show");
    });
</script>

