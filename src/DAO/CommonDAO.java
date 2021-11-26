package DAO;

import entities.Categoria;
import entities.Endereco;
import entities.FormaPagamento;
import entities.Marca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommonDAO implements ICommonDAO {
    @Override
    public List<FormaPagamento> listarFormasPagamentos() {
        java.sql.Connection conn = null;
        try {
            String sql ="SELECT * from forma_pagamento";

            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            List<FormaPagamento> formasPagamentos = new ArrayList<>();
            while(rs.next()){
                var fp = new FormaPagamento();
                fp.setId(rs.getInt("id"));
                fp.setDescricao(rs.getString("descricao"));

                formasPagamentos.add(fp);
            }

            return formasPagamentos;
        } catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return null;
        }finally{
            Connection.closeConnection(conn);
        }
    }

    @Override
    public List<Marca> listarMarcas() {
        return null;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return null;
    }
}
