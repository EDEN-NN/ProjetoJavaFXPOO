package boundary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application{

    Stage window = new Stage();
    TelaLogin login = new TelaLogin();
    TelaCadastro cadastro = new TelaCadastro();

    Button btnLogin = new Button("LOGIN");
    Button btnCadastro = new Button("CADASTRAR");


    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
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
            login.render();
            window.close();
        });

        btnCadastro.setOnAction((e) -> {
            cadastro.render();
            window.close();
        });

        window.setTitle("Tela Inicial");
       window.setScene(scene);
        window.show();

    }


}
