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
        <title>Catálogo</title>
    </head>

    <body style="margin: 0; padding: 0; background-color: black; color: white; font-family: sans-serif;">
        <nav style="display: flex; justify-content: space-between; align-items: center; padding: 15px; background-color: #2e2e2e; position: sticky; top: 0; z-index: 100;">
            <form action="FilmesController" method="GET" style="margin: 0; padding: 0; font-size: large;">
                <input type="hidden" name="op" value="10" />
                <input type="text" name="termoBusca" placeholder="Pesquisar" />
                <button type="submit">IR</button>
            </form>

            <% if (session.getAttribute("usuarioLogado") != null) {
                Pessoa user = (Pessoa) session.getAttribute("usuarioLogado");
            if (user.getSuper_user()) { %>
            <a href="FilmesController?op=9" style="color: yellow; text-decoration: none;">+ Adicionar Filme</a>
            <% } %>

            <div style="display: flex; align-items: center; gap: 15px;">
                <div style="display: flex; align-items: center; gap: 8px;">
                    <span style="background-color: #1a1a1a; color: white; padding: 5px 12px; border-radius: 4px; font-weight: bold; font-size: 0.9em;">
                        TubMoney: <%= user.getTubMoney() %>
                    </span>
                    <a href="adicionar_saldo.jsp" style="background-color: #28a745; color: white; text-decoration: none; padding: 2px 8px; border-radius: 4px; font-weight: bold;">+</a>
                </div>

                <a href="PessoaFilmesController?op=3" style="color: white; text-decoration: none;">Olá, <%= user.getNome() %> 🤠👋</a>
                <a href="PessoasController?op=4" style="color: white; text-decoration: none;">Sair</a>
            </div>
            <% } else { %>
            <div>
                <a href="entrar.jsp" style="color: white; text-decoration: none;">Entrar</a>
            </div>
            <% } %>
        </nav>

        <main>
            <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; padding: 20px; max-width: 1000px; margin: 0 auto;">
                <%
                    List<Filme> filmes = (List<Filme>) request.getAttribute("listaFilmes");
                    if (filmes != null) {
                        for (Filme f : filmes) {
                        %>
                        <div style="border: 1px solid #444; padding: 15px; text-align: center; background-color: #1a1a1a; border-radius: 8px; position: relative;">
                            <img src="<%= f.getCapaLink() %>" alt="<%= f.getNome() %>" style="max-width: 100%; height: 300px; object-fit: cover; border-radius: 4px;">

                            <div style="display: flex; justify-content: center; align-items: center; position: relative;">
                                <h3 style="margin: 10px 0;"><%= f.getNome() %></h3>

                                <% if (isAdmin) { %>
                                <span onclick="toggleMenu(event, <%= f.getId() %>)" style="cursor: pointer; font-size: 22px; font-weight: bold; padding: 0 10px; color: white; user-select: none;">&#8942;</span>

                                <div id="menu-<%= f.getId() %>" style="display: none; position: absolute; right: 0; top: 100%; background-color: #333; border: 1px solid #ffffff; border-radius: 4px; padding: 5px 0; box-shadow: 0 4px 8px rgba(0,0,0,0.5); z-index: 10; min-width: 100px;">
                                    <a href="FilmesController?op=7&id=<%= f.getId() %>" style="color: #ffffff; text-decoration: none; font-size: 14px; display: block; padding: 8px 15px; text-align: center;">Editar</a>
                                    <a href="FilmesController?op=6&id=<%= f.getId() %>" onclick="return confirm('Tem certeza que deseja excluir este filme?');" style="color: #ff4c4c; text-decoration: none; font-size: 14px; display: block; padding: 8px 15px; text-align: center;">Excluir</a>
                                </div>
                                <% } %>
                            </div>

                            <a href="FilmesController?op=3&id=<%= f.getId() %>" style="color: cyan; text-decoration: none;">Ver Detalhes</a>
                        </div>
                        <%
                        }
                    } else {
                        out.print("<p>Nenhum filme encontrado.</p>");
                    }
                %>
            </div>
        </main>

        <script>
            function toggleMenu(event, id) {
                event.stopPropagation();
                var menu = document.getElementById('menu-' + id);
                var isCurrentlyVisible = (menu.style.display === 'block');
                var allMenus = document.querySelectorAll('[id^="menu-"]');
                allMenus.forEach(function(m) { m.style.display = 'none'; });
                if (!isCurrentlyVisible) {
                    menu.style.display = 'block';
                }
            }
            document.addEventListener('click', function(event) {
                var allMenus = document.querySelectorAll('[id^="menu-"]');
                allMenus.forEach(function(m) { m.style.display = 'none'; });
            });
        </script>
    </body>
</html>