package DAO;

import Conexao.Conexao;
import VO.PessoaFilme;
import VO.Pessoa;
import VO.Filme;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaFilmesDAO {
    private final Conexao conexao;

    public PessoaFilmesDAO() {
        conexao = new Conexao();
    }

    public ArrayList<PessoaFilme> listar() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "select id,cpf,filme_id,data_emprestimo,data_devolucao_prev,data_devolucao_real from pessoa_filme";
            ps = conexao.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<PessoaFilme> lista = new ArrayList<>();
            while (rs.next()) {
                PessoaFilme pf = new PessoaFilme();
                pf.setId(rs.getInt("id"));
                pf.setCpf(rs.getString("cpf"));
                pf.setFilmeId(rs.getInt("filme_id"));
                pf.setDataEmprestimo(rs.getString("data_emprestimo"));
                pf.setDataDevolucaoPrev(rs.getString("data_devolucao_prev"));
                pf.setDataDevolucaoReal(rs.getString("data_devolucao_real"));
                lista.add(pf);
            }
            return lista;
        } catch (SQLException erro) {
            erro.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }

    public String inserir(PessoaFilme pf) {
        try {
            PreparedStatement ps;
            String sql = "insert into pessoa_filme (cpf,filme_id,data_devolucao_prev,data_devolucao_real) values (?,?,?,?)";
            ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, pf.getCpf());
            ps.setInt(2, pf.getFilmeId());
            ps.setString(3, pf.getDataDevolucaoPrev());
            ps.setString(4, pf.getDataDevolucaoReal());
            ps.executeUpdate();
            return "True";
        } catch (SQLException erro) {
            return "False";
        } finally {
            conexao.desconectar();
        }
    }
}