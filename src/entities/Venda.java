package entities;

import java.util.List;

public class Venda extends AbstractObjectModel<Integer>{

    private List<Produto> produtos;
    private double total;
    private FormaPagamento formaPagamento;
    private double frete;
    private Endereco entrega;
    private Usuario comprador;

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public Endereco getEntrega() {
        return entrega;
    }

    public void setEntrega(Endereco entrega) {
        this.entrega = entrega;
    }
}
