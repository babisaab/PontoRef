<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
 
    <meta charset="utf-8">
	
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="index.css">
    <title>Funcionários</title>
  </head>
  <body>
    <div class="topnav">
      <a class="active" href="#home">Inicio</a>
      <a>Analise de Funcionários</a>
      <a href="cadastro-cartao.html">Cartão</a>
      <a href="agendamento-ferias.html">Agendamento Férias</a>
      <a href="cadastro-cargo.html">Cargo</a>
      <a href="departamento.html">Departamento</a>

    </div>
    <div class="col-md-12">
     
      <table class="table"  style="margin-top:1em;">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Nome </th>
            <th scope="col">Quantidade de Faltas</th>
            <th scope="col">Cargo</th>
            <th scope="col">Departamento</th>
            <th scope="col">Data nascimento</th>
          </tr>
        </thead>
        <tbody>
		<c:forEach items="${funcionarios}" var="funcionario">
          <tr>
            <th scope="row"><c:out value="${funcionario.nome}"></th>
            <td>r</td>
            <td>r</td>
            <td>r</td>
			<td>r</td>
			<td><a href="ManterController?acao=prepararOperacao&operacao=Editar&codFuncio">
			<c:out value="${funcionario.id}"/> editar</a></td>
			<td>r</td>
          </tr>
         </c:forEach>
        </tbody>
      </table>
      
      <div class="row">
	  <form action="ManterController?acao=prepararOperacao&operacao=Editar&codFuncio"
          <a href="cadastro.html" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">cadastrar</a>
          <button type="button" class="btn btn-primary">Pesquisar</button>
      </div>
    </div>
          
         
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>