<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="VO.Filme" %>
<%@ page import="VO.Pessoa" %>

<%
    boolean isAdmin = false;
    if (session.getAttribute("usuarioLogado") != null) {
        Pessoa u = (Pessoa) session.getAttribute("usuarioLogado");
        isAdmin = u.getSuper_user();
    }
%>

<html>
    <head>
        <title>Resultados da Pesquisa</title>
    </head>

    <body style="margin: 0; padding: 0; background-color: black; color: white; font-family: sans-serif;">
        <nav style="display: flex; justify-content: space-between; align-items: center; padding: 15px; background-color: #2e2e2e; position: sticky; top: 0; z-index: 100; border-bottom: 1px solid #444;">
            <form action="FilmesController" method="GET" style="margin: 0; padding: 0; font-size: large;">
                <input type="hidden" name="op" value="10" />
                <input type="text" name="termoBusca" placeholder="Pesquisar outro filme..." value="<%= request.getParameter("termoBusca") != null ? request.getParameter("termoBusca") : "" %>" style="padding: 5px;"/>
                <button type="submit" style="padding: 5px 10px;">IR</button>
            </form>

            <div style="display: flex; align-items: center; gap: 15px;">
                <a href="FilmesController?op=1" style="color: cyan; text-decoration: none; font-weight: bold;">Voltar ao Catálogo</a>
                <% if (session.getAttribute("usuarioLogado") != null) { Pessoa user = (Pessoa) session.getAttribute("usuarioLogado"); %>
                <a href="PessoaFilmesController?op=3" style="color: white; text-decoration: none;">Olá, <%= user.getNome() %> 🤠👋</a>
                <% } %>
            </div>
        </nav>

        <main style="max-width: 900px; margin: 30px auto; padding: 0 20px;">
            <h2 style="border-bottom: 1px solid #444; padding-bottom: 10px; margin-bottom: 20px;">
                Resultados para: "<span style="color: cyan;"><%= request.getParameter("termoBusca") %></span>"
            </h2>

            <div style="display: flex; flex-direction: column; gap: 15px;">
                <%
                    List<Filme> filmes = (List<Filme>) request.getAttribute("listaFilmes");
                    if (filmes != null && !filmes.isEmpty()) {
                        for (Filme f : filmes) {
                        %>
                        <div style="display: flex; background-color: #1a1a1a; border: 1px solid #333; border-radius: 8px; padding: 15px; gap: 20px; align-items: stretch;">
                            <img src="<%= f.getCapaLink() %>" alt="<%= f.getNome() %>" style="width: 120px; height: 180px; object-fit: cover; border-radius: 4px;">

                            <div style="display: flex; flex-direction: column; justify-content: space-between; flex-grow: 1;">
                                <div>
                                    <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                                        <h3 style="margin: 0 0 10px 0; font-size: 1.4em;"><%= f.getNome() %></h3>
                                        <span style="background-color: #333; padding: 4px 8px; border-radius: 4px; font-size: 0.9em; font-weight: bold;">
                                            T$ <%= f.getTubCusto() %>
                                        </span>
                                    </div>

                                    <p style="margin: 0 0 10px 0; color: #aaa; font-size: 0.9em;">
                                        <strong>Ano:</strong> <%= f.getAno() %> | <strong>Duração:</strong> <%= f.getDuracao() %>
                                    </p>

                                    <p style="margin: 0; color: #ddd; font-size: 0.95em; line-height: 1.4; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden;">
                                        <%= f.getDescricao() != null ? f.getDescricao() : "Nenhuma descrição disponível para este filme." %>
                                    </p>
                                </div>

                                <div style="display: flex; justify-content: flex-end; margin-top: 15px;">
                                    <a href="FilmesController?op=3&id=<%= f.getId() %>" style="background-color: #007bff; color: white; padding: 8px 16px; text-decoration: none; border-radius: 4px; font-weight: bold;">Ver Detalhes</a>
                                </div>
                            </div>
                        </div>
                        <%
                        }
                    } else {
                    %>
                    <div style="text-align: center; padding: 50px; background-color: #1a1a1a; border-radius: 8px;">
                        <h3 style="color: #ff4c4c;">Nenhum filme encontrado.</h3>
                        <p style="color: #aaa;">Tente pesquisar por outro termo ou verifique a ortografia.</p>
                    </div>
                    <%
                    }
                %>
            </div>
        </main>
    </body>
</html>