package controller;

import dao.FuncionarioDAO;
import dao.InformeDAO;
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
import model.Informe;

public class ManterInformeController extends HttpServlet {

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

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id"));
                Informe informe = InformeDAO.getInstance().getInforme(id);
                request.setAttribute("informe", informe);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterInforme.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterInformeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        String operacao = request.getParameter("operacao");

        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdInforme"));

                InformeDAO.getInstance().excluir(InformeDAO.getInstance().getInforme(id));
            } else {
                String dataOcorrido = request.getParameter("txtDataInforme");
                String motivo = request.getParameter("txtMotivoInforme");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = FuncionarioDAO.getInstance().getFuncionario(idFuncionario);
                Informe informe = new Informe(dataOcorrido, motivo, funcionario);
                if (operacao.equals("Incluir")) {
                    InformeDAO.getInstance().salvar(informe);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdInforme"));

                    informe.setId(id);
                    InformeDAO.getInstance().salvar(informe);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaInformeController");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCargoController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ManterInformeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCargoController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ManterInformeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
