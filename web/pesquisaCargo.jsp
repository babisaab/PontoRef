<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Cargos</title>
    </head>
    <body>
        <h1>Pesquisa de Cargos</h1>
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Carga Horária Obrigatória</th>
                <th colspan="2">Ações</th>
            </tr>
            <c:forEach items="${cargos}" var="cargo">
                <tr>
                    <td><c:out value="${cargo.nome}" /></td>
                    <td><c:out value="${cargo.cargaHorariaObrigatoria}" /></td>
                    <td><a href=
                           "CargoController?acao=prepararOperacao&operacao=Editar&id=${cargo.id}">Editar</a></td>
                    <td><a href=
                           "CargoController?acao=prepararOperacao&operacao=Excluir&id=${cargo.id}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="CargoController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>

