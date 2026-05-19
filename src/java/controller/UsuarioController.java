package controller;

import dao.UsuarioDAO;
import vo.UsuarioVO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jean
 */
public class UsuarioController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            int operacao = Integer.parseInt(request.getParameter("op"));
            UsuarioDAO u = new UsuarioDAO();

            switch (operacao) {
                case 1 -> {
                    UsuarioVO uvo = new UsuarioVO();
                    uvo.setNome(request.getParameter("nome"));
                    uvo.setDataNascimento(request.getParameter("data_nascimento"));
                    uvo.setCash(request.getParameter("cash"));
                    uvo.setSexo(request.getParameter("sexo"));
                    uvo.setSenha(request.getParameter("senha"));
                    uvo.setEmail(request.getParameter("email"));
                    
                    response.sendRedirect("exibe_resultado.jsp?result=" + u.inserir(uvo));
                }

                case 2 -> {
                    request.setAttribute("lista", p.listar());
                    RequestDispatcher rd = request.getRequestDispatcher("/exibe_pessoas.jsp");
                    rd.forward(request, response);
                }
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
