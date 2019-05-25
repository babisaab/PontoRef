<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Contato</title>
    </head>
    <body>
        <h1>${operacao} Contato</h1>

        <form action="ManterContatoController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterContato">
            <table>

                <tr>
                    <td>Telefone:</td>
                    <td><input type="text" required="" name="txtTelefone" value="${contato.telefone}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Funcionário:</td>
                        <td>
                            <select name="optFuncionario" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}" <c:if test="${contato.funcionario.id == funcionario.id}"> selected</c:if>>${funcionario.nomeCompleto}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                    <td><input type="text" name="txtIdContato" value="${contato.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="PesquisaContatoController">
            <button>Voltar</button>
        </a>
    </body>
</html>