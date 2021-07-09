package model;

import com.mysql.cj.result.SqlDateValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdottoDAO {
    public List<Prodotto> doRetrieveAll(){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,nome,descrizione,prezzoCent,immagine FROM prodotto ");
            ArrayList<Prodotto> prodotti=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzoCentesimi(rs.getLong(4));
                p.setImmagine(rs.getString(5));
                p.setCategorie(getCategorie(con,p.getId()));  // un prodotto puo avere piu di una categoria
                prodotti.add(p);
            }
            return prodotti;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Prodotto> doRetrieveByNome(String nome){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,nome,descrizione,prezzoCent,immagine FROM prodotto WHERE MATCH (nome) AGAINST(? IN BOOLEAN MODE) ");
            ps.setString(1,nome);
            ArrayList<Prodotto> prodotti=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzoCentesimi(rs.getLong(4));
                p.setImmagine(rs.getString(5));
                p.setCategorie(getCategorie(con,p.getId()));
                prodotti.add(p);
            }
            return prodotti;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveByNameOrDescription(String against){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,nome,descrizione,prezzoCent,immagine FROM prodotto WHERE MATCH (nome,descrizione) AGAINST(?) ");
            ps.setString(1,against);
            ArrayList<Prodotto> prodotti=new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzoCentesimi(rs.getLong(4));
                p.setImmagine(rs.getString(5));
                p.setCategorie(getCategorie(con,p.getId()));
                prodotti.add(p);
            }
            return prodotti;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveByCategoria(int categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, nome, descrizione, prezzoCent,immagine FROM prodotto LEFT JOIN prodotto_categoria ON id=idprodotto WHERE idcategoria=? ");
            ps.setInt(1, categoria);
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzoCentesimi(rs.getLong(4));
                p.setImmagine(rs.getString(5));
                p.setCategorie(getCategorie(con, p.getId()));
                prodotti.add(p);
            }
            return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prodotto doRetrieveById(int id){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT id,nome,descrizione,prezzoCent,immagine FROM prodotto WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Prodotto p=new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzoCentesimi(rs.getLong(4));
                p.setImmagine(rs.getString(5));
                p.setCategorie(getCategorie(con,p.getId()));
            return p;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void doSave(Prodotto prodotto){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("INSERT INTO prodotto(nome,descrizione,prezzoCent,immagine) VALUES(?,?,?,?) ",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,prodotto.getNome());
            ps.setString(2,prodotto.getDescrizione());
            ps.setLong(3,prodotto.getPrezzoCentesimi());
            ps.setString(4,prodotto.getImmagine());
            if(ps.executeUpdate()!=1)
                throw new RuntimeException("Insert error.");
            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            int id=rs.getInt(1);
            prodotto.setId(id);

            PreparedStatement psCa=con.prepareStatement("INSERT INTO prodotto_categoria (idprodotto,idcategoria) VALUE (?,?)");
            for(Categoria c:prodotto.getCategorie()){
                psCa.setInt(1,id);
                psCa.setInt(2,c.getId());
                psCa.addBatch();
            }
            psCa.executeBatch();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Prodotto prodotto){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("UPDATE prodotto SET nome=?, descrizione=?,prezzoCent=?,immagine=? WHERE id=?");
            ps.setString(1,prodotto.getNome());
            ps.setString(2,prodotto.getDescrizione());
            ps.setLong(3,prodotto.getPrezzoCentesimi());
            ps.setString(4,prodotto.getImmagine());
            ps.setInt(5,prodotto.getId());
            if(ps.executeUpdate()!=1){
                throw new RuntimeException("Update error.");
            }
            if(prodotto.getCategorie().isEmpty()){  // se il prodotto non appartiene piu a nessuna categoria
                PreparedStatement psCaDel=con.prepareStatement("DELETE FROM prodotto_categoria WHERE idprodotto=?");
                psCaDel.setInt(1, prodotto.getId());
                psCaDel.executeUpdate();
            }else{  //se abbiamo tolto alcune categorie o aggiunte altre
                PreparedStatement psCaDel=con.prepareStatement("DELETE FROM prodotto_categoria WHERE idprodotto=? AND idcategoria NOT IN(" +
                        prodotto.getCategorie().stream().map(c->String.valueOf(c.getId())).collect(Collectors.joining(","))+")");  //1 2 3 => 1,2,3
                psCaDel.setInt(1,prodotto.getId());
                psCaDel.executeUpdate();

                PreparedStatement psCa = con.prepareStatement(
                        "INSERT IGNORE INTO prodotto_categoria (idprodotto, idcategoria) VALUES (?, ?)");
                for (Categoria c : prodotto.getCategorie()) {
                    psCa.setInt(1, prodotto.getId());
                    psCa.setInt(2, c.getId());
                    psCa.addBatch();
                }
                psCa.executeBatch();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id){
        try(Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("DELETE FROM prodotto WHERE id=?");
            ps.setInt(1,id);
            if(ps.executeUpdate()!=1){
                throw new RuntimeException("DELETE error.");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    private static List<Categoria> getCategorie(Connection con,int id) throws SQLException{
        PreparedStatement ps=con.prepareStatement("SELECT id,nome,descrizione FROM categoria LEFT JOIN prodotto_categoria ON id=idcategoria WHERE idprodotto=?");
        ps.setInt(1,id);
        ArrayList<Categoria> categorie=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            Categoria c=new Categoria();
            c.setId(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setDescrizione(rs.getString(3));
            categorie.add(c);
        }
        return categorie;
    }

}


