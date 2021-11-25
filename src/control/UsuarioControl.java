package control;

import DAO.UsuarioDAO;
import entities.Endereco;
import entities.Produto;
import entities.Telefone;
import entities.Usuario;

import java.util.List;

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

    public List<Endereco> listarEnderecos(Usuario user){

        return dao.listarEnderecos(user);
    }

    public boolean cadastrarEndereco(Usuario user, Endereco endereco){

        return dao.cadastrarEndereco(user, endereco);
    }

    public List<Telefone> listarTelefones(Usuario user){

        return dao.listarTelefones(user);
    }

    public boolean cadastrarTelefone(Usuario user, Telefone telefone){

        return dao.cadastrarTelefone(user, telefone);
    }

}
