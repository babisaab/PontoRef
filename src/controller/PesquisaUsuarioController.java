package controller;


import dao.DaoGenerico;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Funcionario;
import model.Usuario;

public class PesquisaUsuarioController extends HttpServlet {
    
    DaoGenerico<Usuario> daoUsuario = new DaoGenerico<>();
    DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("usuarios", daoUsuario.findAll(Usuario.class));
        request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaUsuario.jsp");
        view.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       processRequest(request, response); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       processRequest(request, response); 
    }
}
