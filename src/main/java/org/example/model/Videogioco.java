package org.example.model;

import org.example.exceptions.ValidationException;

public class Videogioco extends Gioco {

    private Piattaforma piattaforma;
    private double durataOre;
    private Genere genere;

    public Videogioco(
            String id,
            String titolo,
            int annoPubblicazione,
            double prezzo,
            Piattaforma piattaforma,
            double durataOre,
            Genere genere
    ) throws ValidationException {

        super(id, titolo, annoPubblicazione, prezzo);

        if (piattaforma == null) {
            throw new ValidationException("Piattaforma non valida");
        }

        if (durataOre <= 0) {
            throw new ValidationException("Durata di gioco non valida");
        }

        if (genere == null) {
            throw new ValidationException("Genere non valido");
        }

        this.piattaforma = piattaforma;
        this.durataOre = durataOre;
        this.genere = genere;
    }

    public Piattaforma getPiattaforma() {
        return piattaforma;
    }

    public double getDurataOre() {
        return durataOre;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setPiattaforma(Piattaforma piattaforma) throws ValidationException {
        if (piattaforma == null) {
            throw new ValidationException("Piattaforma non valida");
        }
        this.piattaforma = piattaforma;
    }

    public void setDurataOre(double durataOre) throws ValidationException {
        if (durataOre <= 0) {
            throw new ValidationException("Durata di gioco non valida");
        }
        this.durataOre = durataOre;
    }

    public void setGenere(Genere genere) throws ValidationException {
        if (genere == null) {
            throw new ValidationException("Genere non valido");
        }
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Videogioco{" +
                "id='" + id + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", prezzo=" + prezzo +
                ", piattaforma=" + piattaforma +
                ", durataOre=" + durataOre +
                ", genere=" + genere +
                '}';
    }
}
