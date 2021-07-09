<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Welcome"/>
</jsp:include>

<script>
    $(document).ready(function(){
        $("#btn").click(function(){
            $("#div1").fadeOut("slow");
        });
    });
</script>

<div class="div1" align="center" style="padding:20%;">
    <h1>e-Tech</h1>
    <h4>Il miglior e-commerce tech al mondo.</h4>
    <h4 style="font-size:small;">Per davvero.</h4><br>
    <a href="AllProducts">Scopri</a>
</div>


<%@ include file="footer.html"%>  <!--il contenuto viene incollato qui dentro, a momento di compilazione. Se cambiamo footer non si aggiorna-->
