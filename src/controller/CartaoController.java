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
import model.Cartao;
import model.Funcionario;

public class CartaoController extends HttpServlet {

    DaoGenerico<Cartao> daoCartao = new DaoGenerico<>();
    DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
                    List<Cartao> todosCartoes = new ArrayList<Cartao>();
                    List<Cartao> cartoes = new ArrayList<Cartao>();
                    todosCartoes = daoCartao.findAll(Cartao.class);
                    for (Cartao cartao : todosCartoes) {
                        if (cartao.getfuncionario().getId() == id) {
                            cartoes.add(cartao);
                        }
                    }
                    request.setAttribute("acao", acao);
                    request.setAttribute("cartoes", cartoes);
                    request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
                } else {
                    request.setAttribute("cartoes", daoCartao.findAll(Cartao.class));
                    request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
                }
                RequestDispatcher view = request.getRequestDispatcher("/pesquisaCartao.jsp");
                view.forward(request, response);
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
                Long id = Long.parseLong(request.getParameter("id"));
                Cartao cartao = daoCartao.findById(Cartao.class, id);
                request.setAttribute("cartao", cartao);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterCartao.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(CartaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {
        String operacao = request.getParameter("operacao");

        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdCartao"));
                daoCartao.remove(Cartao.class, id);
            } else {
                String tipo = request.getParameter("optTipoCartao");
                String cor = request.getParameter("optCorCartao");
                String motivo = request.getParameter("txtMotivoCartao");
                String data = request.getParameter("txtDataCartao");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = daoFuncionario.findById(Funcionario.class, idFuncionario);
                Cartao cartao = new Cartao(tipo, cor, motivo, data, funcionario);
                if (operacao.equals("Incluir")) {
                    daoCartao.saveOrUpdate(cartao);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdCartao"));

                    cartao.setId(id);
                    daoCartao.saveOrUpdate(cartao);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("CartaoController?acao=All");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(CartaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
