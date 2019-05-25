package controller;

import dao.InformeDAO;
import dao.FuncionarioDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Informe;

public class PesquisaInformeController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String acao = request.getParameter("acao");
   
        if(acao.equals("Only")){
            
             Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("acao", acao);
            List<Informe> todosAfastamentos = new ArrayList<Informe>();
            List<Informe> afastamentos = new ArrayList<Informe>();
            todosAfastamentos = InformeDAO.getInstance().getAllInformes();
            for (Informe afastamento : todosAfastamentos) {
                if (afastamento.getFuncionario().getId() == id) {
                    afastamentos.add(afastamento);
                }
            }
            
            request.setAttribute("acao", acao);
            request.setAttribute("informes", afastamentos);
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
        }else{
            request.setAttribute("informes", InformeDAO.getInstance().getAllInformes());
            request.setAttribute("funcionarios", FuncionarioDAO.getInstance().getAllFuncionarios());
        }
        RequestDispatcher view = request.getRequestDispatcher("/pesquisaInforme.jsp");
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
