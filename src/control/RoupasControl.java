package control;

import DAO.RoupasDAO;
import entities.Roupa;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class RoupasControl {


    RoupasDAO control = new RoupasDAO();

    public void Adicionar(Roupa roupa) {
        if (roupa != null) {
            control.adicionar(roupa);
        }
    }

    public List<Roupa> pesquisar() {
        return null;
    }

    public Roupa pesquisarPorNome(String nome) {
        if (nome != null) {
            Roupa roupa = new Roupa();
            roupa = control.pesquisar(nome);
            return roupa;
        } else {
            return null;
        }
    }

}
