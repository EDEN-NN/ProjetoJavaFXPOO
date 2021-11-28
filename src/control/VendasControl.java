package control;

import DAO.VendasDAO;
import entities.*;

import java.util.List;

public class VendasControl {

    private VendasDAO dao = new VendasDAO();

    public boolean realizarVenda(Carrinho carrinho, Usuario user, Endereco endereco, FormaPagamento pagamento){

        Venda v = new Venda();
        v.setComprador(user);
        v.setFormaPagamento(pagamento);
        v.setEntrega(endereco);
        v.setFrete(10);
        v.setTotal(carrinho.total);
        v.setProdutos(carrinho.produtos);

        return dao.realizarCompra(v);
    }

    public List<Venda> listarCompras(Usuario user){
        if(user!=null)
            return dao.listarVendas(user);

        return null;
    }

}
