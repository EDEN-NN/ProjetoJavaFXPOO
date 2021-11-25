package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLogin extends Application {

    TelaCadastro cadastro = new TelaCadastro();
    TextField txtUsername = new TextField();
    TextField txtPassword = new TextField();
    Button btnLogin = new Button("Login");
    Button btnCadastrar = new Button("Cadastrar");
    Button btnPassword = new Button("Esqueci minha senha");


    @Override
    public void start(Stage stage) throws Exception {

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 300, 400);

        pane.add(new Label("User"),0 ,0 );
        pane.add(txtUsername, 1, 0);
        pane.add(new Label("Password"), 0, 1);
        pane.add(txtPassword, 1, 1);
        pane.add(btnLogin,0, 2);
        pane.add(btnPassword, 1, 2);
        pane.add(btnCadastrar, 2, 2);

        btnLogin.setOnAction((e) -> {

        });

        btnCadastrar.setOnAction((e) -> {
            scene.setRoot(cadastro.render());
        });

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
