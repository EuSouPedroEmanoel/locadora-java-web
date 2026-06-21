<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="VO.Genero" %>
<%@ page import="VO.Filme" %>

<%
    Filme filme = (Filme) request.getAttribute("filme");
    boolean isEdicao = (filme != null);
%>

<html>
    <body style="margin: 0; padding: 0; background-color: black; font-family: sans-serif;">
        <div style="display: flex; justify-content: center"><a href="FilmesController?op=1" style="color: white; margin-top: 10px;">Voltar</a></div>
        <main style="display: flex; flex-direction: column; gap: 4px; justify-content: center; align-items: center; margin: 0; padding: 20px;">
            <form action="FilmesController" method="POST" style="color: white; display: flex; flex-direction: column; gap: 12px; border: 1px solid white; padding: 20px; border-radius: 10px; width: 350px;">
                <input type="hidden" name="op" value="<%= isEdicao ? "8" : "2" %>" />
                <% if (isEdicao) { %> <input type="hidden" name="id" value="<%= filme.getId() %>" /> <% } %>

                <h2><%= isEdicao ? "Editar Filme" : "Adicionar Novo Filme" %></h2>
                <label>Nome: <input type="text" name="nome" value="<%= isEdicao ? filme.getNome() : "" %>" style="width: 100%;"/></label>
                <label>Descrição: <input type="text" name="descricao" value="<%= isEdicao ? filme.getDescricao() : "" %>" style="width: 100%;"/></label>
                <label>Autor: <input type="text" name="autor" value="<%= isEdicao ? filme.getAutor() : "" %>" style="width: 100%;"/></label>
                <label>Indicação Etária: <input type="number" name="indicacaoEtaria" value="<%= isEdicao ? filme.getIndicacaoEtaria() : "" %>" style="width: 100%;"/></label>
                <label>Duração: <input type="text" name="duracao" value="<%= isEdicao ? filme.getDuracao() : "" %>" style="width: 100%;"/></label>
                <label>Capa link: <input type="text" name="capa" value="<%= isEdicao ? filme.getCapaLink() : "" %>" style="width: 100%;"/></label>
                <label>Filme link: <input type="text" name="filme" value="<%= isEdicao ? filme.getFilmeLink() : "" %>" style="width: 100%;"/></label>
                <label>Ano: <input type="text" name="ano" value="<%= isEdicao ? filme.getAno() : "" %>" style="width: 100%;"/></label>
                <input type="hidden" name="disponibilidade" value="true" />
                <label>Genero:
                    <select name="genero_id" style="width: 100%;">
                        <option value="">-- Selecione --</option>
                        <%
                            List<Genero> generos = (List<Genero>) request.getAttribute("listaGeneros");
                            if (generos != null) {
                                for (Genero g : generos) {
                                    boolean selecionado = isEdicao && filme.getGeneroId() == g.getId();
                                %>
                                <option value="<%= g.getId() %>" <%= selecionado ? "selected" : "" %>><%= g.getNome() %></option>
                                <%
                                }
                            }
                        %>
                    </select>
                </label>
                <label>Custo: <input type="number" step="0.01" name="tub_custo" value="<%= isEdicao ? filme.getTubCusto() : "" %>" style="width: 100%;"/></label>
                <button type="submit"><%= isEdicao ? "Atualizar" : "Adicionar" %></button>
            </form>
        </main>
    </body>
</html>