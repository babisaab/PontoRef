<%-- 
    Document   : report
    Created on : 28/04/2019, 12:58:31
    Author     : Babi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatório</title>
    </head>
    <body>

        <h>Relatório de Afastamentos por funcionário</h>
        <form action="RelatorioGenericController?acao=Afastamento.jsp" method="post">
            Escolha um funcionário
            <input type="text" name="paramFunc"/><br/>
            <input type="submit"/>
        </form>

    </body>
</html>
