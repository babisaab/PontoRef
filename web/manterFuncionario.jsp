<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${operacao} Funcionario</title>
    </head>
    <body>
        <h1>${operacao} Funcionario</h1>

        <form action="FuncionarioController?acao=confirmarOperacao&operacao=${operacao}" method="post" name="frmManterFuncionario">
            <table>

                <tr>
                    <td>Nome Completo:</td>
                    <td><input type="text" name="txtNomeCompleto" required="" value="${funcionario.nomeCompleto}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>CPF:</td>
                        <td><input type="text" name="txtCPF" required="" value="${funcionario.cpf}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>RG:</td>
                        <td><input type="text" name="txtRG" required=""  value="${funcionario.rg}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Sexo:</td>
                        <td>
                            <select name="optSexo" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <option value="Masculino" <c:if test="${funcionario.sexo == 'Masculino'}"> selected</c:if>>Masculino</option>
                            <option value="Feminino" <c:if test="${funcionario.sexo == 'Feminino'}"> selected</c:if>>Feminino</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Data de Nascimento:</td>
                        <td><input type="text" name="txtNascimento" required="" value="${funcionario.dataNascimento}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>CEP:</td>
                        <td><input type="text" name="txtCEP" required="" value="${funcionario.cep}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Logradouro:</td>
                        <td><input type="text" name="txtLogradouro"  required="" value="${funcionario.logradouro}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Número:</td>
                        <td><input type="text" name="txtNumero" required=""  value="${funcionario.numero}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Complemento:</td>
                        <td><input type="text" name="txtComplemento" required="" value="${funcionario.complemento}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Bairro:</td>
                        <td><input type="text" name="txtBairro" required="" value="${funcionario.bairro}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Cidade:</td>
                        <td><input type="text" name="txtCidade" required=""  value="${funcionario.cidade}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>UF:</td>
                        <td><input type="text" name="txtUf" required=""  value="${funcionario.uf}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Matrícula:</td>
                        <td><input type="text" name="txtMatricula"  required="" value="${funcionario.matricula}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Curso:</td>
                        <td>
                            <select name="optCurso" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <option value="BSI" <c:if test="${funcionario.sexo == 'BSI'}"> selected</c:if>>BSI</option>
                            <option value="Mecatronica" <c:if test="${funcionario.sexo == 'Mecatronica'}"> selected</c:if>>Engenharia Mecatrônica</option>
                            <option value="Metalurgica" <c:if test="${funcionario.sexo == 'Metalugica'}"> selected</c:if>>Engenharia Metalúgica</option>
                            <option value="Fisica" <c:if test="${funcionario.sexo == 'Fisica'}"> selected</c:if>>Licenciatura em Física</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Data de Admissão:</td>
                        <td><input type="text" name="txtAdmissao" required="" value="${funcionario.dataAdmissao}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>E-mail:</td>
                        <td><input type="text" name="txtEmail" required="" value="${funcionario.email}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>
                    </tr>
                    <tr>
                        <td>Cargo:</td>
                        <td>
                            <select name="optCargo" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                            <c:forEach items="${cargos}" var="cargo">
                                <option value="${cargo.id}" <c:if test="${funcionario.cargo.id == cargo.id}"> selected</c:if>>${cargo.nome}</option>  
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                    <tr>
                        <td><input type="submit" name="btnConfirmar" value="Confirmar"></td>
                        <td><input type="text" name="txtIdFuncionario" value="${funcionario.id}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                </tr>
            </table>
        </form>
        <a href="FuncionarioController?acao=All">
            <button>Voltar</button>
        </a>
    </body>
</html>
