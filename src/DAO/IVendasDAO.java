package DAO;

import entities.*;

import java.sql.SQLException;
import java.util.List;

public interface IVendasDAO {

    public boolean realizarCompra(Venda venda) throws SQLException;
    public List<Venda> listarVendas(Usuario user);

}
