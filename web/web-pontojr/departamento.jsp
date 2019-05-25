<!doctype html>
<html lang="pt-br">
  <head>
 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="cadastro.css">
    <title>Funcionários</title>
  </head>
  <body>
    <div class="topnav">
      <a class="active" href="#home">Inicio</a>
      <a href="index.html">Analise de Funcionários</a>
      <a href="cadastro-cartao.html">Cartão</a>
      <a href="agendamento-ferias.html">Agendamento Férias</a>
      <a href="cadastro-cargo.html">Cargo</a>
      <a href="departamento.html">Departamento</a>

    </div>
    <div class="col-md-12">
            <form>
                    <div class="form-group">
                      <label for="exampleInputEmail1">Nome do departamento</label>
                      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" >
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Cargo</label>
                      <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                             Cargo
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href="#">analista</a>
                              <a class="dropdown-item" href="#">gerente</a>
                            </div>
                          </div>                    
                        </div>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                  </form>
    </div>
         
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>