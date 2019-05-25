<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Cargo</title>
    </head>
    <body>
        <h1>${operacao} Cargo</h1>

        <form action="ManterCargoController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterCargo">
            <table>

                <tr>
                    <td>Nome do Cargo:</td> 
                    <td><input type="text" name="txtNomeCargo"  required="" value="${cargo.nome}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Carga Horaria Obrigatoria do Cargo:</td> 
                        <td><input type="text" required="" name="txtCargaHorariaObrigatoria" value="${cargo.cargaHorariaObrigatoria}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                                    </tr>
                <tr>
                    <td>Departamento:</td>
                    <td>
                        <select name="optDepartamento" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${departamentos}" var="departamento">
                                <option value="${departamento.id}" <c:if test="${cargo.departamento.id == departamento.id}"> selected</c:if>>${departamento.nome}</option>  
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                    <tr>
                        <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                        <td><input type="text" name="txtIdCargo" value="${cargo.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
            </table>
        </form>
        <a href="PesquisaCargoController">
            <button>Voltar</button>
        </a>
    </body>
</html>
