<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Soma MVC</title>
    </head>

    <body style="background-color: black; height: 100dvh; display: flex; justify-content: center; align-items: center;">
        <center
        style="display: flex; flex-direction: column; gap: 4px; border: 1px solid white; border-radius: 10px; padding: 20px;">
        <h1 style="color: white;">
            <% boolean resultado=Boolean.parseBoolean(request.getParameter("result")); if (resultado) {
            out.print("Sucesso ao inserir"); } else { out.print("Erro ao inserir"); } %>
        </h1>

        <a href="FilmesController?op=1" style="color: white; font-size: larger;">Ir para o catálogo</a>
        <a href="index.html" style="color: white; font-size: larger;">Voltar para Home</a>
    </center>
</body>

</html>