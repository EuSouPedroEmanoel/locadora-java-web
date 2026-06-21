<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Adicionar TubMoney</title>
    </head>
    <body style="background-color: black; color: white; font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0;">

        <div style="background-color: #1a1a1a; padding: 30px; border-radius: 8px; border: 1px solid #444; width: 300px; text-align: center;">
            <h2 style="margin-top: 0;">Adicionar Saldo</h2>

            <form action="PessoasController?op=5" method="POST">
                <input type="number" name="novoSaldo" placeholder="Valor (ex: 50.00)" step="0.01" required
                style="width: 100%; padding: 10px; margin-bottom: 20px; border-radius: 4px; border: none; box-sizing: border-box;">

                <button type="submit"
                style="width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">
                Confirmar Adição
            </button>
        </form>

        <a href="FilmesController?op=1" style="display: block; margin-top: 15px; color: white; ">Voltar</a>
    </div>

</body>
</html>