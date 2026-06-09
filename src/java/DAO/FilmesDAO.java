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
            String sql = "SELECT id, nome, capa_link FROM filmes";
            ps = conexao.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Filme> lista = new ArrayList<>();

            while (rs.next()) {
                Filme f = new Filme();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCapaLink(rs.getString("capa_link"));
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
            String sql = "SELECT id, nome, capa_link FROM filmes WHERE id = ?";
            ps = conexao.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Filme f = new Filme();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCapaLink(rs.getString("capa_link"));
                return f;
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            conexao.desconectar();
        }
        return null;
    }

    public String inserir(Filme f) {
    PreparedStatement ps = null;
    try {
        // SQL alinhado com os nomes exatos da sua imagem
        String sql = "INSERT INTO filmes (nome, descricao, autor, indicacao_etaria, capa_link, filme_link, ano, duracao, disponibilidade, genero_id, tub_custo) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        ps = conexao.conectar().prepareStatement(sql);
        
        ps.setString(1, f.getNome());
        ps.setString(2, f.getDescricao());
        ps.setString(3, f.getAutor());
        ps.setInt(4, f.getIndicacaoEtaria());
        ps.setString(5, f.getCapaLink());
        ps.setString(6, f.getFilmeLink());
        
        // Se o banco for tipo YEAR, tente passar como String se o int falhar
        ps.setInt(7, f.getAno()); 
        
        ps.setString(8, f.getDuracao());
        // Tinyint no MySQL funciona com boolean, mas caso dê erro, use setInt(9, f.getDisponibilidade() ? 1 : 0);
        ps.setBoolean(9, f.getDisponibilidade());
        ps.setInt(10, f.getGeneroId());
        ps.setDouble(11, f.getTubCusto());
        
        ps.executeUpdate();
        return "True";
    } catch (SQLException erro) {
        // Isso vai imprimir o erro exato no seu Output do NetBeans
        System.err.println("ERRO SQL: " + erro.getMessage());
        erro.printStackTrace();
        return "False";
    } finally {
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conexao.desconectar();
    }
}
}