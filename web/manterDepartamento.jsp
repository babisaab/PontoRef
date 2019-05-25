<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Departamento</title>
    </head>
    <body>
        <h1>${operacao} Departamento</h1>

        <form action="ManterDepartamentoController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterDepartamento">
            <table>

                <tr>
                    <td>Nome do Departamento:</td> 
                    <td><input type="text"  required="" name="txtNome" value="${departamento.nome}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Descricao do Departamento:</td>
                        <td><input type="text" required="" name="txtDescricao" value="${departamento.descricao}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                        <td><input type="text" name="txtIdDepartamento" value="${departamento.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="PesquisaDepartamentoController">
            <button>Voltar</button>
        </a>
    </body>
</html>
