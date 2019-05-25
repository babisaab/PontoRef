<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Cartões</title>
    </head>
    <body>
        <h1>Pesquisa de Cartões</h1>
        <table border="1">
            <tr>
                <th>Cor</th>
                <th>Data</th>
                <th>Motivo</th>
                <c:if test="${acao != 'Only'}">
                    <th>Funcionario</th>
                    <th colspan="2">Ações</th>
                </c:if>
            </tr>
            <c:forEach items="${cartoes}" var="cartao">
                <tr>
                    <td><c:out value="${cartao.cor}" /></td>
                    <td><c:out value="${cartao.data}" /></td>
                    <td><c:out value="${cartao.motivo}" /></td>
                    <c:if test="${acao != 'Only'}">
                        <td><c:forEach items="${funcionarios}" var="funcionario"><c:if test="${cartao.funcionario.id == funcionario.id}"><c:out value="${funcionario.nomeCompleto}" /></c:if></c:forEach></td>
                        <td><a href=
                           "ManterCartaoController?acao=prepararOperacao&operacao=Editar&id=${cartao.id}">Editar</a></td>
                        <td><a href=
                           "ManterCartaoController?acao=prepararOperacao&operacao=Excluir&id=${cartao.id}">Excluir</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${acao != 'Only'}">
            <form action="ManterCartaoController?acao=prepararOperacao&operacao=Incluir" method="post">
                <input type="submit" name="btnIncluir" value="Incluir">
            </form>
        </c:if>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>
