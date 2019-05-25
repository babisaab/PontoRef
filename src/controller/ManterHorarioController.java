package controller;

import dao.FuncionarioDAO;
import dao.HorarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Horario;

public class ManterHorarioController extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);

            }
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
        String operacao = request.getParameter("operacao");
        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdHorario").trim());

                HorarioDAO.getInstance().excluir(HorarioDAO.getInstance().getHorario(id));
            } else {
                String dia_da_semana = request.getParameter("txtDiaDaSemanaHorario");
                String hora_inicio = request.getParameter("txtHoraInicioHorario");
                String hora_fim = request.getParameter("txtHoraFimHorario");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = FuncionarioDAO.getInstance().getFuncionario(idFuncionario);
                Horario horario = new Horario(dia_da_semana, hora_inicio, hora_fim, funcionario);
                if (operacao.equals("Incluir")) {
                    HorarioDAO.getInstance().salvar(horario);
                } else {
                    if (operacao.equals("Editar")) {
                        Long id = Long.parseLong(request.getParameter("txtIdHorario").trim());

                        horario.setId(id);
                        HorarioDAO.getInstance().salvar(horario);
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaHorarioController");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id").trim());
                Horario horario = HorarioDAO.getInstance().getHorario(id);
                request.setAttribute("horario", horario);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterHorario.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterHorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
