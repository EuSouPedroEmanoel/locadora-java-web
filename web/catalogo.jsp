<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Catálogo</title>
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
                
                VO.Pessoa user = (VO.Pessoa) session.getAttribute("usuarioLogado");
                if (user.getSuper_user()) {
                %>
                <a href="adicionarFilme.jsp" style="color: white;">+ Adicionar Filme</a>
                <%
                }
            %>

            <div style="display: flex; gap: 14px;">
                olá, ${sessionScope.usuarioLogado.nome} 🤠👋
                <a href="LogoutServlet" style="color: white;">Sair</a>
            </div>

            <% } else { %>
            <div style="display: flex; gap: 14px;">
                <a href="entrar.jsp" style="color: white;">Entrar</a>
                <a href="inserir.jsp" style="color: white;">Cadastrar</a>
            </div>
            <% } %>

        </nav>
        <main
        style="background-color: black; height: 100%; display: flex; flex-direction: column; gap: 4px; justify-content: center; align-items: center; margin: 0; padding: 0">
    </main>

</body>

</html>