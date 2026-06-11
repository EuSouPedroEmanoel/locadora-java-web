package Controller;

import DAO.PessoaFilmesDAO;
import VO.PessoaFilme;
import java.io.IOException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PessoaFilmesController", urlPatterns = {"/PessoaFilmesController"})
public class PessoaFilmeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opParam = request.getParameter("op");
        if (opParam == null) return;
        int operacao = Integer.parseInt(opParam.replaceAll("[^0-9]", ""));
        PessoaFilmesDAO pfDAO = new PessoaFilmesDAO();

        switch (operacao) {
            case 1 -> {
                PessoaFilme pessoaFilme = new PessoaFilme();
                pessoaFilme.setCpf(request.getParameter("cpf"));
                pessoaFilme.setFilmeId(Integer.parseInt(request.getParameter("filme_id")));
                pessoaFilme.setDataEmprestimo(request.getParameter("data_emprestimo"));
                
                // Conversão explícita de String para LocalDate
                String dataPrev = request.getParameter("data_devolucao_prev");
                if (dataPrev != null && !dataPrev.isEmpty()) {
                    pessoaFilme.setDataDevolucaoPrev(LocalDate.parse(dataPrev));
                }
                
                response.sendRedirect("exibe_resultado.jsp?result=" + pfDAO.inserir(pessoaFilme));
            }
            case 2 -> {
                int idFilme = Integer.parseInt(request.getParameter("filme_id"));
                String cpf = request.getParameter("cpf");
                
                pfDAO.finalizarEmprestimo(idFilme, cpf);
                response.sendRedirect("exibe_resultado.jsp?result=True");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { processRequest(request, response); }
}