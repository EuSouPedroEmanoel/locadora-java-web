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
        
        String opParam = request.getParameter("op");
        if (opParam == null) return;
        
        int operacao = Integer.parseInt(opParam.replaceAll("[^0-9]", ""));
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
                pes.setSuper_user(Boolean.parseBoolean(request.getParameter("super_user")));
                response.sendRedirect("exibe_resultado.jsp?result=" + p.inserir(pes));
            }
            case 2 -> {
                request.setAttribute("lista", p.listar());
                RequestDispatcher rd = request.getRequestDispatcher("/exibe_pessoas.jsp");
                rd.forward(request, response);
            }
            case 3 -> {
                String email = request.getParameter("email");
                String senha = request.getParameter("password");
                Pessoa usuarioLogado = p.buscarPorLogin(email, senha);
                if (usuarioLogado != null) {
                    request.getSession().setAttribute("usuarioLogado", usuarioLogado);
                    response.sendRedirect("FilmesController?op=1");
                } else {
                    request.setAttribute("erroLogin", "E-mail ou senha inválidos!");
                    RequestDispatcher rd = request.getRequestDispatcher("/entrar.jsp");
                    rd.forward(request, response);
                } 
            }
            case 4 -> {
                request.getSession().invalidate();
                response.sendRedirect("entrar.jsp");
            }
            case 5 -> {
                double valorAdicionado = Double.parseDouble(request.getParameter("novoSaldo"));
                Pessoa user = (Pessoa) request.getSession().getAttribute("usuarioLogado");
                double saldoAtual = Double.parseDouble(user.getTubMoney());
                double novoTotal = saldoAtual + valorAdicionado;
                p.atualizarSaldo(user.getCpf(), novoTotal);
                user.setTubMoney(String.valueOf(novoTotal));
                response.sendRedirect("FilmesController?op=1");
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