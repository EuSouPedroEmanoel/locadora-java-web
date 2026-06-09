package Controller;

import DAO.FilmesDAO;
import DAO.PessoasDAO;
import VO.Filme;
import VO.Pessoa;
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
        
        String opParam = request.getParameter("op");
        if (opParam == null) return;
        int operacao = Integer.parseInt(opParam.replaceAll("[^0-9]", ""));
        FilmesDAO f = new FilmesDAO();
        PessoasDAO p = new PessoasDAO();

        switch (operacao) {
            case 1 -> {
                request.setAttribute("listaFilmes", f.listar());
                RequestDispatcher rd = request.getRequestDispatcher("/catalogo.jsp");
                rd.forward(request, response);
            }
            case 2 -> {
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
                response.sendRedirect("exibe_resultado.jsp?result=" + f.inserir(filme));
            }
            case 3 -> {
                int idFilme = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("filmeDetalhe", f.buscarPorId(idFilme));
                RequestDispatcher rd = request.getRequestDispatcher("/detalhes.jsp");
                rd.forward(request, response);
            }
            case 4 -> {
                int idFilme = Integer.parseInt(request.getParameter("id"));
                Pessoa user = (Pessoa) request.getSession().getAttribute("usuarioLogado");
                Filme filme = f.buscarPorId(idFilme);
                if (user != null && filme != null) {
                    double novoSaldo = Double.parseDouble(user.getTubMoney()) - filme.getTubCusto();
                    p.atualizarSaldo(user.getCpf(), novoSaldo);
                    f.atualizarDisponibilidade(idFilme, false);
                    user.setTubMoney(String.valueOf(novoSaldo));
                }
                response.sendRedirect("FilmesController?op=3&id=" + idFilme);
            }
            case 5 -> {
                int idFilme = Integer.parseInt(request.getParameter("id"));
                f.atualizarDisponibilidade(idFilme, true);
                response.sendRedirect("FilmesController?op=3&id=" + idFilme);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
}