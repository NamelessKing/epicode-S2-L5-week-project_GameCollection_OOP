package org.example.collection;

import org.example.model.Gioco;
import org.example.model.GiocoDaTavolo;
import org.example.exceptions.DuplicateIdException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.ValidationException;
import org.example.model.Videogioco;

import java.util.*;

public class Collezione {
    private Map<String, Gioco> giochi;

    public Collezione() {
        this.giochi = new HashMap<>();
    }

    public void aggiungiGioco(Gioco gioco)
            throws DuplicateIdException, ValidationException {

        if (gioco == null) {
            throw new ValidationException("Gioco nullo: impossibile aggiungere alla collezione");
        }

        if (giochi.containsKey(gioco.getId())) {
            throw new DuplicateIdException(
                    "Esiste già un gioco con ID: " + gioco.getId()
            );
        }

        giochi.put(gioco.getId(), gioco);
    }

    public void rimuoviGioco(String id)
            throws NotFoundException, ValidationException {

        if (id == null || id.isBlank()) {
            throw new ValidationException("ID non valido");
        }

        if (!giochi.containsKey(id)) {
            throw new NotFoundException(
                    "Nessun gioco trovato con ID: " + id
            );
        }

        giochi.remove(id);
    }


    public Gioco cercaPerID(String id)
            throws NotFoundException, ValidationException {

        if (id == null || id.isBlank()) {
            throw new ValidationException("ID non valido");
        }

        Gioco gioco = giochi.get(id);

        if (gioco == null) {
            throw new NotFoundException(
                    "Nessun gioco trovato con ID: " + id
            );
        }

        return gioco;
    }


    public Collection<Gioco> ottieniTuttiGiochi() {
        return giochi.values();
    }


    public void svuotaCollezione() {
        giochi.clear();
    }

    public List<Gioco> ricercaPerPrezzoMassimo(double prezzoMax)
            throws ValidationException {

        if (prezzoMax <= 0) {
            throw new ValidationException("Prezzo massimo non valido");
        }

        return giochi.values()
                .stream()
                .filter(gioco -> gioco.getPrezzo() < prezzoMax)
                .sorted(Comparator.comparingDouble(Gioco::getPrezzo))
                .toList();

    }



    public List<GiocoDaTavolo> ricercaGiochiDaTavoloPerNumeroGiocatori(int numeroGiocatori)
            throws ValidationException {

        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new ValidationException("Numero di giocatori non valido (2–10)");
        }

        return giochi.values()
                .stream()
                .filter(gioco -> gioco instanceof GiocoDaTavolo)
                .map(gioco -> (GiocoDaTavolo) gioco)
                .filter(gdt -> gdt.getNumeroGiocatori() == numeroGiocatori)
                .toList();
    }


    public void aggiornaGioco(String id, Gioco nuovoGioco)
            throws ValidationException, NotFoundException {

        if (id == null || id.isBlank()) {
            throw new ValidationException("ID non valido");
        }

        if (nuovoGioco == null) {
            throw new ValidationException("Gioco nullo: impossibile aggiornare");
        }

        if (!giochi.containsKey(id)) {
            throw new NotFoundException(
                    "Nessun gioco trovato con ID: " + id
            );
        }

        if (!id.equals(nuovoGioco.getId())) {
            throw new ValidationException(
                    "ID del gioco non coerente con quello da aggiornare"
            );
        }

        giochi.put(id, nuovoGioco);
    }


    public long countVideogiochi() {
        return giochi.values()
                .stream()
                .filter(gioco -> gioco instanceof Videogioco)
                .count();
    }

    public long countGiochiDaTavolo() {
        return giochi.values()
                .stream()
                .filter(gioco -> gioco instanceof GiocoDaTavolo)
                .count();
    }


    public Gioco trovaGiocoPrezzoPiuAlto()
            throws ValidationException {

        if (giochi.isEmpty()) {
            throw new ValidationException("Collezione vuota: impossibile determinare il prezzo massimo");
        }

        return giochi.values()
                .stream()
                .max(Comparator.comparingDouble(Gioco::getPrezzo))
                .orElseThrow(() ->
                        new ValidationException("Errore nel calcolo del prezzo massimo")
                );
    }

    public double calcolaMediaPrezzi()
            throws ValidationException {

        if (giochi.isEmpty()) {
            throw new ValidationException("Collezione vuota: impossibile calcolare la media");
        }

        return giochi.values()
                .stream()
                .mapToDouble(Gioco::getPrezzo)
                .average()
                .orElseThrow(() ->
                        new ValidationException("Errore nel calcolo della media dei prezzi")
                );
    }



}
