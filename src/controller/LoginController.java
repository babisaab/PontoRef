package controller;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;
import model.Funcionario;
import model.Usuario;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("VerificarUsuario")) {
            verificar(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
        Boolean confirma = false;
        String login = request.getParameter("txtLogin");
        String senha = request.getParameter("txtSenha");
        String senhaVdd = "";
        Funcionario funcionario = new Funcionario();
        try {
            List<Usuario> usuarios = UsuarioDAO.getInstance().getAllUsuarios();
            for (Usuario usuario : usuarios) {
                if (usuario.getLogin().equals(login)) {
                    confirma = true;
                    senhaVdd = usuario.getSenha();
                    funcionario = usuario.getFuncionario();

                }
            }
            if (confirma) {
                if (senhaVdd.equals(senha)) {
                    request.getSession().setAttribute("login", login);
                    request.getSession().setAttribute("senha", senha);
                    request.getSession().setAttribute("f", funcionario);
                    RequestDispatcher view = request.getRequestDispatcher("/menu.jsp");
                    view.forward(request, response);
                } else {
                    RequestDispatcher view = request.getRequestDispatcher("/errorSenhaIncorreta.jsp");
                    view.forward(request, response);
                }
            } else {
                RequestDispatcher view = request.getRequestDispatcher("/errorUsuarioIncorreto.jsp");
                view.forward(request, response);
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
