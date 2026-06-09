package Controller;

import DAO.GenerosDAO;
import VO.Genero;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "GeneroController", urlPatterns = {"/GeneroController"})
public class GeneroController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int operacao = Integer.parseInt(request.getParameter("op"));
        GenerosDAO g = new GenerosDAO();

        switch (operacao) {
            case 1 -> {
                request.setAttribute("listaGeneros", g.listar());
                RequestDispatcher rd = request.getRequestDispatcher("/adicionarFilme.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "GeneroController";
    }
}