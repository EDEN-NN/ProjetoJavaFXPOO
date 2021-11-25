package boundary;

import DAO.UsuarioDAO;
import control.UsuarioControl;
import entities.Usuario;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaCadastro {

    UsuarioControl userControl = new UsuarioControl();

    TextField txtName = new TextField();
    TextField txtPassword = new TextField();
    TextField txtCpf = new TextField();
    TextField txtLastName = new TextField();
    TextField txtEmail = new TextField();
    DatePicker birthDate = new DatePicker();
    Button btnCreate = new Button("Criar Conta");

    public Pane render() {
        GridPane pane = new GridPane();

        pane.add(new Label("Username"), 0, 0);
        pane.add(txtName, 1,0);
        pane.add(new Label("Last name"), 0, 1);
        pane.add(txtLastName, 1, 1);
        pane.add(new Label("Password"), 0, 2);
        pane.add(txtPassword, 1, 2);
        pane.add(new Label("CPF"), 0, 3);
        pane.add(txtCpf, 1, 3);
        pane.add(new Label("Email"), 0, 4);
        pane.add(txtEmail, 1, 4);
        pane.add(new Label("Birthdate"), 0, 5);
        pane.add(birthDate, 1, 5);
        pane.add(btnCreate, 0, 6);

        btnCreate.setOnAction((e) -> {
            Usuario usuario = new Usuario();
            usuario.setName(txtName.getText());
            usuario.setLastname(txtLastName.getText());
            usuario.setPassword(txtPassword.getText());
            usuario.setCpf(txtCpf.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setData_nasc(birthDate.getValue());

            userControl.signup(usuario);
            new Alert(Alert.AlertType.INFORMATION, "Usuário salvo com sucesso").showAndWait();
        });

        return pane;
    }
}
