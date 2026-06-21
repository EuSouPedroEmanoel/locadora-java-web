<%@ page import="java.util.List" %>
<%@ page import="VO.PessoaFilme" %>
<%@ page import="VO.Pessoa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>Meu Perfil - Locações</title>
    </head>
    <body style="font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: black; color: white;">

        <nav style="display: flex; justify-content: space-between; align-items: center; padding: 15px; background-color: #2e2e2e; position: sticky; top: 0; border-bottom: 1px solid #444; z-index: 100;">
            <div style="font-size: 1.2em; font-weight: bold;">
                Histórico de Empréstimos - ${sessionScope.usuarioLogado.nome}
            </div>
            <div>
                <a href="FilmesController?op=1" style="color: white; text-decoration: none; margin-right: 15px;">Voltar ao Catálogo</a>
                <a href="PessoasController?op=4" style="color: #ff4c4c; text-decoration: none;">Sair</a>
            </div>
        </nav>

        <main>
            <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; padding: 20px; max-width: 1000px; margin: 0 auto;">
                <%
                    List<PessoaFilme> lista = (List<PessoaFilme>) request.getAttribute("listaFilmes");
                    
                    if (lista != null && !lista.isEmpty()) {
                        for (PessoaFilme item : lista) {
                        %>
                        <div style="border: 1px solid #444; padding: 15px; text-align: center; background-color: #1a1a1a; border-radius: 8px; display: flex; flex-direction: column; justify-content: space-between;">

                            <img src="<%= item.getCapaFilme() != null ? item.getCapaFilme() : "https://via.placeholder.com/200x300?text=Sem+Capa" %>" alt="Capa do Filme" style="max-width: 100%; height: 300px; object-fit: cover; border-radius: 4px; margin-bottom: 15px;">

                            <h3 style="margin-top: 0;"><%= item.getNomeFilme() != null ? item.getNomeFilme() : "Filme ID: " + item.getFilmeId() %></h3>

                            <div>
                                <p style="font-size: 0.9em; margin: 5px 0; color: #ccc;">Alugado em: <%= item.getDataEmprestimo() %></p>

                                <%
                                    // LÓGICA DE STATUS: Devolvido (No Prazo/Atrasado), Pendente ou Atrasado
                                    if (item.getDataDevolucaoReal() != null) {
                                        // Verifica se entregou depois do prazo
                                        boolean entregueAtrasado = item.getDataDevolucaoPrev() != null && item.getDataDevolucaoReal().isAfter(item.getDataDevolucaoPrev());
                                        
                                        if (entregueAtrasado) {
                                        %>
                                        <p style="color: #ff8c00; font-weight: bold; margin: 5px 0;">⚠ Devolvido (Atrasado)</p>
                                        <p style="font-size: 0.9em; margin: 5px 0; color: #ccc;">Data de Entrega: <%= item.getDataDevolucaoReal() %></p>
                                        <p style="font-size: 0.8em; margin: 0; color: #999;">O prazo era: <%= item.getDataDevolucaoPrev() %></p>
                                        <%      } else { %>
                                        <p style="color: #00cc44; font-weight: bold; margin: 5px 0;">✔ Devolvido no prazo</p>
                                        <p style="font-size: 0.9em; margin: 5px 0; color: #ccc;">Data de Entrega: <%= item.getDataDevolucaoReal() %></p>
                                        <%
                                        }
                                    } else {
                                        // Pega a data de hoje para comparar com a data prevista
                                        java.time.LocalDate hoje = java.time.LocalDate.now();
                                        boolean estaAtrasado = item.getDataDevolucaoPrev() != null && item.getDataDevolucaoPrev().isBefore(hoje);
                                        
                                        if (estaAtrasado) {
                                        %>
                                        <p style="color: #ff4c4c; font-weight: bold; margin: 5px 0;">🚨 Atrasado</p>
                                        <p style="font-size: 0.9em; margin: 5px 0; color: #ffcccc;">Prazo Final era: <%= item.getDataDevolucaoPrev() %></p>
                                        <a href="FilmesController?op=5&id=<%= item.getFilmeId() %>" style="display: inline-block; margin-top: 15px; padding: 10px; background-color: #cc0000; color: white; text-decoration: none; border-radius: 4px; font-weight: bold;">Devolver Agora</a>
                                        <%      } else { %>
                                        <p style="color: #ffcc00; font-weight: bold; margin: 5px 0;">⏳ Pendente</p>
                                        <p style="font-size: 0.9em; margin: 5px 0; color: #ccc;">Prazo Final: <%= item.getDataDevolucaoPrev() %></p>
                                        <a href="FilmesController?op=5&id=<%= item.getFilmeId() %>" style="display: inline-block; margin-top: 15px; padding: 10px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; font-weight: bold;">Devolver Agora</a>
                                        <%      }
                                        }
                                    %>
                                </div>
                            </div>
                            <%
                            }
                        } else {
                        %>
                        <div style="grid-column: span 3; text-align: center; padding: 50px; color: #aaa;">
                            <h2>Nenhum histórico de locação encontrado.</h2>
                            <p>Explore o catálogo e alugue seus filmes favoritos!</p>
                        </div>
                        <%
                        }
                    %>
                </div>
            </main>

        </body>
    </html>