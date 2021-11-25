package control;

import DAO.UsuarioDAO;
import entities.Usuario;

public class UsuarioControl {

    private UsuarioDAO dao = new UsuarioDAO();

    public Usuario login(Usuario user){
        user.setPassword(Extensions.getHashMd5(user.getPassword()));
        var result = dao.login(user);

        return result;
    }

    public String signup(Usuario user){
        user.setPassword(Extensions.getHashMd5(user.getPassword()));
        var result = dao.signup(user);

        if(result)
            return "Login realizado com sucesso!";

        return "Email e/ou senha incorreta!";
    }

}
