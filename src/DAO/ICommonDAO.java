package DAO;

import entities.Categoria;
import entities.FormaPagamento;
import entities.Marca;

import java.util.List;

public interface ICommonDAO {
    public List<FormaPagamento> listarFormasPagamentos();
    public List<Marca> listarMarcas();
    public List<Categoria> listarCategorias();
}
