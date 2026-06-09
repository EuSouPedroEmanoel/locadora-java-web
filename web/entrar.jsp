<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrar</title>
    </head>

    <body
        style="background-color: black; height: 100dvh; display: flex; flex-direction: column; gap: 4px; justify-content: center; align-items: center;">
        <center
            style="display: flex; flex-direction: column; gap: 4px; border: 1px solid white; border-radius: 10px; padding: 20px;">
            <h1 style="color: white; margin: 0; padding: 0;">
                Entrar
            </h1>

            <form action="PessoasController?op=3"
                style="display: flex; flex-direction: column; gap: 8px; margin: 0; padding: 0;" method="post">
                <label for="email" style="color: white;">Email</label>
                <input type="email" name="email" style="background-color: #2E2E2E; color: white;" />
                <br />
                <label for="password" style="color: white;">Senha</label>
                <input type="password" name="password" style="background-color: #2E2E2E; color: white;" />
                <br />
                <input type="submit" value="Entrar" style="background-color: #2E2E2E; color: white;" />
            </form>
        </center>
        <a href="index.html" style="color: white;">Voltar para a Home</a>
    </body>

    </html>