<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Horario</title>
    </head>
    <body>
        <h1>${operacao} Horario</h1>

        <form action="HorarioController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterHorario">
            <table>
           
                <tr>
                    <td>Dia da Semana:</td> 
                    <td><input type="text" name="txtDiaDaSemanaHorario" value="${horario.diaDaSemana}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                </tr>
                <tr>
                    <td>Hora Inicio:</td> 
                    <td><input type="text" name="txtHoraInicioHorario" value="${horario.horaInicio}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                </tr>
                <tr>
                    <td>Hora Fim:</td> 
                    <td><input type="text" name="txtHoraFimHorario" value="${horario.horaFim}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                </tr>
                <tr>
                    <td>Funcionário:</td>
                    <td>
                        <select name="optFuncionario" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}" <c:if test="${horario.funcionario.id == funcionario.id}"> selected</c:if>>${funcionario.nomeCompleto}</option>  
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                                            <td><input type="text" name="txtIdHorario" value="${horario.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="HorarioController?acao=All">
            <button>Voltar</button>
        </a>
    </body>
</html>