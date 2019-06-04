package controller;



import dao.DaoGenerico;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Contato;
import model.Funcionario;

public class PesquisaContatoController extends HttpServlet {
    
    DaoGenerico<Contato> daoContato = new DaoGenerico<>();
    DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("contatos", daoContato.findAll(Contato.class));
        request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaContato.jsp");
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
