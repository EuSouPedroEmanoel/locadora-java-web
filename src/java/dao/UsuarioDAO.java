package dao;

import conexao.Conexao;
import vo.UsuarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public void cadastrar(UsuarioVO usuario) {
        // O SQL de Inserção. Os pontos de interrogação são espaços em branco
        // que vamos preencher com os dados do usuário. (Não inserimos o ID porque é automático).
        String sql = "INSERT INTO usuarios (nome, data_nascimento, cash, sexo, senha, nivel_acesso, email, data_criacao) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Conexao db = new Conexao();
        Connection conn = db.estabeleceConexao();
        
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                // Preenchendo os pontos de interrogação com os dados do VO
                stmt.setString(1, usuario.getNome());
                stmt.setDate(2, usuario.getDataNascimento());
                stmt.setDouble(3, usuario.getCash());
                stmt.setString(4, usuario.getSexo());
                stmt.setString(5, usuario.getSenha());
                stmt.setInt(6, usuario.getNivelAcesso());
                stmt.setString(7, usuario.getEmail());
                stmt.setTimestamp(8, usuario.getDataCriacao());
                
                // Executa o comando no banco
                stmt.execute();
                System.out.println("Usuário cadastrado com sucesso!");
                
            } catch (SQLException e) {
                System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            } finally {
                db.desconectar();
            }
        }
    }
}