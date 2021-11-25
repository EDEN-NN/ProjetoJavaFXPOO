package DAO;

import entities.Usuario;
import entities.Venda;

import java.util.List;

public class VendasDAO implements IVenda{

    @Override
    public boolean realizarCompra(Venda venda) {
        return false;
    }

    @Override
    public List<Venda> listarVendas(Usuario user) {
        return null;
    }

}
