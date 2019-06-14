<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Cartão</title>
    </head>
    <body>
        <h1>${operacao} Cartão</h1>

        <form action="CartaoController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterCartao">
            <table>

                <tr> 
                    <td>Tipo do Cartão:</td> 
                    <td>
                        <select name="optTipoCartao" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <option value="Assiduidade" <c:if test="${cartao.tipo == 'Assiduidade'}"> selected</c:if>>Assiduidade</option>
                            <option value="Outro" <c:if test="${cartao.tipo == 'Outro'}"> selected</c:if>>Outro</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Cor do Cartão:</td> 
                        <td>
                            <select  required="" name="optCorCartao" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <option value="Azul" <c:if test="${cartao.cor == 'Azul'}"> selected</c:if>>Azul</option>
                            <option value="Amarelo" <c:if test="${cartao.cor == 'Amarelo'}"> selected</c:if>>Amarelo</option>
                            <option value="Laranja" <c:if test="${cartao.cor == 'Laranja'}"> selected</c:if>>Laranja</option>
                            <option value="Vermelho" <c:if test="${cartao.cor == 'Vermelho'}"> selected</c:if>>Vermelho</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Motivo do Cartão:</td> 
                        <td><input type="text" name="txtMotivoCartao" required="" value="${cartao.motivo}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Data do Ocorrido:</td> 
                        <td><input type="date" name="txtDataCartao" required="" value="${cartao.data}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Funcionário:</td>
                        <td>
                            <select name="optFuncionario" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${funcionarios}" var="funcionario">
                                <option value="${funcionario.id}" <c:if test="${cartao.funcionario.id == funcionario.id}"> selected</c:if>>${funcionario.nomeCompleto}</option>  
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                    <td><input type="text" name="txtIdCartao" value="${cartao.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="CartaoController?acao=All">
            <button>Voltar</button>
        </a>
    </body>
</html>