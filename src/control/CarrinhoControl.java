package control;

import entities.Carrinho;
import entities.Produto;

public class CarrinhoControl {

    public static Carrinho carrinho = new Carrinho();

    public boolean addProduto(Produto produto){
        var last = carrinho.produtos.size();
        carrinho.produtos.add(produto);
        if(carrinho.produtos.size() != last){
            carrinho.total+=produto.getPreco();
            return  true;
        }

        return false;
    }

    public boolean removerProduto(Produto produto){
        var last = carrinho.produtos.size();
        carrinho.produtos.remove(produto);
        if(carrinho.produtos.size() != last){
            carrinho.total-=produto.getPreco();
            return  true;
        }

        return false;
    }

    public boolean limparCarrinho(){
        var last = carrinho.produtos.size();
        carrinho.produtos.clear();
        if(carrinho.produtos.size() != last){
            carrinho.total = 0;
            return  true;
        }

        return false;
    }

}
