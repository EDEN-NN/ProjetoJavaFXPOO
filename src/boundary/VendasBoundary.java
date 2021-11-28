package boundary;

import control.CarrinhoControl;
import control.CommonControl;
import control.UsuarioControl;
import entities.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.w3c.dom.Text;

public class VendasBoundary extends Boundary{

    Usuario user;

    public VendasBoundary(Usuario user){
        this.user = user;
    }

    UsuarioControl usuarioControl = new UsuarioControl();
    CommonControl commonControl = new CommonControl();
    ComboBox<FormaPagamento> pagamento = new ComboBox<>();
    ComboBox<Endereco> enderecoComboBox = new ComboBox<>();
    ComboBox<Produto> produtoComboBox = new ComboBox<>();
    Button btnConfirmar = new Button("CONFIRMAR COMPRA");
    TextField txtTotal = new TextField();
    Label lblProduto = new Label();


    @Override
    public void render() {
        Stage window = new Stage();
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);


        pagamento.getItems().addAll(commonControl.listarFormasPagamentos());

        pagamento.setConverter(new StringConverter<FormaPagamento>() {
            @Override
            public String toString(FormaPagamento formaPagamento) {
                return formaPagamento.getDescricao();
            }

            @Override
            public FormaPagamento fromString(String s) {
                return pagamento.getItems().stream().filter(x -> x.getDescricao().equals(s)).findFirst().orElse(null);
            }
        });

        enderecoComboBox.getItems().addAll(usuarioControl.listarEnderecos(user));

        enderecoComboBox.setConverter(new StringConverter<Endereco>() {
            @Override
            public String toString(Endereco endereco) {
                return endereco.getLogradouro() + " " + endereco.getBairro() + " " + endereco.getNumero();
            }

            @Override
            public Endereco fromString(String s) {
                return enderecoComboBox.getItems().stream().filter(x -> x.getLogradouro().equals(s)).findFirst().orElse(null);
            }
        });




        lblProduto.setText(getProduto());

        txtTotal.setText(String.valueOf(CarrinhoControl.carrinho.total));

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.add(new Label("FORMA DE PAGAMENTO"), 0, 0);
        pane.add(pagamento, 1, 0);
        pane.add(new Label("ENDEREÇO PARA ENTREGA"), 0, 1);
        pane.add(enderecoComboBox, 1, 1);
        pane.add(new Label("CONFIRMAR CARRINHO"), 0, 2);
        pane.add(produtoComboBox, 1, 2);
        pane.add(btnConfirmar, 0, 3);

        window.setTitle("CONFIRMAR COMPRA");
        window.setScene(scene);
        window.show();
    }

        public String getProduto() {
        String nome = "";
        for (Produto p : CarrinhoControl.carrinho.produtos) {
            nome+= p.getNome() + "\n";
        }
        return nome;
     }

}
