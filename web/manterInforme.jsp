<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Informe</title>
    </head>
    <body>
        <h1>${operacao} Informe</h1>

        <form action="ManterInformeController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterInforme">
            <table>

                <tr>
                    <td>Data do ocorrido do Informe:</td> 
                    <td><input type="date" name="txtDataInforme" value="${informe.dataOcorrido}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Motivo do Informe</td> 
                        <td><input type="text" name="txtMotivoInforme" value="${informe.motivo}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Funcionário:</td>
                        <td>
                            <select name="optFuncionario" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}" <c:if test="${informe.funcionario.id == funcionario.id}"> selected</c:if>>${funcionario.nomeCompleto}</option>  
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                    <td><input type="text" name="txtIdInforme" value="${informe.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="PesquisaInformeController">
            <button>Voltar</button>
        </a>
    </body>
</html>