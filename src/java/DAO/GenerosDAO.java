package DAO;

import Conexao.Conexao;
import VO.Genero;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenerosDAO {
    private final Conexao conexao;

    public GenerosDAO() {
        conexao = new Conexao();
    }

    public ArrayList<Genero> listar() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "select id,nome from genero";
            ps = conexao.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<Genero> lista = new ArrayList<>();
            while (rs.next()) {
                Genero g = new Genero();
                g.setId(rs.getInt("id"));
                g.setNome(rs.getString("nome"));
                lista.add(g);
            }
            return lista;
        }
            catch (SQLException erro) {
            erro.printStackTrace();
            return null;
        } finally {
            conexao.desconectar();
        }
    }
}