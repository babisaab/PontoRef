package controller;

import utils.NetworkManager;
import dao.HorarioDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Horario;

public class PontoController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String source = NetworkManager.myIP();
        String remote = request.getRemoteAddr();
        System.out.println(source + "\t" + remote);
        Boolean sameNetwork = NetworkManager.sameNetwork(source + "/10", remote);
        Long id = Long.parseLong(request.getParameter("id"));
        if (sameNetwork) {
            String diaAtual = Instant.now().toString();
            request.setAttribute("dia", diaAtual);
            List<Horario> horarios = HorarioDAO.getInstance().getAllHorarios();
            request.getRequestDispatcher("/menu.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/errorLocalizacao.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PontoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PontoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
