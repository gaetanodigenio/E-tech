package model;

import com.mysql.cj.result.SqlDateValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrdineDAO {
    public ArrayList<Ordine> doRetrieveAll(){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT idOrdine,dataOrdine,totaleordini,idUtente,nomeUtente,listaProdotti,numeroProdotti FROM ordini ");
            ArrayList<Ordine> ordini=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Ordine nuovo=new Ordine();
                nuovo.setIdOrdine(rs.getInt(1));
                nuovo.setDataOrdine(rs.getTimestamp(2));
                nuovo.setTotaleOrdine(rs.getString(3));
                nuovo.setIdUtente(rs.getInt(4));
                nuovo.setNomeUtente(rs.getString(5));
                nuovo.setListaProdotti(rs.getString(6));
                nuovo.setNumeroProdotti(rs.getString(7));
                ordini.add(nuovo);
            }
            return ordini;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveByUserId(int userId){
        try(Connection con= ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT idOrdine,dataOrdine,totaleordini,idUtente,nomeUtente,listaProdotti,numeroProdotti FROM ordini WHERE idUtente=?");
            ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            ArrayList<Ordine> ordini=new ArrayList<>();
            while(rs.next()){
                Ordine nuovo=new Ordine();
                nuovo.setIdOrdine(rs.getInt(1));
                nuovo.setDataOrdine(rs.getTimestamp(2));
                nuovo.setTotaleOrdine(rs.getString(3));
                nuovo.setIdUtente(rs.getInt(4));
                nuovo.setNomeUtente(rs.getString(5));
                nuovo.setListaProdotti(rs.getString(6));
                nuovo.setNumeroProdotti(rs.getString(7));
                ordini.add(nuovo);
            }
            return ordini;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void doSave(Ordine ordine){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT INTO ordini(idOrdine,dataOrdine,totaleordini,idUtente,nomeUtente,listaProdotti,numeroProdotti) VALUES(?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,ordine.getIdOrdine());
            ps.setTimestamp(2,ordine.getDataOrdine());
            ps.setString(3,ordine.getTotaleOrdine());
            ps.setInt(4,ordine.getIdUtente());
            ps.setString(5,ordine.getNomeUtente());
            ps.setString(6,ordine.getListaProdotti());
            ps.setString(7,ordine.getNumeroProdotti());

            if(ps.executeUpdate()!=1)
                throw new RuntimeException("Insert error.");
            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            int id=rs.getInt(1);
            ordine.setIdOrdine(id);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
