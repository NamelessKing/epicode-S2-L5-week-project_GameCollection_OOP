package org.example.collection;

import org.example.model.Gioco;
import org.example.model.GiocoDaTavolo;
import org.example.exceptions.DuplicateIdException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.ValidationException;

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


}
