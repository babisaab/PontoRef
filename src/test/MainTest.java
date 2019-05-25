package test;

import model.*;
import dao.*;

public class MainTest {
    public static void main(String[] args) {
        Departamento dep = new Departamento("Finanças", "Departamento responsável pelas finanças");
        Cargo cargo = new Cargo("Presidente", "20 horas", dep);
        Funcionario fun = new Funcionario("João Vitor Pureza", "018.792.506-24", "MG-15.868.173", "Masculino", "23/05/1997", "36021-540", "Rua Dr. José Procópio Teixeira", "427", "apto 204", "Bom Pastor", "Juiz de Fora", "MG", "17011602", "BSI", "10/03/2019", "joão@pureza.com", cargo);
        Usuario usu = new Usuario("17011602", "123456", fun);
        Contato contatinho = new Contato("3232-3232", fun);
        Afastamento a1 = new Afastamento("19/03/2019", "22/03/2019", "Virose", "Doente", fun);
        Afastamento a2 = new Afastamento("09/04/2019", "10/04/2019", "Febre", "Doente", fun);
        Cartao card = new Cartao("Falta", "Vermelho", "Não compareceu ao trabalho", "04/04/2019", fun);
        Informe info = new Informe("26/03/2019", "Comendo em local indevido", fun);
        Horario h1 = new Horario("Segunda-feira", "13:00", "15:00", fun);
        Horario h2 = new Horario("Terça-feira", "13:00", "15:00", fun);
        Horario h3 = new Horario("Quarta-feira", "13:00", "15:00", fun);
        Horario h4 = new Horario("Quinta-feira", "13:00", "15:00", fun);
        Horario h5 = new Horario("Sexta-feira", "13:00", "15:00", fun);
        Ponto p1 = new Ponto("13:00", "15:00", fun, h1);
        Ponto p2 = new Ponto("13:00", "15:00", fun, h2);
        Ponto p3 = new Ponto("13:00", "15:00", fun, h3);
        Ponto p4 = new Ponto("13:00", "15:00", fun, h4);
        Ponto p5 = new Ponto("13:00", "15:00", fun, h5);
        
        DepartamentoDAO.getInstance().salvar(dep);
        CargoDAO.getInstance().salvar(cargo);
        FuncionarioDAO.getInstance().salvar(fun);
        UsuarioDAO.getInstance().salvar(usu);
        ContatoDAO.getInstance().salvar(contatinho);
        AfastamentoDAO.getInstance().salvar(a1);
        AfastamentoDAO.getInstance().salvar(a2);
        CartaoDAO.getInstance().salvar(card);
        InformeDAO.getInstance().salvar(info);
        HorarioDAO.getInstance().salvar(h1);
        HorarioDAO.getInstance().salvar(h2);
        HorarioDAO.getInstance().salvar(h3);
        HorarioDAO.getInstance().salvar(h4);
        HorarioDAO.getInstance().salvar(h5);
        PontoDAO.getInstance().salvar(p1);
        PontoDAO.getInstance().salvar(p2);
        PontoDAO.getInstance().salvar(p3);
        PontoDAO.getInstance().salvar(p4);
        PontoDAO.getInstance().salvar(p5);
    }
}
