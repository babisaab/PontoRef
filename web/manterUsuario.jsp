<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Usuario</title>
    </head>
    <body>
        <h1>${operacao} Usuario</h1>

        <form action="UsuarioController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterUsuario">
            <table>
      
                <tr>
                    <td>Login do Usuario:</td> 
                    <td><input type="text" name="txtLoginUsuario" value="${usuario.login}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                </tr>
                <tr>
                    <td>Senha do Usuario:</td> 
                    <td><input type="text" name="txtSenhaUsuario" value="${usuario.senha}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                </tr>
                <tr>
                    <td>Funcionário:</td>
                    <td>
                        <select name="optFuncionario" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}" <c:if test="${usuario.funcionario.id == funcionario.id}"> selected</c:if>>${funcionario.nomeCompleto}</option>  
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                                            <td><input type="text" name="txtIdUsuario" value="${usuario.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="UsuarioController?acao=All">
            <button>Voltar</button>
        </a>
    </body>
</html>
