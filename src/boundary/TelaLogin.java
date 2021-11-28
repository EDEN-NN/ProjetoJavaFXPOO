package boundary;

import control.UsuarioControl;
import entities.Usuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLogin extends Boundary {

    UsuarioControl control = new UsuarioControl();
    TextField txtUsername = new TextField();
    PasswordField txtPassword = new PasswordField();
    Button btnLogin = new Button("Login");
    Button btnCadastro = new Button("Cadastro");

    @Override
    public void render() {
        Stage window = new Stage();
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 300, 300);

        pane.add(new Label("Email"), 0, 0);
        pane.add(txtUsername, 1, 0);
        pane.add(new Label("Password"), 0, 1);
        pane.add(txtPassword, 1, 1);
        pane.add(btnLogin, 0, 2);
        pane.add(btnCadastro, 0, 3);

        btnLogin.setOnAction((e) -> {
            var user = new Usuario();
            user.setEmail(txtUsername.getText());
            user.setPassword(txtPassword.getText());
            user = control.login(user);
            if (user == null) {
                new Alert(Alert.AlertType.INFORMATION, "Email ou senha invalidos!").showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Logado!").showAndWait();
                HomeBoundary home = new HomeBoundary(user);
                home.render();
                window.close();
            }
        });

        btnCadastro.setOnAction((e) -> {
            TelaCadastro cadastro = new TelaCadastro();
            cadastro.render();
            window.close();
        });

        window.setTitle("Tela Login");
        window.setScene(scene);
        window.show();

    }
}

//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//}
