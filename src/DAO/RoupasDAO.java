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
    public void adicionar() {

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
