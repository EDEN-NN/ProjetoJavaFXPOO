//package control;
//
//import DAO.ProdutosDAO;
//import entities.Roupa;
//
//import java.util.List;
//
//public class ProdutosControl {
//
//
//    ProdutosDAO control = new ProdutosDAO();
//
//    public void Adicionar(Roupa roupa) {
//        if (roupa != null) {
//            control.adicionar(roupa);
//        }
//    }
//
//    public List<Roupa> pesquisar() {
//        return null;
//    }
//
//    public Roupa pesquisarPorNome(String nome) {
//        if (nome != null) {
//            Roupa roupa = new Roupa();
//            roupa = control.pesquisar(nome);
//            return roupa;
//        } else {
//            return null;
//        }
//    }
//
//}
