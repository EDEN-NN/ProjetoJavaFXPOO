package control;

import DAO.UsuarioDAO;
import entities.Usuario;

public class UsuarioControl {

    private UsuarioDAO dao = new UsuarioDAO();

    public Usuario login(Usuario user){
        user.setPassword(Extensions.getHashMd5(user.getPassword()));
        return dao.login(user);
    }

    public boolean signup(Usuario user){
        user.setPassword(Extensions.getHashMd5(user.getPassword()));
        return dao.signup(user);
    }

}
