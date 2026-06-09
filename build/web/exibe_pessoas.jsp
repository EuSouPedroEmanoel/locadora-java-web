<%@page import="java.util.List" %>
    <%@page import="VO.Pessoa" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Listagem</title>
                <style>
                    td {
                        padding: 5px 10px;
                        border: none;
                        border-bottom: 1px solid rgb(173, 173, 173);
                    }

                    table {
                        border-collapse: none;
                        padding: 10px;
                        border: 2px solid white;
                        border-radius: 20px;
                    }
                </style>
            </head>

            <body
                style="background-color: black; display: flex; flex-direction: column; gap: 10px; justify-content: center; align-items: center; height: 100dvh; color: white; font-size: larger;">

                <% List pessoas=(List) request.getAttribute("lista"); if (pessoas !=null) { %>

                    <center>Achados: <%= pessoas.size() %>
                    </center>

                    <% out.print("<table width=\"50%\" border=\"1\" cellspacing=\"0\" align=\"center\">"); %>
                        <tr>
                            <td>CPF</td>
                            <td>Nome</td>
                            <td>Data de Nascimento</td>
                            <td>Sexo</td>
                            <td>TubMoney</td>
                            <td>Email</td>
                            <td>Senha</td>
                            <td>Super User</td>
                        </tr>

                        <% for (int cont=0; cont < pessoas.size(); cont++) { Pessoa p=(Pessoa) pessoas.get(cont);
                            out.print("<tr>");
                            out.print("<td>" + p.getCpf() + "</td>");
                            out.print("<td>" + p.getNome() + "</td>");
                            out.print("<td>" + p.getDataNascimento() + "</td>");
                            out.print("<td>" + p.getSexo() + "</td>");
                            out.print("<td>" + p.getTubMoney() + "</td>");
                            out.print("<td>" + p.getEmail() + "</td>");
                            out.print("<td>" + p.getSenha() + "</td>");
                            out.print("<td>" + p.getSuper_user() + "</td>");
                            out.print("</tr>");
                            }
                            out.print("</table>");
                            }
                            %>
                            <center><a href="index.html" style="color: white;">Página inicial</a></center>

            </body>

            </html>