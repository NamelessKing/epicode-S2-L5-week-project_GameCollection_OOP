package org.example.model;

import org.example.exceptions.ValidationException;

public abstract class Gioco {

    protected final String id;          
    protected String titolo;
    protected int annoPubblicazione;
    protected double prezzo;

    public Gioco(String id, String titolo, int annoPubblicazione, double prezzo)
            throws ValidationException {

        if (id == null || id.isBlank()) {
            throw new ValidationException("ID non valido");
        }

        if (titolo == null || titolo.isBlank()) {
            throw new ValidationException("Titolo non valido");
        }

        if (annoPubblicazione <= 0) {
            throw new ValidationException("Anno di pubblicazione non valido");
        }

        if (prezzo <= 0) {
            throw new ValidationException("Prezzo non valido");
        }

        this.id = id;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }


    public String getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }


    public void setTitolo(String titolo) throws ValidationException {
        if (titolo == null || titolo.isBlank()) {
            throw new ValidationException("Titolo non valido");
        }
        this.titolo = titolo;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) throws ValidationException {
        if (annoPubblicazione <= 0) {
            throw new ValidationException("Anno di pubblicazione non valido");
        }
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setPrezzo(double prezzo) throws ValidationException {
        if (prezzo <= 0) {
            throw new ValidationException("Prezzo non valido");
        }
        this.prezzo = prezzo;
    }


    @Override
    public String toString() {
        return "Gioco{" +
                "id='" + id + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", prezzo=" + prezzo +
                '}';
    }
}
