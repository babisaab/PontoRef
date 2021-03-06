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
import model.Cargo;
import model.Funcionario;

public class FuncionarioController extends HttpServlet {

    DaoGenerico<Cargo> daoCargo = new DaoGenerico<>();
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
                if (acao.equals("getOnly")) {
                    prepararOperacao(request, response);
                } else {
                    request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
                    RequestDispatcher view = request.getRequestDispatcher("/pesquisaFuncionario.jsp");
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
            request.setAttribute("cargos", daoCargo.findAll(Cargo.class));
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id").trim());
                Funcionario funcionario = daoFuncionario.findById(Funcionario.class, id);
                request.setAttribute("funcionario", funcionario);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterFuncionario.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {
        String operacao = request.getParameter("operacao");

        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdFuncionario"));
                daoFuncionario.remove(Funcionario.class, id);
            } else {
                String nomeCompleto = request.getParameter("txtNomeCompleto");
                String cpf = request.getParameter("txtCPF");
                String rg = request.getParameter("txtRG");
                String sexo = request.getParameter("optSexo");
                String dataNascimento = request.getParameter("txtNascimento");
                String cep = request.getParameter("txtCEP");
                String logradouro = request.getParameter("txtLogradouro");
                String numero = request.getParameter("txtNumero");
                String complemento = request.getParameter("txtComplemento");
                String bairro = request.getParameter("txtBairro");
                String cidade = request.getParameter("txtCidade");
                String uf = request.getParameter("txtUf");
                String matricula = request.getParameter("txtMatricula");
                String curso = request.getParameter("optCurso");
                String dataAdmissao = request.getParameter("txtAdmissao");
                String email = request.getParameter("txtEmail");
                Long idCargo = Long.parseLong(request.getParameter("optCargo"));
                Cargo cargo = daoCargo.findById(Cargo.class, idCargo);

                Funcionario funcionario = new Funcionario(nomeCompleto, cpf, rg, sexo, dataNascimento, cep, logradouro, numero, complemento, bairro, cidade, uf, matricula, curso, dataAdmissao, email, cargo);
                if (operacao.equals("Incluir")) {
                    daoFuncionario.saveOrUpdate(funcionario);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdFuncionario"));

                    funcionario.setId(id);
                    daoFuncionario.saveOrUpdate(funcionario);
                } else if (operacao.equals("Editar")) {
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("FuncionarioController?acao=All");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
