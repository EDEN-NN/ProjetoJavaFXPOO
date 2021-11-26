package boundary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application implements IExecutorComandos {

    TelaLogin login = new TelaLogin();
    TelaCadastro cadastro = new TelaCadastro();

    Button btnLogin = new Button("LOGIN");
    Button btnCadastro = new Button("CADASTRAR");


    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 250, 250);

        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(20);

        btnLogin.setPrefSize(100, 30);
        btnCadastro.setPrefSize(100, 30);


        pane.add(btnLogin, 2, 2);
        pane.add(btnCadastro, 2, 3);

        btnLogin.setOnAction((e) -> {
            executarComandos("login");
        });

        btnCadastro.setOnAction((e) -> {
            executarComandos("cadastro");
        });

        stage.setTitle("Tela Inicial");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void executarComandos(String comandos) {
        if (comandos.contains("login")) {
           Pane pane = login.render();
           Scene scene = new Scene(pane, 300,300);
           Stage stage = new Stage();
           stage.setTitle("Login");
           stage.setScene(scene);
           stage.show();
        } else if (comandos.contains("cadastro")) {
            Pane pane = cadastro.render();
            Scene scene = new Scene(pane, 300,300);
            Stage stage = new Stage();
            stage.setTitle("Cadastro");
            stage.setScene(scene);
            stage.show();
        }
    }

}
