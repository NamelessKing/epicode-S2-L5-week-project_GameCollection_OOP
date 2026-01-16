package org.example.app;

import org.example.collection.Collezione;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.ValidationException;
import org.example.model.*;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Collezione collezione = new Collezione();
        Scanner scanner = new Scanner(System.in);

        inizializzaCollezione(collezione);

        boolean running = true;

        while (running) {
            stampaMenu();
            System.out.print("Scelta: ");
            String scelta = scanner.nextLine();

            try {
                switch (scelta) {
                    case "1" -> mostraTuttiIGiochi(collezione);
                    case "2" -> cercaGiocoPerId(collezione, scanner);
                    case "3" -> rimuoviGioco(collezione, scanner);
                    case "4" -> ricercaPerPrezzo(collezione, scanner);
                    case "5" -> ricercaPerNumeroGiocatori(collezione, scanner);
                    case "6" -> mostraStatistiche(collezione);
                    case "0" -> running = false;
                    default -> System.out.println("Scelta non valida");
                }
            } catch (ValidationException | NotFoundException e) {
                System.out.println("Errore: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Errore imprevisto: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Applicazione terminata.");
    }


    private static void stampaMenu() {
        System.out.println("""
                
                ===== GAME COLLECTION =====
                1. Mostra tutti i giochi
                2. Cerca gioco per ID
                3. Rimuovi gioco
                4. Ricerca giochi per prezzo massimo
                5. Ricerca giochi da tavolo per numero giocatori
                6. Mostra statistiche
                0. Esci
                """);
    }

    private static void inizializzaCollezione(Collezione collezione) {
        try {
            collezione.aggiungiGioco(
                    new Videogioco(
                            "V1",
                            "Elden Ring",
                            2022,
                            69.99,
                            Piattaforma.PC,
                            120,
                            Genere.RPG
                    )
            );

            collezione.aggiungiGioco(
                    new Videogioco(
                            "V2",
                            "The Witcher 3",
                            2015,
                            49.99,
                            Piattaforma.PS5,
                            150,
                            Genere.RPG
                    )
            );

            collezione.aggiungiGioco(
                    new GiocoDaTavolo(
                            "T1",
                            "Catan",
                            1995,
                            39.99,
                            4,
                            90
                    )
            );

            collezione.aggiungiGioco(
                    new GiocoDaTavolo(
                            "T2",
                            "Carcassonne",
                            2000,
                            29.99,
                            5,
                            45
                    )
            );

        } catch (Exception e) {
            System.out.println("Errore inizializzazione: " + e.getMessage());
        }
    }

    private static void mostraTuttiIGiochi(Collezione collezione) {
        collezione.ottieniTuttiGiochi()
                .forEach(System.out::println);
    }

    private static void cercaGiocoPerId(Collezione collezione, Scanner scanner)
            throws ValidationException, NotFoundException {

        System.out.print("Inserisci ID: ");
        String id = scanner.nextLine();

        Gioco gioco = collezione.cercaPerID(id);
        System.out.println(gioco);
    }

    private static void rimuoviGioco(Collezione collezione, Scanner scanner)
            throws ValidationException, NotFoundException {

        System.out.print("Inserisci ID da rimuovere: ");
        String id = scanner.nextLine();

        collezione.rimuoviGioco(id);
        System.out.println("Gioco rimosso correttamente.");
    }

    private static void ricercaPerPrezzo(Collezione collezione, Scanner scanner)
            throws ValidationException {

        System.out.print("Inserisci prezzo massimo: ");
        double prezzoMax = Double.parseDouble(scanner.nextLine());

        collezione.ricercaPerPrezzoMassimo(prezzoMax)
                .forEach(System.out::println);
    }

    private static void ricercaPerNumeroGiocatori(Collezione collezione, Scanner scanner)
            throws ValidationException {

        System.out.print("Inserisci numero di giocatori: ");
        int n = Integer.parseInt(scanner.nextLine());

        collezione.ricercaGiochiDaTavoloPerNumeroGiocatori(n)
                .forEach(System.out::println);
    }

    private static void mostraStatistiche(Collezione collezione)
            throws ValidationException {

        System.out.println("Numero videogiochi: " + collezione.countVideogiochi());
        System.out.println("Numero giochi da tavolo: " + collezione.countGiochiDaTavolo());
        System.out.println("Gioco più caro: " + collezione.trovaGiocoPrezzoPiuAlto());
        System.out.println("Prezzo medio: " + collezione.calcolaMediaPrezzi());
    }
}
