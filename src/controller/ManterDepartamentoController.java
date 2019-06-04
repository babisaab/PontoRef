package controller;

import dao.DaoGenerico;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Departamento;

public class ManterDepartamentoController extends HttpServlet {

    DaoGenerico<Departamento> daoDepartamento = new DaoGenerico<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id"));
                Departamento departamento = daoDepartamento.findById(Departamento.class, id);
                request.setAttribute("departamento", departamento);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterDepartamento.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        } catch (IOException ex) {
            Logger.getLogger(ManterDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {

        try {
            String operacao = request.getParameter("operacao");

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdDepartamento"));
                daoDepartamento.remove(Departamento.class, id);
            } else {
                String nome = request.getParameter("txtNome");
                String descricao = request.getParameter("txtDescricao");
                Departamento departamento = new Departamento(nome, descricao);
                if (operacao.equals("Incluir")) {
                    daoDepartamento.saveOrUpdate(departamento);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdDepartamento"));

                    departamento.setId(id);
                    daoDepartamento.saveOrUpdate(departamento);

                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaDepartamentoController");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
