package DAO;

import Conexao.Conexao;
import VO.Filme;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmesDAO {
    private final Conexao conexao;

    public FilmesDAO() {
        conexao = new Conexao();
    }

    public ArrayList<Filme> listar() {
        PreparedStatement ps;
        ResultSet rs;

        try {
            // Selecionando todas as colunas
            String sql = "SELECT * FROM filmes";
            ps = conexao.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Filme> lista = new ArrayList<>();

            while (rs.next()) {
                Filme f = carregarFilme(rs);
                lista.add(f);
            }
            return lista;
        } catch (SQLException erro) {
            erro.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }

    public Filme buscarPorId(int id) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM filmes WHERE id = ?";
            ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return carregarFilme(rs);
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            conexao.desconectar();
        }
        return null;
    }

    // Método auxiliar para evitar repetição de código
    private Filme carregarFilme(ResultSet rs) throws SQLException {
        Filme f = new Filme();
        f.setId(rs.getInt("id"));
        f.setNome(rs.getString("nome"));
        f.setDescricao(rs.getString("descricao"));
        f.setAutor(rs.getString("autor"));
        f.setIndicacaoEtaria(rs.getInt("indicacao_etaria"));
        f.setCapaLink(rs.getString("capa_link"));
        f.setFilmeLink(rs.getString("filme_link"));
        f.setAno(rs.getInt("ano"));
        f.setDuracao(rs.getString("duracao"));
        f.setDisponibilidade(rs.getBoolean("disponibilidade"));
        f.setGeneroId(rs.getInt("genero_id"));
        f.setTubCusto(rs.getDouble("tub_custo"));
        return f;
    }

    public String inserir(Filme f) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO filmes (nome, descricao, autor, indicacao_etaria, capa_link, filme_link, ano, duracao, disponibilidade, genero_id, tub_custo) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, f.getNome());
            ps.setString(2, f.getDescricao());
            ps.setString(3, f.getAutor());
            ps.setInt(4, f.getIndicacaoEtaria());
            ps.setString(5, f.getCapaLink());
            ps.setString(6, f.getFilmeLink());
            ps.setInt(7, f.getAno()); 
            ps.setString(8, f.getDuracao());
            ps.setBoolean(9, f.getDisponibilidade());
            ps.setInt(10, f.getGeneroId());
            ps.setDouble(11, f.getTubCusto());
            
            ps.executeUpdate();
            return "True";
        } catch (SQLException erro) {
            System.err.println("ERRO SQL: " + erro.getMessage());
            return "False";
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            conexao.desconectar();
        }
    }

    public void atualizarDisponibilidade(int id, boolean status) {
    try {
        String sql = "UPDATE filmes SET disponibilidade = ? WHERE id = ?";
        PreparedStatement ps = conexao.conectar().prepareStatement(sql);
        ps.setBoolean(1, status);
        ps.setInt(2, id);
        ps.executeUpdate();
    } catch (SQLException e) { e.printStackTrace(); }
    finally { conexao.desconectar(); }
}
}