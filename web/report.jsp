<%-- 
    Document   : report
    Created on : 28/04/2019, 12:58:31
    Author     : Babi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatório</title>
    </head>
    <body>
        <h>Relatório de Informes por funcionário</h>
        <form action="RelatorioInformeParamController" method="post">
            Escolha um funcionário
            <input type="text" name="paramFunc"/><br/> 
            <input type="submit"/>
        </form>
        
        
        <h>Relatório de Horários por funcionário</h>
        <form action="RelatorioHorarioParamController" method="post">
            Escolha um funcionário
            <input type="text" name="paramFunc"/><br/>
            <input type="submit"/>
        </form>
        
        
        <h>Relatório de Afastamentos por funcionário</h>
        <form action="RelatorioAfastamentoParamController" method="post">
            Escolha um funcionário
            <input type="text" name="paramFunc"/><br/>
            <input type="submit"/>
        </form>
        
        
        <h>Relatório de Cartões por funcionário</h>
        <form action="RelatorioCartaoParamController" method="post">
            Escolha um funcionário
            <input type="text" name="paramFunc"/><br/>
            <input type="submit"/>
        </form>
        
        
        <h>Relatório de Funcionários</h>
        <form action="RelatorioFuncParamController" method="post">
            Escolha um funcionário
            <input type="text" name="paramFunc"/><br/>
            <input type="submit"/>
        </form>
        
        
        
        <ul> 
        <li><a href="RelatorioAfastFuncController"><span title="Relatorio">Relatório de Afastamentos</span></a></li>
        <li><a href="RelatorioCargDeptController"><span title="Relatorio">Relatório de Cargos</span></a></li>
        <li><a href="RelatorioCargFuncController"><span title="Relatorio">Relatório de Departamentos</span></a></li>
        <li><a href="RelatorioCartaoController"><span title="Relatorio">Relatório de Cartões</span></a></li>
        <li><a href="RelatorioInformeController"><span title="Relatorio">Relatório de Informes</span></a></li>
        <li><a href="RelatorioHorarioController"><span title="Relatorio">Relatório de Horários</span></a></li>
        <li><a href="RelatorioCfuncionarioController"><span title="Relatorio">Relatório de Funcionários</span></a></li>
        </ul>
    </body>
</html>
