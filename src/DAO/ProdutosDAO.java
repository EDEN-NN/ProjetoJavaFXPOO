package DAO;


import entities.Categoria;
import entities.Marca;
import entities.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO implements IProdutosDAO<Produto> {

    @Override
    public List<Produto> pesquisar(String search) {
        java.sql.Connection conn = null;
        try {
            String sql ="SELECT preco, quantidade, produto.nome, produto.descricao, produto.tamanho, produto.id AS `id_produto`, marca.nome AS `marca`, categoria.nome AS `categoria`\n" +
                    "  FROM estoque\n" +
                    "  INNER JOIN produto ON estoque.id_produto = produto.id\n" +
                    "  INNER JOIN marca ON produto.id_marca = marca.id\n" +
                    "  INNER JOIN categoria ON produto.id_categoria = categoria.id\n" +
                    "  WHERE produto.nome LIKE ? OR produto.descricao LIKE ?";
            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + search + "%");
            pstm.setString(2, "%" + search + "%");

            ResultSet rs = pstm.executeQuery();

            List<Produto> produtos = new ArrayList<>();
            while(rs.next()){

                var prod = new Produto();
                prod.setPreco(rs.getDouble("preco"));
                prod.setQuantidade(rs.getInt("quantidade"));
                prod.setNome(rs.getString("nome"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setTamanho(rs.getString("tamanho"));
                prod.setId(rs.getInt("id_produto"));

                var marca = new Marca();
                marca.setNome(rs.getString("marca"));
                prod.setMarca(marca);

                var categoria = new Categoria();
                categoria.setNome(rs.getString("categoria"));
                prod.setCategoria(categoria);

                produtos.add(prod);
            }
            return produtos;
        } catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return null;
        }finally{
            Connection.closeConnection(conn);
        }
    }

}