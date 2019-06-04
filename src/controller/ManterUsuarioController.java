package controller;


import dao.DaoGenerico;
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
import model.Usuario;
import model.Funcionario;

public class ManterUsuarioController extends HttpServlet {
    DaoGenerico<Usuario> daoUsuario =new DaoGenerico<>();
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
                Long id = Long.parseLong(request.getParameter("txtIdUsuario").trim());
daoUsuario.remove(Usuario.class, id);
            } else {
                String login = request.getParameter("txtLoginUsuario");
                String senha = request.getParameter("txtSenhaUsuario");
                Long idFuncionario = Long.parseLong(request.getParameter("optFuncionario"));
                Funcionario funcionario = daoFuncionario.findById(Funcionario.class, idFuncionario);
                Usuario usuario = new Usuario(login, senha, funcionario);
                if (operacao.equals("Incluir")) {

                daoUsuario.saveOrUpdate(usuario);} else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdUsuario").trim());

                    usuario.setId(id);
                    daoUsuario.saveOrUpdate(usuario);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaUsuarioController");
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
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id"));
                Usuario usuario = daoUsuario.findById(Usuario.class, id);
                request.setAttribute("usuario", usuario);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterUsuario.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(ManterUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
