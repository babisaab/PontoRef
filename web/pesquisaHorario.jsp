<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisa de Horários</title>
    </head>
    <body>
        <h1>Pesquisa de Horários</h1>
        <table border="1">
            <tr>
                <th>Dia da Semana</th>
                <th>Hora Início</th>
                <th>Hora Fim</th>
                <c:if test="${acao != 'Only'}">
                    <th>Funcionario</th>
                    <th colspan="2">Ações</th>
                </c:if>
            </tr>
            <c:forEach items="${horarios}" var="horario">
                <tr>
                    <td><c:out value="${horario.diaDaSemana}" /></td>
                    <td><c:out value="${horario.horaInicio}" /></td>
                    <td><c:out value="${horario.horaFim}" /></td>
                    <c:if test="${acao != 'Only'}">
                        <td><c:forEach items="${funcionarios}" var="funcionario"><c:if test="${horario.funcionario.id == funcionario.id}"><c:out value="${funcionario.nomeCompleto}" /></c:if></c:forEach></td>
                        <td><a href=
                           "ManterHorarioController?acao=prepararOperacao&operacao=Editar&id=${horario.id}">Editar</a></td>
                        <td><a href=
                           "ManterHorarioController?acao=prepararOperacao&operacao=Excluir&id=${horario.id}">Excluir</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${acao != 'Only'}">
            <form action="ManterHorarioController?acao=prepararOperacao&operacao=Incluir" method="post">
                <input type="submit" name="btnIncluir" value="Incluir">
            </form>
        </c:if>
        <a href="menu.jsp">
            <button>Voltar</button>
        </a>
    </body>
</html>