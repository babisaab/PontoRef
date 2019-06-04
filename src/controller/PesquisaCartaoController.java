package controller;



import dao.DaoGenerico;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Cargo;
import model.Cartao;
import model.Funcionario;

public class PesquisaCartaoController extends HttpServlet {

    DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();
    DaoGenerico<Cartao> daoCartao = new DaoGenerico<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
      
        if (acao.equals("Only")) {
            Long id = Long.parseLong(request.getParameter("id"));
               List<Cartao> todosAfastamentos = new ArrayList<Cartao>();
            List<Cartao> afastamentos = new ArrayList<Cartao>();
            todosAfastamentos = daoCartao.findAll(Cartao.class);
            for (Cartao afastamento : todosAfastamentos) {
                if (afastamento.getfuncionario().getId() == id) {
                    afastamentos.add(afastamento);
                }
            }
            request.setAttribute("acao", acao);
            request.setAttribute("cartoes",afastamentos);
            request.setAttribute("funcionarios",daoFuncionario.findAll(Funcionario.class));
        } else {
            request.setAttribute("cartoes", daoCartao.findAll(Cartao.class));
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
        }
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaCartao.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
