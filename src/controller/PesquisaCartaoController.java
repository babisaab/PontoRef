package controller;

import dao.CartaoDAO;
import dao.FuncionarioDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Cartao;

public class PesquisaCartaoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
      
        if (acao.equals("Only")) {
            Long id = Long.parseLong(request.getParameter("id"));
               List<Cartao> todosAfastamentos = new ArrayList<Cartao>();
            List<Cartao> afastamentos = new ArrayList<Cartao>();
            todosAfastamentos = CartaoDAO.getInstance().getAllCartoes();
            for (Cartao afastamento : todosAfastamentos) {
                if (afastamento.getfuncionario().getId() == id) {
                    afastamentos.add(afastamento);
                }
            }
            request.setAttribute("acao", acao);
            request.setAttribute("cartoes",afastamentos);
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
        } else {
            request.setAttribute("cartoes", CartaoDAO.getInstance().getAllCartoes());
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
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
