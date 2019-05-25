<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="pt-br">
    <head>

        <title> Menu</title>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
    </head>
    <body>


        <div class="container">
            <title>${f.nomeCompleto} Funcionario</title>
            <h1>${dia}</h1>
            <ul>
                <title>${f.nomeCompleto} Funcionario</title>

                <li><a href="PesquisaCargoController"><span title="Cargo">Cargo</span></a></li>
                <li><a href="PesquisaCartaoController?acao=All"><span title="Cartão">Cartão</span></a></li>
                <li><a href="PesquisaAfastamentoController?acao=All"><span title="Afastamento">Afastamento</span></a></li>
                <li><a href="PesquisaContatoController"><span title="Contato">Contato</span></a></li>
                <li><a href="PesquisaDepartamentoController"><span title="Departamento">Departamento</span></a></li>
                <li><a href="PesquisaFuncionarioController"><span title="Funcionario">Funcionário</span></a></li>
                <li><a href="PesquisaHorarioController?acao=All"><span title="Horario">Horário</span></a></li>
                <li><a href="PesquisaInformeController?acao=All"><span title="Informe">Informe</span></a></li>
                <li><a href="PesquisaUsuarioController"><span title="Usuario">Usuário</span></a></li>

                <li><a href="report.jsp"><span title="Relatorio">Relatórios</span></a></li>
            </ul>

            <ul>
                <li><a href="PesquisaAfastamentoController?acao=Only&id=${f.id}"><span title="Seus Afastamentos">Seus Afastamentos</span></a></li>
                <li><a href="PesquisaCartaoController?acao=Only&id=${f.id}"><span title="Seus Cartões">Seus Cartões</span></a></li>
                <li><a href="PesquisaHorarioController?acao=Only&id=${f.id}"><span title="Seus Horários">Seus Horarios</span></a></li>
                <li><a href="PesquisaInformeController?acao=Only&id=${f.id}"><span title="Seus Informes">Seus Informes</span></a></li>
                <!------ <li><a href="PontoController?acao=Entrada&id=${idFuncionario}"><span title="Marcar Entrada">Marcar Entrada</span></a></li> ---------->
               <!------ <li><a href="PontoController?acao=Saida&id=${idFuncionario}"><span title="Marcar Saida">Marcar Saida</span></a></li> ---------->
            </ul>

            <td><input type="text" name="txtIdFuncionario" value="${idFuncionario}" hidden="" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>


        </div>
    </body>
</html>

