package DAO;

import entities.Usuario;
import entities.Venda;

import java.util.List;

public interface IVenda {

    public boolean realizarCompra(Venda venda);
    public List<Venda> listarVendas(Usuario user);

}
