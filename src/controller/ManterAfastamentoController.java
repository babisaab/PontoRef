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
import model.Afastamento;
import model.Funcionario;

public class ManterAfastamentoController extends HttpServlet {
    DaoGenerico<Afastamento> daoAfastamento = new DaoGenerico<>();
        DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();


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
                Long id = Long.parseLong(request.getParameter("txtIdAfastamento").trim());

                daoAfastamento.remove(Afastamento.class,id );
            } else {
                String inicio = request.getParameter("txtInicioAfastamento");
                String termino = request.getParameter("txtTerminoAfastamento");
                String justificativa = request.getParameter("txtJustificativaAfastamento");
                String tipo = request.getParameter("txtTipo");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = daoFuncionario.findById(Funcionario.class, idFuncionario);
                Afastamento afastamento = new Afastamento(inicio, termino, justificativa, tipo, funcionario);
                if (operacao.equals("Incluir")) {
                    daoAfastamento.saveOrUpdate(afastamento);
                } else {
                    if (operacao.equals("Editar")) {
                        Long id = Long.parseLong(request.getParameter("txtIdAfastamento").trim());

                        afastamento.setId(id);
                    daoAfastamento.saveOrUpdate(afastamento);
                    }
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAfastamentoController");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterAfastamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException {
        try {
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class)
                    );
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id").trim());
                Afastamento afastamento = daoAfastamento.findById(Afastamento.class, id);
                request.setAttribute("afastamento", afastamento);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterAfastamento.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterAfastamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterAfastamentoController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ManterAfastamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterAfastamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterAfastamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
