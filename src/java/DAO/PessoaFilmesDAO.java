package DAO;

import Conexao.Conexao;
import VO.PessoaFilme;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PessoaFilmesDAO {
    private final Conexao conexao;

    public PessoaFilmesDAO() {
        conexao = new Conexao();
    }

    public ArrayList<PessoaFilme> listar() {
        String sql = "SELECT id, pessoa_id, filme_id, data_emprestimo, data_devolucao_prev, data_devolucao_real FROM pessoa_filme";
        ArrayList<PessoaFilme> lista = new ArrayList<>();
        
        try (var conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                PessoaFilme pf = new PessoaFilme();
                pf.setId(rs.getInt("id"));
                pf.setCpf(rs.getString("pessoa_id"));
                pf.setFilmeId(rs.getInt("filme_id"));
                pf.setDataEmprestimo(rs.getString("data_emprestimo"));
                
                String prev = rs.getString("data_devolucao_prev");
                if (prev != null) pf.setDataDevolucaoPrev(LocalDate.parse(prev));
                
                String real = rs.getString("data_devolucao_real");
                if (real != null) pf.setDataDevolucaoReal(LocalDate.parse(real));
                
                lista.add(pf);
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            conexao.desconectar();
        }
        return lista;
    }

    public String inserir(PessoaFilme pf) {
        String sql = "INSERT INTO pessoa_filme (pessoa_id, filme_id, data_devolucao_prev) VALUES (?, ?, ?)";
        
        try (var conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, pf.getCpf());
            ps.setInt(2, pf.getFilmeId());
            ps.setString(3, pf.getDataDevolucaoPrev().toString());
            
            ps.executeUpdate();
            return "True";
        } catch (SQLException erro) {
            erro.printStackTrace();
            return "False";
        } finally {
            conexao.desconectar();
        }
    }

    public void finalizarEmprestimo(int filmeId, String cpf) {
        String sql = "UPDATE pessoa_filme SET data_devolucao_real = ? WHERE filme_id = ? AND pessoa_id = ? AND data_devolucao_real IS NULL";
        
        try (var conn = conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, filmeId);
            ps.setString(3, cpf);
            ps.executeUpdate();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            conexao.desconectar();
        }
    }

    public String buscarCpfDonoDoFilme(int filmeId) {
    String sql = "SELECT pessoa_id FROM pessoa_filme WHERE filme_id = ? AND data_devolucao_real IS NULL";
    try (var conn = conexao.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, filmeId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("pessoa_id");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        conexao.desconectar();
    }
    return null;
}
}