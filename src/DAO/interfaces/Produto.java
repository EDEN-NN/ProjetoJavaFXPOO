package DAO.interfaces;

import entities.Roupa;

import java.util.List;

public interface Produto {
    List<Roupa> listar();
    void adicionar(Roupa roupa);
    void remover();
    Roupa pesquisar(String nome);
}
