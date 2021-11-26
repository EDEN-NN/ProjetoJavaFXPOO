package control;

import DAO.CommonDAO;
import entities.FormaPagamento;

import java.util.List;

public class CommonControl {

    CommonDAO dao = new CommonDAO();

    public List<FormaPagamento> listarFormasPagamentos(){
        return dao.listarFormasPagamentos();
    }

}
