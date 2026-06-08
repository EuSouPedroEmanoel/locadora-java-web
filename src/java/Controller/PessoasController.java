package Controller;

import DAO.PessoasDAO;
import VO.Pessoa;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PessoasController", urlPatterns = {"/PessoasController"})
public class PessoasController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int operacao = Integer.parseInt(request.getParameter("op"));
        PessoasDAO p = new PessoasDAO();

        switch (operacao) {
            case 1 -> {
                Pessoa pes = new Pessoa();
                pes.setCpf(request.getParameter("cpf"));
                pes.setNome(request.getParameter("nome")); 
                pes.setDataNascimento(request.getParameter("data_nascimento"));  
                pes.setSexo(request.getParameter("sexo"));  
                pes.setTubMoney(request.getParameter("tub_money")); 
                pes.setEmail(request.getParameter("email"));  
                pes.setSenha(request.getParameter("senha"));      
                response.sendRedirect("exibe_resultado.jsp?result=" + p.inserir(pes));
            }

            case 2 -> {
                request.setAttribute("lista", p.listar());
                RequestDispatcher rd = request.getRequestDispatcher("/exibe_pessoas.jsp");
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
        return "Controlador para operações de Pessoas";
    }
}