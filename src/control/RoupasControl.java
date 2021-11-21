package control;

import DAO.RoupasDAO;
import entities.Roupa;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class RoupasControl {


    RoupasDAO control = new RoupasDAO();
    ObservableList roupas = FXCollections.observableArrayList();

    public List<Roupa> pesquisar() {
        List<Roupa> list = control.listar();
        roupas.addAll(list);
        return roupas;
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
