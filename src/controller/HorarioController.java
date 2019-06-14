package controller;

import dao.DaoGenerico;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Horario;

public class HorarioController extends HttpServlet {

    DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();
    DaoGenerico<Horario> daoHorario = new DaoGenerico<>();

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            } else {
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
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
        String operacao = request.getParameter("operacao");
        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdHorario").trim());
                daoHorario.remove(Horario.class, id);
            } else {
                String dia_da_semana = request.getParameter("txtDiaDaSemanaHorario");
                String hora_inicio = request.getParameter("txtHoraInicioHorario");
                String hora_fim = request.getParameter("txtHoraFimHorario");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = daoFuncionario.findById(Funcionario.class, idFuncionario);
                Horario horario = new Horario(dia_da_semana, hora_inicio, hora_fim, funcionario);
                if (operacao.equals("Incluir")) {
                    daoHorario.saveOrUpdate(horario);
                } else {
                    if (operacao.equals("Editar")) {
                        Long id = Long.parseLong(request.getParameter("txtIdHorario").trim());

                        horario.setId(id);
                        daoHorario.saveOrUpdate(horario);
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("HorarioController?acao=All");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id").trim());
                Horario horario = daoHorario.findById(Horario.class, id);
                request.setAttribute("horario", horario);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterHorario.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
