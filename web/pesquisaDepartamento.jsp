<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Departamentos</title>
    </head>
    <body>
        <h1>Pesquisa de Departamentos</h1>
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>descrição</th>
                <th colspan="2">Ações</th>
            </tr>
            <c:forEach items="${departamentos}" var="departamento">
                <tr>
                    <td><c:out value="${departamento.nome}" /></td>
                    <td><c:out value="${departamento.descricao}" /></td>
                    <td><a href=
                           "DepartamentoController?acao=prepararOperacao&operacao=Editar&id=${departamento.id}">Editar</a></td>
                    <td><a href=
                           "DepartamentoController?acao=prepararOperacao&operacao=Excluir&id=${departamento.id}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="DepartamentoController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>
