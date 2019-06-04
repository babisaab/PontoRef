package controller;

import dao.DaoGenerico;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import model.Informe;

public class PesquisaInformeController extends HttpServlet {
    
        DaoGenerico<Funcionario> daoFuncionario = new DaoGenerico<>();
         DaoGenerico<Informe> daoInforme = new DaoGenerico<>();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String acao = request.getParameter("acao");
   
        if(acao.equals("Only")){
            
             Long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("acao", acao);
            List<Informe> todosAfastamentos = new ArrayList<Informe>();
            List<Informe> afastamentos = new ArrayList<Informe>();
            todosAfastamentos = daoInforme.findAll(Informe.class);
            for (Informe afastamento : todosAfastamentos) {
                if (afastamento.getFuncionario().getId() == id) {
                    afastamentos.add(afastamento);
                }
            }
            
            request.setAttribute("acao", acao);
            request.setAttribute("informes", afastamentos);
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
        }else{
            request.setAttribute("informes", daoInforme.findAll(Informe.class));
            request.setAttribute("funcionarios", daoFuncionario.findAll(Funcionario.class));
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
