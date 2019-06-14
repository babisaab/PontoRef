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
import model.Contato;
import model.Funcionario;

public class ContatoController extends HttpServlet {

    DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();
    DaoGenerico<Contato> daoContato = new DaoGenerico<>();

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("confirmarOperacao")) {
            confirmarOperacao(request, response);
        } else {
            if (acao.equals("prepararOperacao")) {
                prepararOperacao(request, response);
            } else {
                if (acao.equals("getOnly)")) {
                    prepararOperacao(request, response);
                } else {

                    request.setAttribute("contatos", daoContato.findAll(Contato.class));
                    request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
                    RequestDispatcher view = request.getRequestDispatcher("/pesquisaContato.jsp");
                    view.forward(request, response);

                }
            }
        }
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
            if (!operacao.equals("Incluir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Contato contato = daoContato.findById(Contato.class, (long) id);
                request.setAttribute("contato", contato);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterContato.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {
        try {
            String operacao = request.getParameter("operacao");

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdContato"));
                daoContato.remove(Contato.class, id);
            } else {
                String telefone = request.getParameter("txtTelefone");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = daoFuncionario.findById(Funcionario.class, idFuncionario);
                Contato contato = new Contato(telefone, funcionario);
                if (operacao.equals("Incluir")) {
                    daoContato.saveOrUpdate(contato);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdContato"));

                    contato.setId(id);
                    daoContato.saveOrUpdate(contato);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("ContatoController?acao=All");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContatoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
