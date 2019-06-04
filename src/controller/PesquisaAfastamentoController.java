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
import model.Afastamento;
import model.Funcionario;

public class PesquisaAfastamentoController extends HttpServlet {
    DaoGenerico<Afastamento> daoAfastamento = new DaoGenerico<>();
        DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao.equals("Only")) {
            Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("acao", acao);
            List<Afastamento> todosAfastamentos = new ArrayList<Afastamento>();
            List<Afastamento> afastamentos = new ArrayList<Afastamento>();
            todosAfastamentos = daoAfastamento.findAll(Afastamento.class);
            for (Afastamento afastamento : todosAfastamentos) {
                if (afastamento.getFuncionario().getId() == id) {
                    afastamentos.add(afastamento);
                }
            }
            request.setAttribute("afastamentos", afastamentos);
            request.setAttribute("funcionario", daoFuncionario.findById(Funcionario.class, id));
        } else {
            request.setAttribute("afastamentos", daoAfastamento.findAll(Afastamento.class));
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
        }
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaAfastamento.jsp");
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
