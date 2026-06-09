<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="VO.Filme" %>
<%@ page import="VO.Pessoa" %>

<html>
    <head>
        <title>Catálogo</title>
        <style>
            .grid-container {
                display: grid;
                grid-template-columns: repeat(3, 1fr); /* Cria 3 colunas iguais */
                gap: 20px;
                padding: 20px;
                max-width: 1000px;
                margin: 0 auto;
            }
            .card {
                border: 1px solid #444;
                padding: 15px;
                text-align: center;
                background-color: #1a1a1a;
                border-radius: 8px;
            }
            .card img {
                max-width: 100%;
                height: 300px;
                object-fit: cover;
                border-radius: 4px;
            }
        </style>
    </head>

    <body style="margin: 0; padding: 0; background-color: black; color: white;">
        <nav style="display: flex; justify-content: space-between; align-items: center; padding: 15px; background-color: #2e2e2e; position: sticky; top: 0;">
            <form action="" style="margin: 0; padding: 0; font-size: large;"">
                <input type="text" placeholder="Pesquisar" />
                <button type="submit">IR</button>
            </form>

            <% if (session.getAttribute("usuarioLogado") != null) {
                Pessoa user = (Pessoa) session.getAttribute("usuarioLogado");
                if (user.getSuper_user()) { %>
                    <a href="http://localhost:8080/Locadora/GeneroController?op=1" style="color: yellow;">+ Adicionar Filme</a>
            <% } %>
                <div>
                    Olá, ${sessionScope.usuarioLogado.nome} 🤠👋
                    <a href="PessoasController?op=4" style="color: white; margin-left: 10px;">Sair</a>
                </div>
            <% } else { %>
                <div>
                    <a href="entrar.jsp" style="color: white;">Entrar</a>
                </div>
            <% } %>
        </nav>

        <main>
            <div class="grid-container">
                <% 
                    List<Filme> filmes = (List<Filme>) request.getAttribute("listaFilmes");
                    if (filmes != null) {
                        for (Filme f : filmes) { 
                %>
                    <div class="card">
                        <img src="<%= f.getCapaLink() %>" alt="<%= f.getNome() %>">
                        <h3><%= f.getNome() %></h3>
                        <a href="FilmesController?op=3&id=<%= f.getId() %>" style="color: cyan;">Ver Detalhes</a>
                    </div>
                <% 
                        }
                    } else {
                        out.print("<p>Nenhum filme encontrado.</p>");
                    }
                %>
            </div>
        </main>
    </body>
</html>