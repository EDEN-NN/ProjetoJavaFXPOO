package DAO;

import java.util.List;

public interface IProdutosDAO<T> {
    //public void adicionar(T source);
    //public void remover(T source);
    public List<T> pesquisar(String search);
}
