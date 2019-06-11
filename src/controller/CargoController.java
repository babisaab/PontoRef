package controller;

import dao.DaoGenerico;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;
import model.Departamento;

public class CargoController extends HttpServlet {

    DaoGenerico<Cargo> daoCargo = new DaoGenerico<>();
    DaoGenerico<Departamento> daoDepartamento = new DaoGenerico<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
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

                    request.setAttribute("cargos", daoCargo.findAll(Cargo.class));
                    RequestDispatcher view = request.getRequestDispatcher("/pesquisaCargo.jsp");
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
            request.setAttribute("departamentos", daoDepartamento.findAll(Departamento.class));
            if (!operacao.equals("Incluir")) {
                Long id = Long.parseLong(request.getParameter("id").trim());
                Cargo cargo = daoCargo.findById(Cargo.class, id);
                request.setAttribute("cargo", cargo);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterCargo.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(CargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {
        String operacao = request.getParameter("operacao");
        try {

            if (operacao.equals("Excluir")) {
                Long id = Long.parseLong(request.getParameter("txtIdCargo").trim());
                daoCargo.remove(Cargo.class, id);
            } else {
                String nome = request.getParameter("txtNomeCargo");
                String cargaHorariaObrigatoria = request.getParameter("txtCargaHorariaObrigatoria");
                Long idDepartamento = Long.parseLong(request.getParameter("optDepartamento"));
                Departamento departamento = daoDepartamento.findById(Departamento.class, idDepartamento);
                Cargo cargo = new Cargo(nome, cargaHorariaObrigatoria, departamento);
                if (operacao.equals("Incluir")) {
                    daoCargo.saveOrUpdate(cargo);
                } else if (operacao.equals("Editar")) {
                    Long id = Long.parseLong(request.getParameter("txtIdCargo").trim());

                    cargo.setId(id);
                    daoCargo.saveOrUpdate(cargo);
                }
            }

            RequestDispatcher view = request.getRequestDispatcher("CargoController?acao=All");
            view.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(CargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CargoController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(CargoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CargoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
