<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Afastamento</title>
    </head>
    <body>
        <h1>${operacao} Afastamento</h1>

        <form action="ManterAfastamentoController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterAfastamento">
            <table>
                <tr>
                    <td>Data de Início:</td> 
                    <td><input type="date"  required="" name="txtInicioAfastamento" value="${afastamento.dataInicio}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Data de Término:</td> 
                        <td><input type="date" required="" name="txtTerminoAfastamento" value="${afastamento.dataFim}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Justificativa:</td> 
                        <td><input type="text" required="" name="txtJustificativaAfastamento" value="${afastamento.justificativa}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Tipo:</td> 
                        <td><input type="text" required="" name="txtTipo" value="${afastamento.tipo}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Funcionário:</td>
                        <td>
                            <select name="optFuncionario" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}" <c:if test="${afastamento.funcionario.id == funcionario.id}"> selected</c:if>>${funcionario.nomeCompleto}</option>  
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                    <td><input type="text" name="txtIdAfastamento" value="${afastamento.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="PesquisaAfastamentoController">
            <button>Voltar</button>
        </a>
    </body>
</html>