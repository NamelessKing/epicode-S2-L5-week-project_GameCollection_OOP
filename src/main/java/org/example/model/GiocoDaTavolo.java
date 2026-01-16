package org.example.model;

import org.example.exceptions.ValidationException;

public class GiocoDaTavolo extends Gioco {

    private int numeroGiocatori;
    private int durataMediaMinuti;

    public GiocoDaTavolo(
            String id,
            String titolo,
            int annoPubblicazione,
            double prezzo,
            int numeroGiocatori,
            int durataMediaMinuti
    ) throws ValidationException {

        super(id, titolo, annoPubblicazione, prezzo);

        if (numeroGiocatori <= 0) {
            throw new ValidationException("Numero di giocatori non valido");
        }

        if (durataMediaMinuti <= 0) {
            throw new ValidationException("Durata media non valida");
        }

        this.numeroGiocatori = numeroGiocatori;
        this.durataMediaMinuti = durataMediaMinuti;
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public int getDurataMediaMinuti() {
        return durataMediaMinuti;
    }

    public void setNumeroGiocatori(int numeroGiocatori) throws ValidationException {
        if (numeroGiocatori <= 0) {
            throw new ValidationException("Numero di giocatori non valido");
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public void setDurataMediaMinuti(int durataMediaMinuti) throws ValidationException {
        if (durataMediaMinuti <= 0) {
            throw new ValidationException("Durata media non valida");
        }
        this.durataMediaMinuti = durataMediaMinuti;
    }

    @Override
    public String toString() {
        return "GiocoDaTavolo{" +
                "id='" + id + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", prezzo=" + prezzo +
                ", numeroGiocatori=" + numeroGiocatori +
                ", durataMediaMinuti=" + durataMediaMinuti +
                '}';
    }
}
