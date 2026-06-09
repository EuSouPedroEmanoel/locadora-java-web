<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Catálogo</title>
        <style>
            input.form {
                width: 100%;
            }
        </style>
    </head>

    <body style="margin: 0; padding: 0;">
        <nav
        style="display: flex; flex-direction: row; justify-content: space-between; align-items: center; padding: 10px; font-size: large; position: sticky; top: 0; background-color: #2e2e2e; color: white;">
        <form action="" style="margin: 0; padding: 0; font-size: large;">
            <input type="text" placeholder="Pesquisar" style="font-size: large;" />
            <button type="submit" style="font-size: large;">IR</button>
        </form>

        <%
            if (session.getAttribute("usuarioLogado") != null) {
            %>



            <div style="display: flex; gap: 14px; color: white;">
                <a href="catalogo.jsp" style="color: white;">Catálogo<a/>
                olá, ${sessionScope.usuarioLogado.nome} 🤠👋
                <a href="LogoutServlet" style="color: white;">Sair</a>
            </div>

            <% } else { %>
            <div style="display: flex; gap: 14px; color: white;">
                <a href="entrar.jsp" style="color: white;">Entrar</a>
                <a href="inserir.jsp" style="color: white;">Cadastrar</a>
            </div>
            <% } %>
        </nav>
        <main
        style="background-color: black; height: 100%; display: flex; flex-direction: column; gap: 4px; justify-content: center; align-items: center; margin: 0; padding: 0">

        <form action="" style="color: white; display: flex; flex-direction: column; gap: 12px; border: 1px solid white; padding: 20px; border-radius: 10px;">
            <h2>Adicionar Novo filme:</h2>
            <label>
                Nome: 
                <input type="text" name="nome" class="form"/>
            </label>
            <label>Descrição:
                <input type="text" name="descricao"  class="form"/>
            </label>
            <label>Autor:
                <input type="text" name="autor"  class="form"/>
            </label>
            <label>Indicação Etária:
                <input type="number" step="1" name="indicacaoEtaria"  class="form"/>
            </label>
            <label>Duração:
                <input type="text" name="duracao"  class="form" placeholder="1h50m"/>
            </label>
            <label>Capa link:
                <input type="text" name="capa"  class="form"/>
            </label>
            <label>Filme link:
                <input type="text" name="filme"  class="form"/>
            </label>
            <label>Ano:
                <input type="text" name="ano"  class="form"/>
            </label>

            <input type="hidden" name="disponibilidade" value="true" />

            <label>Genero:
                <input type="text" name="genero"  class="form"/>
            </label>
            <button type="submit">Adicionar</button>
        </form>
    </main>

</body>

</html>