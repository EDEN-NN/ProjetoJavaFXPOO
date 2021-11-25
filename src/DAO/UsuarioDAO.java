package DAO;

import entities.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class UsuarioDAO implements IUsuario{

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

}
