package controller;


import dao.DaoGenerico;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Departamento;

public class PesquisaDepartamentoController extends HttpServlet {
    
    DaoGenerico<Departamento> daoDepartamento = new DaoGenerico<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("departamentos", daoDepartamento.findAll(Departamento.class));
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaDepartamento.jsp");
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
