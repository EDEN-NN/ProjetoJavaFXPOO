package DAO;

import entities.Usuario;

public interface IUsuario {
    public Usuario login(Usuario user);
    public boolean signup(Usuario user);
}
