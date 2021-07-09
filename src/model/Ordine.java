package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Ordine {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return idOrdine == ordine.idOrdine &&
                totaleOrdine == ordine.totaleOrdine &&
                Objects.equals(dataOrdine, ordine.dataOrdine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdine, dataOrdine, totaleOrdine);
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "idOrdine=" + idOrdine +
                ", dataOrdine=" + dataOrdine +
                ", totaleOrdine=" + totaleOrdine +
                '}';
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Timestamp getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Timestamp dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public String getTotaleOrdine() {
        return totaleOrdine;
    }

    public void setTotaleOrdine(String totaleOrdine) {
        this.totaleOrdine = totaleOrdine;
    }


    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(String listaProdotti) {
        this.listaProdotti = listaProdotti;
    }


    public String getNumeroProdotti() {
        return numeroProdotti;
    }

    public void setNumeroProdotti(String numeroProdotti) {
        this.numeroProdotti = numeroProdotti;
    }



    //variabili istanza
    private int idOrdine;
    private Timestamp dataOrdine;
    private String totaleOrdine;
    private int idUtente;
    private String nomeUtente;
    private String listaProdotti;
    private String numeroProdotti;
}
