package Controller;

import DAO.FilmesDAO;
import VO.Filme;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FilmesController", urlPatterns = {"/FilmesController"})
public class FilmesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int operacao = Integer.parseInt(request.getParameter("op"));
        FilmesDAO f = new FilmesDAO();

        switch (operacao) {
            case 1 -> {
                request.setAttribute("listaFilmes", f.listar());
                RequestDispatcher rd = request.getRequestDispatcher("/catalogo.jsp");
                rd.forward(request, response);
            }
           case 2 -> {
    // Rastreamento: Vamos ver se os dados estão chegando no Controller
    System.out.println("--- Dados recebidos no Controller ---");
    System.out.println("Nome: " + request.getParameter("nome"));
    System.out.println("Genero ID: " + request.getParameter("genero_id"));
    System.out.println("Custo: " + request.getParameter("tub_custo"));
    // ... adicione outros se quiser
    
    Filme filme = new Filme();
    filme.setNome(request.getParameter("nome"));
    filme.setDescricao(request.getParameter("descricao"));
    filme.setAutor(request.getParameter("autor"));
    filme.setIndicacaoEtaria(Integer.parseInt(request.getParameter("indicacaoEtaria")));
    filme.setCapaLink(request.getParameter("capa"));
    filme.setFilmeLink(request.getParameter("filme"));
    filme.setAno(Integer.parseInt(request.getParameter("ano")));
    filme.setDuracao(request.getParameter("duracao"));
    filme.setDisponibilidade(Boolean.parseBoolean(request.getParameter("disponibilidade")));
    filme.setGeneroId(Integer.parseInt(request.getParameter("genero_id")));
    filme.setTubCusto(Double.parseDouble(request.getParameter("tub_custo")));
    
    String resultado = f.inserir(filme);
    System.out.println("Resultado da inserção no banco: " + resultado);
    
    response.sendRedirect("exibe_resultado.jsp?result=" + resultado);
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
        return "FilmesController";
    }
}