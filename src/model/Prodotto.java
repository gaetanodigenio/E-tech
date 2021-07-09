package model;

import java.util.List;
import java.util.Objects;

public class Prodotto {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public long getPrezzoCentesimi() {
        return prezzoCentesimi;
    }

    public void setPrezzoCentesimi(long prezzoCentesimi) {
        this.prezzoCentesimi = prezzoCentesimi;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString(){
        return "Prodotto [id=" + id + " ,nome=" + nome + " ,descrizione=" + descrizione + " ,prezzoCent=" + prezzoCentesimi
                + " ,categorie=" + categorie + "]";
    }

    public String getPrezzoEuro(){
        return String.format("%.2f", prezzoCentesimi / 100.);
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Prodotto other=(Prodotto) obj;
        if(categorie==null){
            if(other.categorie!=null)
                return false;
        }else if(!categorie.equals(other.categorie))
            return false;
        if(descrizione == null){
            if(other.descrizione!=null)
                return false;
        }else if(!descrizione.equals((other.descrizione)))
            return false;
        if(id!=other.id)
            return false;
        if(nome==null){
            if(other.nome!=null)
                return false;
        }else if(!nome.equals(other.nome))
            return false;
        if(prezzoCentesimi!=other.prezzoCentesimi)
            return false;
        return true;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }



    //variabili istanza
    private int id;
    private String nome;
    private String descrizione;
    private long prezzoCentesimi;
    private List<Categoria> categorie;
    private String immagine;
}
