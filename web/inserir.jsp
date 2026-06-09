<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir</title>
    </head>

    <body
        style="background-color: black; height: 100dvh; display: flex; flex-direction: column; justify-content: center; align-items: center;">

        <h2 style="color: white;">Cadastrar</h2>
        <form name="frm" method="post" action="PessoasController?op=1"
            style="border: 1px solid white; padding: 10px; width: 500px; height: 600px; display: flex; justify-content: center; align-items: center; flex-direction: column; gap: 8px; border-radius: 10px">
            <label for="nome" style="color: white;">Nome</label>
            <input type="text" name="nome" style="background-color: #2E2E2E; color: white;">

            <label for="cpf" style="color: white;">CPF</label>
            <input type="text" name="cpf" style="background-color: #2E2E2E; color: white;">

            <label for="date" style="color: white;">Data de Nascimento</label>
            <input type="date" name="data_nascimento" style="background-color: #2E2E2E; color: white;" />

            <p style="color: white; margin: 0; padding: 0;">Sexo</p>
            <div style="display: flex; flex-direction: column;">
                <label style="color: white;">
                    <input type="radio" value="M" name="sexo" />
                    Masculino
                </label>
                <label style="color: white;">
                    <input type="radio" value="F" name="sexo" />
                    Feminino
                </label>
                <label style="color: white;">
                    <input type="radio" value="O" name="sexo" />
                    Outro
                </label>
            </div>


            <label for="tub_money" style="color: white;">Adicionar TubMoney</label>
            <input type="number" step="0.01" name="tub_money" style="background-color: #2E2E2E; color: white;" />

            <label for="email" style="color: white;">Email</label>
            <input type="email" name="email" style="background-color: #2E2E2E; color: white;" />

            <label for="password" style="color: white;">Senha</label>
            <input type="password" name="senha" style="background-color: #2E2E2E; color: white;" />
            <br />

            <label for="super_user" style="color: white;">Definir como super usuário?</label>
            <input type="checkbox" name="super_user" value="true" style="height: 30px; width: 30px;" />

            <input type="submit" value="Cadastrar" style="background-color: #2E2E2E; color: white;" />


        </form>

        <a href="index.html" style="color: white;">Voltar para Home</a>
    </body>

    </html>