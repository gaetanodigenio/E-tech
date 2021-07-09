package model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {
    public ArrayList<Utente> doRetrieveAll(){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,username,password,nome,email,admin FROM utente ");
            ArrayList<Utente> utenti=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Utente u=new Utente();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setAdmin(rs.getBoolean(6));
                utenti.add(u);
            }
            return utenti;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public Utente doRetrieveById(int id){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,username,password,nome,email,admin FROM utente WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Utente u=new Utente();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setAdmin(rs.getBoolean(6));
                return u;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsername(String username){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,username,password,nome,email,admin FROM utente WHERE username=?");
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Utente u=new Utente();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setAdmin(rs.getBoolean(6));
                return u;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsernamePassword(String username,String password){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,username,password,nome,email,admin FROM utente WHERE username=? AND password=SHA1(?)");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Utente u=new Utente();
                u.setId(rs.getInt(1));
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNome(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setAdmin(rs.getBoolean(6));
                return u;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (username, password, nome, email, admin) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getEmail());
            ps.setBoolean(5, utente.isAdmin());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            utente.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("DELETE FROM login WHERE idutente=?");
            PreparedStatement ps1=con.prepareStatement("DELETE FROM utente WHERE id=?");
            ps.setInt(1,id);
            ps1.setInt(1,id);
            ps.executeUpdate();
            if(ps1.executeUpdate()!=1 ){
                throw new RuntimeException("DELETE error.");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Utente utente){
        try(Connection con=ConPool.getConnection()){
        PreparedStatement ps=con.prepareStatement("UPDATE utente SET username=?, nome=?,email=?,admin=? WHERE id=?");
        ps.setString(1,utente.getUsername());
        ps.setString(2,utente.getNome());
        ps.setString(3,utente.getEmail());
        ps.setBoolean(4,utente.isAdmin());
        ps.setInt(5,utente.getId());
        if(ps.executeUpdate()!=1){
            throw new RuntimeException("Update error.");
        }
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
}

}
