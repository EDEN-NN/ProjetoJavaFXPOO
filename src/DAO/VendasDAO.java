package DAO;

import entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendasDAO implements IVendasDAO {

    @Override
    public boolean realizarCompra(Venda venda) {

        java.sql.Connection conn = null;
        try {
            conn = Connection.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO venda (total, frete, id_forma_pagamento, id_usuario, id_endereco, inserted) "
                    + "VALUES ( ? , ? , ? , ? , ? , ?)";

            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setDouble(1, venda.getTotal());
            pstm.setDouble(2, venda.getFrete());
            pstm.setInt(3, venda.getFormaPagamento().getId());
            pstm.setInt(4, venda.getComprador().getId());
            pstm.setInt(5, venda.getEntrega().getId());
            pstm.setDate(6, Date.valueOf(LocalDate.now()));
            var exec = pstm.execute();

            if(exec){
                try (var generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int total = 0;
                        for (var prod : venda.getProdutos()) {
                            String sql2 = "INSERT INTO venda_produtos (id_produto, id_venda, preco_unitario, inserted) " +
                                    "VALUES ( ? , ? , ? , ? )";

                            var pstm2 = conn.prepareStatement(sql2);
                            pstm2.setInt(1, prod.getId());
                            pstm2.setInt(2, generatedKeys.getInt("id"));
                            pstm2.setDouble(3, venda.getFormaPagamento().getId());
                            pstm2.setDate(4, Date.valueOf(LocalDate.now()));
                            var value = pstm2.execute();
                            if(value)
                                total++;
                        }

                        if(total != venda.getProdutos().size())
                            throw new Exception("Products insert error.");

                        conn.commit();
                        return true;
                    }
                    else {
                        throw new Exception("No ID obtained.");
                    }
                }
            }
            conn.rollback();
            return false;
        } catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return false;
        } finally {
            Connection.closeConnection(conn);
        }
    }

    @Override
    public List<Venda> listarVendas(Usuario user) {

        java.sql.Connection conn = null;
        try {
            String sql ="SELECT venda.id, total, frete, id_forma_pagamento, forma_pagamento.descricao, logradouro, numero, cep, bairro, cidade, estado, complemento FROM venda\n" +
                    "  INNER JOIN forma_pagamento ON id_forma_pagamento = forma_pagamento.id\n" +
                    "  INNER JOIN endereco ON id_endereco = endereco.id\n" +
                    "  WHERE venda.id_usuario = ?";

            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, user.getId());

            ResultSet rs = pstm.executeQuery();

            List<Venda> vendas = new ArrayList<>();
            while(rs.next()){
                var v = new Venda();
                v.setId(rs.getInt("id"));
                v.setTotal(rs.getDouble("total"));
                v.setFrete(rs.getDouble("frete"));

                var p = new FormaPagamento();
                p.setId(rs.getInt("id_forma_pagamento"));
                p.setDescricao(rs.getString("descricao"));

                v.setFormaPagamento(p);

                var e = new Endereco();
                e.setLogradouro(rs.getString("logradouro"));
                e.setNumero(rs.getString("numero"));
                e.setCep(rs.getString("cep"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));
                e.setComplemento(rs.getString("complemento"));

                v.setEntrega(e);

                vendas.add(v);
            }

            String sqlProd = "SELECT preco_unitario, produto.id, produto.nome, descricao, tamanho, marca.nome as 'nome_marca', categoria.nome as 'nome_categoria' FROM venda_produtos\n" +
                    "  INNER JOIN produto ON id_produto = produto.id\n" +
                    "  INNER JOIN marca ON produto.id_marca = marca.id\n" +
                    "  INNER JOIN categoria ON produto.id_categoria = categoria.id\n" +
                    "  WHERE id_venda = ?";

            for (var venda : vendas) {
                var pstm2 = conn.prepareStatement(sqlProd);
                pstm2.setInt(1, venda.getId());
                var rs2 = pstm2.executeQuery();

                List<Produto> produtos = new ArrayList<>();
                while(rs2.next()){
                    var p = new Produto();
                    p.setId(rs2.getInt("id"));
                    p.setNome(rs2.getString("nome"));
                    p.setDescricao(rs2.getString("descricao"));
                    p.setTamanho(rs2.getString("tamanho"));

                    var m = new Marca();
                    m.setNome(rs2.getString("nome_marca"));

                    p.setMarca(m);

                    var c = new Categoria();
                    c.setNome(rs2.getString("nome_categoria"));

                    p.setCategoria(c);

                    produtos.add(p);
                }

                venda.setProdutos(produtos);
            }

            return vendas;
        } catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return null;
        }finally{
            Connection.closeConnection(conn);
        }

    }

}
