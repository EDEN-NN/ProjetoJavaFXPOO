package control;

import DAO.ProdutosDAO;
import entities.Produto;

import java.util.List;

public class ProdutosControl {

    ProdutosDAO dao = new ProdutosDAO();

    public List<Produto> pesquisar(String search){

        return dao.pesquisar(search);
    }

}
