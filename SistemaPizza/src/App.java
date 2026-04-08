import decoratorpizza.*;
import observer.*;
import singletonpizza.DbConnection;
import singletonpizza.GestoreOrdini;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DbConnection.getIstanzaDb().connect();
        GestoreOrdini gestore = GestoreOrdini.getInstance();

        // registra gli observer
        gestore.aggiungiObserver(new Cucina());
        gestore.aggiungiObserver(new Forno());
        gestore.aggiungiObserver(new Consegna());

        Scanner sc = new Scanner(System.in);

        boolean esci = false;
        while (!esci) {
            System.out.println("\n========= PIZZERIA MENU =========");
            System.out.println("1 - Crea nuova pizza");
            System.out.println("2 - Aggiungi ingrediente extra");
            System.out.println("3 - Visualizza ordine corrente");
            System.out.println("4 - Conferma ordine");
            System.out.println("5 - Cambia stato ordine");
            System.out.println("6 - Visualizza storico ordini");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");

            int scelta = sc.nextInt();

            switch (scelta) {
                case 1 -> {
                    System.out.println("Scegli pizza: 1) Margherita  2) Diavola");
                    int b = sc.nextInt();
                    Pizza pizza = switch (b) {
                        case 2 -> new Diavola();
                        default -> new Margherita();
                    };
                    gestore.setPizzaCorrente(pizza);
                    System.out.println("Pizza creata: " + pizza.getDescrizione());
                }
                case 2 -> {
                    if (gestore.getPizzaCorrente() == null) {
                        System.out.println("Prima crea una pizza (opzione 1).");
                        break;
                    }
                    System.out.println("Scegli ingrediente: 1) Mozzarella Extra  2) Salame  3) Funghi  4) Olive");
                    int e = sc.nextInt();
                    Pizza decorata = switch (e) {
                        case 1 -> if (disponibile("mozzarellaextra")) {
                            new MozzarellaExtra(gestore.getPizzaCorrente());
                            consumaIngrediente("mozzarellaextra");
                        } else System.out.println("La mozzarella non è più disponibile");
                        case 2 -> if (disponibile("salame")) {
                            new Salame(gestore.getPizzaCorrente());
                            consumaIngrediente("salame");
                        } else System.out.println("Il salame non è più disponibile");
                        case 3 -> if (disponibile("funghi")) {
                            new Funghi(gestore.getPizzaCorrente());
                            consumaIngrediente("funghi");
                        } else System.out.println("I funghi non sono più disponibili");
                        case 4 -> if (disponibile("Olive")) {
                            new Olive(gestore.getPizzaCorrente());
                            consumaIngrediente("olive");
                        } else System.out.println("Le olive non sono più disponibili");
                        default -> 
                    };
                    gestore.decoraPizzaCorrente(decorata);
                    System.out.println("Aggiunto! Pizza: " + decorata.getDescrizione());
                }
                case 3 -> gestore.visualizzaOrdineCorrente();
                case 4 -> gestore.confermaOrdine();
                case 5 -> {
                    gestore.visualizzaStoricoDb();
                    System.out.println("Scegli id pizza da cambiare: ");
                    int cambioId = sc.nextInt();
                    System.out.println("Scegli stato pizza:");
                    System.out.println("1) CREATO  2) IN PREPARAZIONE  3) IN COTTURA  4) PRONTO  5) CONSEGNATO");
                    int cambioStato = sc.nextInt();
                    String statoPizza = switch (cambioStato) {
                        case 2 -> "IN PREPARAZIONE";
                        case 3 -> "IN COTTURA";
                        case 4 -> "PRONTO";
                        case 5 -> "CONSEGNATO";
                        default -> "CREATO";
                    };
                    gestore.cambiaStatoOrdineDb(cambioId, statoPizza);
                }
                case 6 -> gestore.visualizzaStoricoDb();
                case 0 -> esci = true;
                default -> System.out.println("Scelta non valida.");
            }
        }

        sc.close();
        System.out.println("Arrivederci!");
    }
}