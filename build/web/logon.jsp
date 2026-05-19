<%-- 
    Document   : logon
    Created on : 19 de mai. de 2026, 14:13:38
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
           <a href="/Locadora"><h1>YouCadora</h1></a>
           <hr/>
            <div>
                <h2>Cadastrar</h2>
                <div>
                    <form>
                        <input
                            name="nome"
                            type="text" placeholder="Ex: José da Silva Júnior"/>
                        <input
                            name="data_nascimento"
                            type="date"/>
                        <input 
                            name="email"
                            type="email" placeholder="Ex: jose@email.com"/>
                        <input 
                            name="sexo"
                            type="radio" value="Masculino"/>
                        <input 
                            name="sexo"
                            type="radio" value="Feminino"/>
                        <input 
                            name="sexo"
                            type="radio" value="Outro"/>
                        <input 
                            name="cash"
                            type="number" step="0.01" />
                        <input 
                            name="nivel_acesso"
                            type="checkbox"/>
                        <input 
                            name="senha"
                            type="password" placeholder="Defina uma senha"/>
                        <input 
                            name="repetir_senha"
                            type="password" placeholder="Repita a sua senha"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
