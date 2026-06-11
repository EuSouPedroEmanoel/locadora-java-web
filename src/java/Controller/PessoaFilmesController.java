package Controller;

import DAO.PessoaFilmesDAO;
import VO.PessoaFilme;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
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
        PessoaFilmesDAO pf = new PessoaFilmesDAO();

        switch (operacao) {
            case 1 -> {
                PessoaFilme pessoaFilme = new PessoaFilme();
                pessoaFilme.setCpf(request.getParameter("cpf"));
                pessoaFilme.setFilmeId(Integer.parseInt(request.getParameter("filme_id")));
                pessoaFilme.setDataDevolucaoPrev(new Data(request.getParameter("data_devolucao_prev")));
                response.sendRedirect("exibe_resultado.jsp?result=" + pf.inserir(pessoaFilme));
            }
        }
    }
}