package DAO;

import entities.Roupa;

import java.util.List;

public interface IProduto<T> {
    public List<T> listar();
    public void adicionar(T source);
    public void remover(T source);
    public T pesquisar(String search);
}
