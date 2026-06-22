<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="VO.Genero" %>

<html>

    <head>
        <title>Catálogo</title>
        <style>
            .form {
                width: 100%;
            }
        </style>
    </head>

    <body style="margin: 0; padding: 0; background-color: black;">


        <div style="display: flex; justify-content: center"><a href="FilmesController?op=1" style="color: white">Voltar</a></div>


        <main
        style="display: flex; flex-direction: column; gap: 4px; justify-content: center; align-items: center; margin: 0; padding: 20px;">

        <form action="FilmesController" method="POST" style="color: white; display: flex; flex-direction: column; gap: 12px; border: 1px solid white; padding: 20px; border-radius: 10px;">

            <input type="hidden" name="op" value="2" />

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
                <select name="genero_id" id="genero" class="form">
                    <option value="">-- Selecione um Gênero --</option>

                    <%
                        List<Genero> generos = (List<Genero>) request.getAttribute("listaGeneros");
                        
                        if (generos != null) {
                            for (Genero g : generos) {
                            %>
                            <option value="<%= g.getId() %>"> <%= g.getNome() %> </option>
                            <%
                            }
                        }
                    %>
                </select>
            </label>

            <label>
                Custo:
                <input type="number" step="0.01" name="tub_custo"  class="form"/>
            </label>

            <button type="submit">Adicionar</button>
        </form>
    </main>

</body>
</html>