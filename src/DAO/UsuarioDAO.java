package DAO;

import entities.Endereco;
import entities.Telefone;
import entities.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public Usuario login(Usuario user) {
        java.sql.Connection conn = null;
        try {
            String sql ="SELECT * FROM usuario"+" where email = ? AND password = ?";
            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, user.getEmail());
            pstm.setString(2,user.getPassword());

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){

                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setCpf(rs.getString("cpf"));
                user.setId(rs.getInt("id"));
                user.setAdministrator(rs.getBoolean("isAdministrator"));
                user.setData_nasc(rs.getDate("data_nasc").toLocalDate());

                return user;
            }

            return null;
        } catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return null;
        }finally{
            Connection.closeConnection(conn);
        }
    }

    @Override
    public boolean signup(Usuario user) {
        java.sql.Connection conn = null;
        try {
            String sql="INSERT INTO usuario "+" (name,lastname,cpf,data_nasc,email,password,inserted) VALUES"
                    +" ( ? , ? , ? , ? , ? , ? , ? )";

            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getLastname());
            pstm.setString(3, user.getCpf());
            pstm.setDate(4, Date.valueOf(user.getData_nasc()));
            pstm.setString(5, user.getEmail());
            pstm.setString(6, user.getPassword());
            pstm.setDate(7, Date.valueOf(LocalDate.now()));
            pstm.execute();

            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return false;
        }finally{
            Connection.closeConnection(conn);
        }
    }

    @Override
    public boolean cadastrarEndereco(Usuario user, Endereco endereco) {
        java.sql.Connection conn = null;
        try {
            String sql="INSERT INTO endereco "+" (logradouro,numero,cep,bairro,cidade,estado,complemento,referencia,id_usuario,inserted) VALUES"
                    +" ( ? , ? , ? , ? , ? , ? , ? )";

            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, endereco.getLogradouro());
            pstm.setString(2, endereco.getNumero());
            pstm.setString(3, endereco.getCep());
            pstm.setString(4, endereco.getBairro());
            pstm.setString(5, endereco.getCidade());
            pstm.setString(6, endereco.getEstado());
            pstm.setString(7, endereco.getComplemento());
            pstm.setString(8, endereco.getReferencia());
            pstm.setInt(9, user.getId());
            pstm.setDate(10, Date.valueOf(LocalDate.now()));
            pstm.execute();

            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return false;
        }finally{
            Connection.closeConnection(conn);
        }
    }

    @Override
    public List<Endereco> listarEnderecos(Usuario user) {
        java.sql.Connection conn = null;
        try {
            String sql ="SELECT * from endereco WHERE id_usuario = ?";

            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, user.getId());

            ResultSet rs = pstm.executeQuery();

            List<Endereco> enderecos = new ArrayList<>();
            while(rs.next()){
                var end = new Endereco();
                end.setId(rs.getInt("id"));
                end.setLogradouro(rs.getString("logradouro"));
                end.setNumero(rs.getString("numero"));
                end.setCep(rs.getString("cep"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setComplemento(rs.getString("complemento"));
                end.setReferencia(rs.getString("referencia"));

                enderecos.add(end);
            }

            return enderecos;
        } catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return null;
        }finally{
            Connection.closeConnection(conn);
        }
    }

    @Override
    public boolean cadastrarTelefone(Usuario user, Telefone telefone) {
        java.sql.Connection conn = null;
        try {
            String sql="INSERT INTO telefone "+" (numero,id_usuario,inserted) VALUES"
                    +" ( ? , ? , ? )";

            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, telefone.getNumero());
            pstm.setInt(2, user.getId());
            pstm.setDate(3, Date.valueOf(LocalDate.now()));
            pstm.execute();

            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return false;
        }finally{
            Connection.closeConnection(conn);
        }
    }

    @Override
    public List<Telefone> listarTelefones(Usuario user) {
        java.sql.Connection conn = null;
        try {
            String sql ="SELECT * from telefone WHERE id_usuario = ?";

            conn = Connection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, user.getId());

            ResultSet rs = pstm.executeQuery();

            List<Telefone> telefones = new ArrayList<>();
            while(rs.next()){
                var tel = new Telefone();
                tel.setId(rs.getInt("id"));
                tel.setNumero(rs.getString("numero"));

                telefones.add(tel);
            }

            return telefones;
        } catch (Exception e) {
            System.err.println(e.getMessage()+"\n\n"+e.getStackTrace());
            return null;
        }finally{
            Connection.closeConnection(conn);
        }
    }

}
