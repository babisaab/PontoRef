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
import model.Funcionario;
import model.Horario;

public class PesquisaHorarioController extends HttpServlet {

        DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();
                DaoGenerico<Horario> daoHorario = new DaoGenerico<>();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao.equals("Only")) {

            Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("acao", acao);
            List<Horario> todosHorarios = new ArrayList<Horario>();
            List<Horario> horarios = new ArrayList<Horario>();
            todosHorarios = daoHorario.findAll(Horario.class);
            for (Horario horario : todosHorarios) {
                if (horario.getFuncionario().getId() == id) {
                    horarios.add(horario);
                }
            }

            request.setAttribute("acao", acao);
            request.setAttribute("horarios", horarios);
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
        } else {
            request.setAttribute("horarios", daoHorario.findAll(Horario.class));
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
        }
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaHorario.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
