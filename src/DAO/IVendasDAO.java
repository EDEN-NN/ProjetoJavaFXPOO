package DAO;

import entities.Usuario;
import entities.Venda;

import java.util.List;

public interface IVendasDAO {

    public boolean realizarCompra(Venda venda);
    public List<Venda> listarVendas(Usuario user);

}
