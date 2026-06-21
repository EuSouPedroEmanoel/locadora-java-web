package DAO;

import Conexao.Conexao;
import VO.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoasDAO {
    private final Conexao conexao;

    public PessoasDAO() {
        conexao = new Conexao();
    }

    public String inserir(Pessoa p) {
        try {
            PreparedStatement ps;
            String sql = "insert into pessoa (cpf,nome, data_nascimento, sexo, tub_money, email, senha, super_user) values (?,?,?,?,?,?,?,?)";
            ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, p.getCpf());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getDataNascimento());
            ps.setString(4, p.getSexo());
            ps.setString(5, p.getTubMoney());
            ps.setString(6, p.getEmail());
            ps.setString(7, p.getSenha());
            ps.setBoolean(8, p.getSuper_user());
            ps.executeUpdate();
            return "True";
        } catch (SQLException erro) {
            return "False";
        } finally {
            conexao.desconectar();
        }
    }

    public void atualizarSaldo(String cpf, double novoSaldo) {
        try {
            String sql = "UPDATE pessoa SET tub_money = ? WHERE cpf = ?";
            PreparedStatement ps = conexao.conectar().prepareStatement(sql);
            ps.setDouble(1, novoSaldo);
            ps.setString(2, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar();
        }
    }

    public ArrayList<Pessoa> listar() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "select cpf,nome,data_nascimento,sexo,tub_money,email,senha, super_user from pessoa";
            ps = conexao.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Pessoa> lista = new ArrayList<>();            
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getString("data_nascimento"));
                p.setSexo(rs.getString("sexo"));
                p.setTubMoney(rs.getString("tub_money"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                p.setSuper_user(rs.getBoolean("super_user"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException erro) {
            erro.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }

    public Pessoa buscarPorLogin(String email, String senha) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "select cpf,nome,data_nascimento,sexo,tub_money,email,senha,super_user from pessoa where email = ? and senha = ?";
            ps = conexao.conectar().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getString("data_nascimento"));
                p.setSexo(rs.getString("sexo"));
                p.setTubMoney(rs.getString("tub_money"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                p.setSuper_user(rs.getBoolean("super_user"));
                return p; 
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            conexao.desconectar();
        }
        return null; 
    }
}