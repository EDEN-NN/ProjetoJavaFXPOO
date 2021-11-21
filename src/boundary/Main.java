package boundary;

import control.RoupasControl;
import entities.Roupa;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    RoupasControl control = new RoupasControl();
    List<Roupa> list = new ArrayList<>();

    TextField txtId = new TextField();
    TextField txtNome = new TextField();
    TextField txtModelo = new TextField();
    TextField txtPreco = new TextField();
    TextField txtQuantidade = new TextField();
    Button btnPesquisar = new Button("Pesquisar");
    Button btnAdicionar = new Button("Adicionar");

    @Override
    public void start(Stage primaryStage) throws Exception{
       GridPane gp = new GridPane();
        Scene scene = new Scene(gp, 1024, 670);

        gp.add(new Label("id"), 0, 0);
        gp.add(txtId, 1, 0);
        gp.add(new Label("nome"), 0, 1);
        gp.add(txtNome, 1, 1);
        gp.add(new Label("modelo"),0 ,2 );
        gp.add(txtModelo, 1, 2);
        gp.add(new Label("preço"), 0, 3);
        gp.add(txtPreco, 1, 3);
        gp.add(new Label("quantidade"), 0, 4);
        gp.add(txtQuantidade, 1, 4);
        gp.add(btnPesquisar, 0, 5);
        gp.add(btnAdicionar, 1, 5);
        primaryStage.setTitle("Gadders");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnAdicionar.setOnAction((e) -> {
            if(txtId != null) {
                Roupa roupa = new Roupa();
                roupa.setId(Integer.parseInt(txtId.getText()));
                roupa.setNome(txtNome.getText());
                roupa.setModelo(txtModelo.getText());
                roupa.setPreco(Double.parseDouble(txtPreco.getText()));
                roupa.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                control.Adicionar(roupa);
                limpar();
                new Alert(Alert.AlertType.INFORMATION, "Roupa salva com sucesso").showAndWait();
            }
        });
        btnPesquisar.setOnAction((e) -> {
        list.add(control.pesquisarPorNome(txtNome.getText()));
        for (Roupa p : list) {
            toBoundary(p);
        }
        });

    }

    public void toBoundary(Roupa roupa) {
        if (roupa != null) {
            txtId.setText(String.valueOf(roupa.getId()));
            txtNome.setText(roupa.getNome());
            txtModelo.setText(roupa.getModelo());
            txtPreco.setText(String.valueOf(roupa.getPreco()));
            txtQuantidade.setText(String.valueOf(roupa.getQuantidade()));
        }
    }

    public void limpar() {
        txtId.setText("");
        txtNome.setText("");
        txtModelo.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
