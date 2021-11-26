package DAO;

import entities.*;

import java.util.List;

public class VendasDAO implements IVendasDAO {

    @Override
    public boolean realizarCompra(Carrinho carrinho, Usuario user, Endereco endereco, FormaPagamento formaPagamento) {

        java.sql.Connection conn = null;
        try {
            conn = Connection.getConnection();

        } catch (Exception e){
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
