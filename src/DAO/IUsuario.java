package DAO;

import entities.Endereco;
import entities.Telefone;
import entities.Usuario;

import java.util.List;

public interface IUsuario {
    public Usuario login(Usuario user);
    public boolean signup(Usuario user);
    public boolean cadastrarEndereco(Usuario user, Endereco endereco);
    public List<Endereco> listarEnderecos(Usuario user);
    public boolean cadastrarTelefone(Usuario user, Telefone endereco);
    public List<Telefone> listarTelefones(Usuario user);
}
