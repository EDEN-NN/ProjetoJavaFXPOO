package boundary;

import control.CarrinhoControl;
import control.ProdutosControl;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class HomeBoundary extends Boundary{

    Usuario user;
    ObservableList<Produto> list;

    public HomeBoundary(Usuario user) {
        list = FXCollections.observableArrayList();
        getList("");
        this.user = user;
    }

    public HomeBoundary() {
        list = FXCollections.observableArrayList();
        getList("");
    }

    ProdutosControl control = new ProdutosControl();
    TextField txtProduct = new TextField();
    Button btnPesquisar = new Button("PESQUISAR");
    Button btnAdd = new Button("Adicionar ao carrinho");
    TextField txtSearch = new TextField();
    Button btnRemove = new Button("Remover");
    Button btnList = new Button("Listar Carrinho");
    Button btnComprar = new Button("Fechar Compra");

    @Override
    public void render() {
        VBox pane = new VBox();
        Stage window = new Stage();
        Scene scene = new Scene(pane, 900, 720);

        HBox box = new HBox();
        box.setPadding(new Insets(10, 10, 10,10));
        box.setSpacing(10);
        txtSearch.setPromptText("ID do produto");
        box.getChildren().addAll(txtSearch, btnAdd, btnRemove, btnList, btnComprar);

        pane.getChildren().addAll(txtProduct, btnPesquisar);
        TableView<Produto> table = new TableView<>();
        TableColumn<Produto, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(150);
        idColumn.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));
        TableColumn<Produto, Double> precoColumn = new TableColumn<>("Preço");
        precoColumn.setMinWidth(150);
        precoColumn.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
        TableColumn<Produto, Integer> quantidadeColumn = new TableColumn<>("Quantidade");
        quantidadeColumn.setMinWidth(150);
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));
        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setMinWidth(150);
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        TableColumn<Produto, String> descricaoColumn = new TableColumn<>("Descrição");
        descricaoColumn.setMinWidth(150);
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
        TableColumn<Produto, String> tamanhoColumn = new TableColumn<>("Tamanho");
        tamanhoColumn.setMinWidth(150);
        tamanhoColumn.setCellValueFactory(new PropertyValueFactory<Produto, String>("tamanho"));
        table.setItems(list);
        table.getColumns().addAll(idColumn, precoColumn, quantidadeColumn, nomeColumn, descricaoColumn, tamanhoColumn);

        pane.getChildren().addAll(table, box);

        btnAdd.setOnAction((e) -> {
            Integer id = Integer.parseInt(txtSearch.getText());
            var produto = list.stream().filter(x -> x.getId() == id);
            CarrinhoControl control = new CarrinhoControl();
            control.addProduto(produto.findFirst().get());
            txtSearch.clear();
            new Alert(Alert.AlertType.INFORMATION, "ITEM ADICIONADO AO CARRINHO!").showAndWait();
        });

        btnRemove.setOnAction((e) -> {
            Integer id = Integer.parseInt(txtSearch.getText());
            var produto = list.stream().filter(x -> x.getId() == id);
            CarrinhoControl control = new CarrinhoControl();
            control.removerProduto(produto.findFirst().get());
            table.refresh();
            txtSearch.clear();
            new Alert(Alert.AlertType.INFORMATION, "ITEM REMOVIDO DO CARRINHO!").showAndWait();
        });

        btnList.setOnAction((e) -> {
            table.getItems().clear();
            list.addAll(CarrinhoControl.carrinho.produtos);
            table.setItems(list);
        });

        btnPesquisar.setOnAction((e) -> {
            table.getItems().clear();
            getList(txtProduct.getText());
            table.setItems(list);
            txtProduct.clear();
        });

        btnComprar.setOnAction((e) -> {
            VendasBoundary vendas = new VendasBoundary(user);
            vendas.render();
            window.close();
        });

        window.setTitle("HOME");
        window.setScene(scene);
        window.show();


    }

    public void getList(String search) {
        if (txtProduct.getText() != null) {
            list.removeAll();
            List<Produto> produtos = (control.pesquisar(search));
            for (Produto p : produtos) {
                list.add(p);
            }
        }
    }

}
