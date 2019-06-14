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
import model.Informe;

public class InformeController extends HttpServlet {

    DaoGenerico<Informe> daoInforme = new DaoGenerico<>();
    DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();

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
                    List<Informe> todosInformes = new ArrayList<Informe>();
                    List<Informe> informes = new ArrayList<Informe>();
                    todosInformes = daoInforme.findAll(Informe.class);
                    for (Informe informe : todosInformes) {
                        if (informe.getFuncionario().getId() == id) {
                            informes.add(informe);
                        }
                    }

                    request.setAttribute("acao", acao);
                    request.setAttribute("informes", informes);
                    request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
                } else {
                    request.setAttribute("informes", daoInforme.findAll(Informe.class));
                    request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
                }
                RequestDispatcher view = request.getRequestDispatcher("/pesquisaInforme.jsp");
                view.forward(request, response);
            }
        }
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id"));
                Informe informe = daoInforme.findById(Informe.class, id);
                request.setAttribute("informe", informe);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterInforme.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(InformeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        String operacao = request.getParameter("operacao");

        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdInforme"));
                daoInforme.remove(Informe.class, id);
            } else {
                String dataOcorrido = request.getParameter("txtDataInforme");
                String motivo = request.getParameter("txtMotivoInforme");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = daoFuncionario.findById(Funcionario.class, idFuncionario);
                Informe informe = new Informe(dataOcorrido, motivo, funcionario);
                if (operacao.equals("Incluir")) {
                    daoInforme.saveOrUpdate(informe);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdInforme"));

                    informe.setId(id);
                    daoInforme.saveOrUpdate(informe);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("InformeController?acao=All");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(InformeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InformeController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(InformeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InformeController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(InformeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
