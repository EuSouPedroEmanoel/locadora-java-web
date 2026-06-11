<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="VO.Filme"%>
<%@page import="VO.Pessoa"%>
<%@page import="DAO.PessoaFilmesDAO"%>

<html>
    <head>
        <title>Detalhes do Filme</title>
    </head>
    <body style="background-color: black; color: white; padding: 20px; font-family: sans-serif;">
        <%
            Filme f = (Filme) request.getAttribute("filmeDetalhe");
            Pessoa user = (Pessoa) session.getAttribute("usuarioLogado");
            PessoaFilmesDAO pfDAO = new PessoaFilmesDAO();
            
            String cpfDono = (f != null && !f.getDisponibilidade()) ? pfDAO.buscarCpfDonoDoFilme(f.getId()) : null;
            boolean usuarioEhDono = (user != null && cpfDono != null && user.getCpf().equals(cpfDono));
        %>

        <div style="max-width: 800px; margin: 0 auto; display: flex; gap: 30px;">
            <% if(f != null) { %>
            <img src="<%= f.getCapaLink() %>" style="width: 300px; border-radius: 8px;">
            <div>
                <h1><%= f.getNome() %></h1>
                <p><strong>Custo:</strong> R$ <%= String.format("%.2f", f.getTubCusto()) %></p>

                <% if(user != null) { %>
                <p style="color: gold;"><strong>Seu Saldo:</strong> <%= user.getTubMoney() %> TubMoney</p>

                <% if (f.getDisponibilidade()) { %>
                <% if (Double.parseDouble(user.getTubMoney()) >= f.getTubCusto()) { %>
                <a href="FilmesController?op=4&id=<%= f.getId() %>"
                    style="background-color: green; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;">
                    Alugar Filme
                </a>
                <% } else { %>
                <p style="color: red;">Saldo insuficiente para alugar.</p>
                <% } %>
                <% } else { %>
                <p style="color: orange; font-weight: bold; font-size: 1.2em;">ALUGADO</p>

                <% if (usuarioEhDono) { %>
                <div style="display: flex; gap: 10px; align-items: center;">
                    <a href="FilmesController?op=5&id=<%= f.getId() %>"
                        style="background-color: red; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;">
                        Devolver
                    </a>
                    <a href="<%= f.getFilmeLink() %>" target="_blank"
                        style="color: cyan; font-weight: bold; text-decoration: underline;">
                        Assista aqui
                    </a>
                </div>
                <% } %>
                <% } %>

                <% } else { %>
                <p><a href="entrar.jsp" style="color: cyan;">Entre para alugar este filme</a></p>
                <% } %>

                <hr style="border: 0; border-top: 1px solid #444; margin: 20px 0;">

                <p><strong>Descrição:</strong> <%= f.getDescricao() %></p>
                <p><strong>Autor:</strong> <%= f.getAutor() %></p>
                <p><strong>Indicação Etária:</strong> <%= f.getIndicacaoEtaria() %> anos</p>
                <p><strong>Ano:</strong> <%= f.getAno() %></p>
                <p><strong>Duração:</strong> <%= f.getDuracao() %></p>

                <br>
                <a href="FilmesController?op=1" style="color: white; border: 1px solid white; padding: 10px; text-decoration: none;">Voltar ao Catálogo</a>
            </div>
            <% } else { %>
            <p>Filme não encontrado.</p>
            <a href="FilmesController?op=1">Voltar</a>
            <% } %>
        </div>
    </body>
</html>