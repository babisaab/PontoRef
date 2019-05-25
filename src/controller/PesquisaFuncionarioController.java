package controller;

import dao.FuncionarioDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PesquisaFuncionarioController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaFuncionario.jsp");
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
