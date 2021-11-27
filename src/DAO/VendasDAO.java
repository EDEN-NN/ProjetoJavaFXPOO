package DAO;

import entities.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
        return null;
    }

}
