package DAO;

import DAO.interfaces.Produto;
import entities.Roupa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class RoupasDAO implements Produto {


    @Override
    public List<Roupa> listar() {
        java.sql.Connection conn = null;
        try {
           conn = Connection.getConnection();
           PreparedStatement ps = conn.prepareStatement("SELECT * FROM roupas");
           ResultSet rs = ps.executeQuery();
           while(rs.next()) {
            Roupa roupa = new Roupa();
            roupa.setId(rs.getInt("id"));
            roupa.setNome(rs.getString("nome"));
            roupa.setModelo(rs.getString("modelo"));
            roupa.setPreco(rs.getDouble("preco"));
            roupa.setQuantidade(rs.getInt("quantidade"));
           }
           return null;
        }catch(Exception e) {
            System.out.println(e.getMessage() + "/n/n" + e.getStackTrace());
            return null;
        } finally {
            Connection.closeConnection(conn);
        }

    }

    @Override
    public void adicionar(Roupa roupa) {
        java.sql.Connection conn = null;
        if (roupa != null) {
            try {
                conn = Connection.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO roupas (id ,nome, modelo, preco, quantidade) " +
                        "VALUES (? ,?, ?, ?, ?)" );
                ps.setInt(1, roupa.getId());
                ps.setString(2, roupa.getNome());
                ps.setString(3, roupa.getModelo());
                ps.setDouble(4, roupa.getPreco());
                ps.setInt(5, roupa.getQuantidade());
                ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                e.getMessage();
            } finally {
                Connection.closeConnection(conn);
            }
        }
    }

    @Override
    public void remover() {

    }

    @Override
    public Roupa pesquisar(String nome) {
        java.sql.Connection conn = null;
        try {
            conn = Connection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM roupas " +
                    "WHERE nome like ? ");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            Roupa roupa = new Roupa();
            while (rs.next()) {
                if (rs.getString("nome").contains(nome)) {

                    roupa.setId(rs.getInt("id"));
                    roupa.setNome(rs.getString("nome"));
                    roupa.setModelo(rs.getString("modelo"));
                    roupa.setPreco(rs.getDouble("preco"));
                    roupa.setQuantidade(rs.getInt("quantidade"));
                }
            }
            return roupa;
        }catch (Exception e){
            System.out.println(e.getMessage() + "/n/n" + e.getStackTrace());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Tentano conexao");
    }

}
