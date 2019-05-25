package controller;

import dao.HorarioDAO;
import dao.FuncionarioDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Horario;

public class PesquisaHorarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao.equals("Only")) {

            Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("acao", acao);
            List<Horario> todosAfastamentos = new ArrayList<Horario>();
            List<Horario> afastamentos = new ArrayList<Horario>();
            todosAfastamentos = HorarioDAO.getInstance().getAllHorarios();
            for (Horario afastamento : todosAfastamentos) {
                if (afastamento.getFuncionario().getId() == id) {
                    afastamentos.add(afastamento);
                }
            }

            request.setAttribute("acao", acao);
            request.setAttribute("horarios", afastamentos);
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
        } else {
            request.setAttribute("horarios", HorarioDAO.getInstance().getAllHorarios());
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
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
