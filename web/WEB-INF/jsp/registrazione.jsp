<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrazione"/>
</jsp:include>

<section style="margin-left: 20px;">
    <h1 style="text-align: left;margin:20px 0px 20px 10px;">Registrazione</h1><br>
    <form name="registrazione" action="RegistraUtente" method="post">
        <label><strong>Username(minimo 5 caratteri, solo lettere e numeri):</strong></label>
        <input type="text" name="username" oninput="validaUsername()"><br><br>
        <label><strong>Password (almeno 8 caratteri, deve contenere: una lettera maiuscola, una minuscola e un numero):</strong></label>
        <input type="password" name="password" oninput="validaPassword()"><br><br>
        <label><strong>Conferma password</strong></label>
        <input type="password" name="passwordconferma" oninput="validaPassword()"><br><br>
        <label><strong>Nome (no numeri)</strong></label>
        <input type="text" name="nome" oninput="validaNome()"><br><br>
        <label><strong>Email:</strong></label>
        <input type="text" name="email" oninput="validaEmail()"><br><br>
        <input id="registra" type="submit" value="Registra" disabled style="padding: 10px;margin-bottom: 10px;"><span id="registramimessaggio"></span>
    </form>
</section>
<script>
    var ok='green';
    var no='red';
    var usernameOk=null;
    var passwordOk=null;
    var nomeOk=null;
    var emailOk=null;



    function validaUsername(){
        var input=document.forms['registrazione']['username'];
        if(input.value.length >=5 && input.value.match(/^[0-9a-zA-Z]+$/)){
            var xmlHttpRequest=new XMLHttpRequest();
            xmlHttpRequest.onreadystatechange=function(){
                if(this.readyState==4 && this.status==200 && this.responseText=='<ok/>'){
                    usernameOk=true;
                    input.style.color=ok;
                }else{
                    input.style.color=no;
                    usernameOk=false;
                }
                cambiaStatoRegistra();
    }
            xmlHttpRequest.open("GET","VerificaUsername?username="+encodeURIComponent(input.value),true);
            xmlHttpRequest.send();

        }else{
            input.style.color=no;
            usernameOk=false;
            cambiaStatoRegistra();
        }
    }



    function validaPassword(){
        var input=document.forms['registrazione']['password'];
        var inputconf=document.forms['registrazione']['passwordconferma'];
        var password=inputconf.value;
        if(password.length>=8 && password.toUpperCase()!=password && password.toLowerCase() !=password && /[0-9]/.test(password)){
            input.style.color=ok;
            if(password==inputconf.value){
                inputconf.style.color=ok;
                passwordOk=true;
            }else{
            inputconf.style.color=no;
            passwordOk=false;
        }
        }else{
        input.style.color=no;
        inputconf.style.color=no;
        passwordOk=false;
    }
    cambiaStatoRegistra();
}

    function validaNome(){
        var input=document.forms['registrazione']['nome'];
        if(input.value.trim().length>0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)){
            input.style.color=ok;
            nomeOk=true;
        }else{
            input.style.color=no;
            nomeOk=false;
        }
        cambiaStatoRegistra();
    }


    function validaEmail(){
        var input=document.forms['registrazione']['email'];
        if(input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
            input.style.color=ok;
            emailOk=true;
        }else{
            input.style.color=no;
            emailOk=false;
        }
        cambiaStatoRegistra();
    }


    function cambiaStatoRegistra() {
        if(usernameOk && passwordOk && nomeOk && emailOk){
            document.getElementById('registra').disabled=false;
            document.getElementById('registramimessaggio').innerHTML='';
        }else{
            document.getElementById('registra').disabled=true;
            document.getElementById('registramimessaggio').innerHTML="Verifica che tutti i campi siano inseriti correttamente";
        }

    }

</script>

<%@include file="footer.html"%>