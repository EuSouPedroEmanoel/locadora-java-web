package Controller;

import DAO.FilmesDAO;
import DAO.GenerosDAO;
import DAO.PessoasDAO;
import DAO.PessoaFilmesDAO;
import VO.Filme;
import VO.Pessoa;
import VO.PessoaFilme;
import java.io.IOException;
import java.time.LocalDate;
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
        PessoaFilmesDAO pfDAO = new PessoaFilmesDAO();

        switch (operacao) {
            case 1 -> {
                request.setAttribute("listaFilmes", f.listar());
                request.setAttribute("listaGeneros", new GenerosDAO().listar());
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
                    PessoaFilme pf = new PessoaFilme();
                    pf.setCpf(user.getCpf());
                    pf.setFilmeId(idFilme);
                    pf.setDataEmprestimo(LocalDate.now().toString()); 
                    pf.setDataDevolucaoPrev(LocalDate.now().plusDays(7));
                    pfDAO.inserir(pf);
                    user.setTubMoney(String.valueOf(novoSaldo));
                }
                response.sendRedirect("FilmesController?op=3&id=" + idFilme);
            }
            case 5 -> {
                int idFilme = Integer.parseInt(request.getParameter("id"));
                Pessoa user = (Pessoa) request.getSession().getAttribute("usuarioLogado");
                f.atualizarDisponibilidade(idFilme, true);
                pfDAO.finalizarEmprestimo(idFilme, user.getCpf());
                response.sendRedirect("FilmesController?op=3&id=" + idFilme);
            }
            case 6 -> {
                int idFilme = Integer.parseInt(request.getParameter("id"));
                f.excluir(idFilme);
                response.sendRedirect("FilmesController?op=1");
            }
            case 7 -> {
                int idFilme = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("filme", f.buscarPorId(idFilme));
                request.setAttribute("listaGeneros", new GenerosDAO().listar());
                RequestDispatcher rd = request.getRequestDispatcher("/form_filme.jsp");
                rd.forward(request, response);
            }
            case 8 -> {
                Filme filme = new Filme();
                filme.setId(Integer.parseInt(request.getParameter("id")));
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
                f.atualizar(filme);
                response.sendRedirect("FilmesController?op=1");
            }
            case 9 -> {
                request.setAttribute("listaGeneros", new GenerosDAO().listar());
                RequestDispatcher rd = request.getRequestDispatcher("/form_filme.jsp");
                rd.forward(request, response);
            }
            case 10 -> {
                String termo = request.getParameter("termoBusca");
                request.setAttribute("listaFilmes", f.pesquisarPorNome(termo));
                RequestDispatcher rd = request.getRequestDispatcher("/resultados_pesquisa.jsp");
                rd.forward(request, response);
            }
            case 11 -> {
                String generoId = request.getParameter("genero_id");
                String disponibilidade = request.getParameter("disponibilidade");
                request.setAttribute("listaFilmes", f.filtrar(generoId, disponibilidade));
                request.setAttribute("listaGeneros", new GenerosDAO().listar());
                RequestDispatcher rd = request.getRequestDispatcher("/catalogo.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
}