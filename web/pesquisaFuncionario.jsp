<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Funcionário</title>
    </head>
    <body>
        <h1>Pesquisa de Funcionários</h1>
        <table border="1">
            <tr>
                <th>Nome Completo</th>
                <th>CPF</th>
                <th>Data de Nascimento</th>
                <th>Matrícula</th>
                <th>Data de Admissão</th>
                <th>E-mail</th>
                <th colspan="2">Ações</th>
            </tr>
            <c:forEach items="${funcionarios}" var="funcionario">
                <tr>
                    <td><c:out value="${funcionario.nomeCompleto}" /></td>
                    <td><c:out value="${funcionario.cpf}" /></td>
                    <td><c:out value="${funcionario.dataNascimento}" /></td>
                    <td><c:out value="${funcionario.matricula}" /></td>
                    <td><c:out value="${funcionario.dataAdmissao}" /></td>
                    <td><c:out value="${funcionario.email}" /></td>
                    <td><a href=
                           "FuncionarioController?acao=prepararOperacao&operacao=Editar&id=${funcionario.id}">Editar</a></td>
                    <td><a href=
                           "FuncionarioController?acao=prepararOperacao&operacao=Excluir&id=${funcionario.id}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
        <form action="FuncionarioController?acao=prepararOperacao&operacao=Incluir" method="post">
            <input type="submit" name="btnIncluir" value="Incluir">
        </form>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>
