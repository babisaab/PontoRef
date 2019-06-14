<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Contatos</title>
    </head>
    <body>
        <h1>Pesquisa de Contatos</h1>
        <table border="1">
            <tr>
                <th>Telefone</th>
                <th>Funcionario</th>
                <th colspan="2">Ações</th>
            </tr>
            <c:forEach items="${contatos}" var="contato">
                <tr>
                    <td><c:out value="${contato.telefone}" /></td>
                    <td><c:forEach items="${funcionarios}" var="funcionario"><c:if test="${contato.funcionario.id == funcionario.id}"><c:out value="${funcionario.nomeCompleto}" /></c:if></c:forEach></td>
                    <td><a href=
                           "ContatoController?acao=prepararOperacao&operacao=Editar&id=${contato.id}">Editar</a></td>
                    <td><a href=
                           "ContatoController?acao=prepararOperacao&operacao=Excluir&id=${contato.id}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="ContatoController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>

