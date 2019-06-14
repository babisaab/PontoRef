<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Afastamentos</title>
    </head>
    <body>
        <h1>Pesquisa de Afastamentos</h1>
        <table border="1">
            <tr>
                <th>Data de Inicio</th>
                <th>Data de Termino</th>
                <th>Justificativa</th>
                <th>Tipo</th>
                <c:if test="${acao != 'Only'}">
                    <th>Funcionario</th>
                    <th colspan="2">Ações</th>
                </c:if>
            </tr>
            <c:forEach items="${afastamentos}" var="afastamento">
                <tr>
                    <td><c:out value="${afastamento.dataInicio}" /></td>
                    <td><c:out value="${afastamento.dataFim}" /></td>
                    <td><c:out value="${afastamento.justificativa}" /></td>
                    <td><c:out value="${afastamento.tipo}" /></td>
                    <c:if test="${acao != 'Only'}">
                        <td><c:forEach items="${funcionarios}" var="funcionario"><c:if test="${afastamento.funcionario.id == funcionario.id}"><c:out value="${funcionario.nomeCompleto}" /></c:if></c:forEach></td>
                        <td><a href=
                           "AfastamentoController?acao=prepararOperacao&operacao=Editar&id=${afastamento.id}">Editar</a></td>
                        <td><a href=
                           "AfastamentoController?acao=prepararOperacao&operacao=Excluir&id=${afastamento.id}">Excluir</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${acao != 'Only'}">
            <form action="AfastamentoController?acao=prepararOperacao&operacao=Incluir" method="post">
                <input type="submit" name="btnIncluir" value="Incluir">
            </form>
        </c:if>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>
