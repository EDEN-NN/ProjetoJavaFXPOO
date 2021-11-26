package boundary;

import control.UsuarioControl;
import entities.Usuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLogin extends Boundary {

    UsuarioControl control = new UsuarioControl();
    TextField txtUsername = new TextField();
    TextField txtPassword = new TextField();
    Button btnLogin = new Button("Login");
    Button btnPassword = new Button("Esqueci minha senha");

    @Override
    public Pane render() {
        GridPane pane = new GridPane();

        pane.add(new Label("User"), 0, 0);
        pane.add(txtUsername, 1, 0);
        pane.add(new Label("Password"), 0, 1);
        pane.add(txtPassword, 1, 1);
        pane.add(btnLogin, 0, 2);
        pane.add(btnPassword, 1, 2);

        btnLogin.setOnAction((e) -> {
            var user = new Usuario();
            user.setEmail(txtUsername.getText());
            user.setPassword(txtPassword.getText());
            user = control.login(user);
            if (user == null) {
                new Alert(Alert.AlertType.INFORMATION, "Email ou senha invalidos!").showAndWait();
            } else {

            }
        });

        return pane;
    }
}

//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//}
