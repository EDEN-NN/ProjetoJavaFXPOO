package DAO;

import entities.*;

import java.util.List;

public interface IVendasDAO {

    public boolean realizarCompra(Carrinho carrinho, Usuario user, Endereco endereco, FormaPagamento formaPagamento);
    public List<Venda> listarVendas(Usuario user);

}
