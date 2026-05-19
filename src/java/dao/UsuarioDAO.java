package dao;

import conexao.Conexao;
import vo.UsuarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    private final Conexao conexao;

    public UsuarioDAO() {
        conexao = new Conexao();
    }

    public boolean inserir(Usuario u) {
        String sql = "INSERT INTO usuario (nome, data_nascimento, cash, sexo, senha, nivel_acesso, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDataNascimento());
            ps.setString(3, u.getCash());
            ps.setString(4, u.getSexo());
            ps.setString(5, u.getSenha());
            ps.setString(6, u.getNivelAcesso());
            ps.setString(7, u.getEmail());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException erro) {
            System.err.println("Exceção causada na inserção: " + erro.getMessage());
            return false;
        } finally {
            conexao.desconectar();
        }
    }
}
