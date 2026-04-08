package singletonpizza;

import java.util.ArrayList;

import decoratorpizza.Pizza;

public class GestoreOrdini {
    private static GestoreOrdini instance;

    private Pizza pizzaCorrente;
    private ArrayList<String> storicoOrdini = new ArrayList<>();

    //costruttore privato impedisce new GestoreOrdini all'esterno
    private GestoreOrdini() {}

    public static GestoreOrdini getInstance() {
        if (instance == null) {
            instance = new GestoreOrdini();
        }
        return instance;
    }

    //imposta oggetto base corrente
    public void setPizzaCorrente (Pizza pizza) {
        pizzaCorrente = pizza;
    }

    //aggiungi decoratore all'oggetto corrente
    public void decoraPizzaCorrente (Pizza pizzaDecorata) {
        pizzaCorrente = pizzaDecorata;
    }

    //getObjCorrente
    public Pizza getPizzaCorrente() {
        return pizzaCorrente;
    }

    //stampa ordine corrente
    public void visualizzaOrdineCorrente() {
        if (pizzaCorrente == null) {
            System.out.println("Nessuna pizza selezionata.");
            return;
        }
        System.out.println("Ordine corrente: " + pizzaCorrente.getDescrizione());
        System.out.printf("Costo totale: " + pizzaCorrente.getCosto());
    }

    //conferma ordine corrente
    public void confermaOrdine () {
        if (pizzaCorrente == null) {
            System.out.println("Nessuna pizza selezionata.");
            return;
        }

        String riepilogo = pizzaCorrente.getDescrizione() + " | " + pizzaCorrente.getCosto() + "E";

        storicoOrdini.add(riepilogo);
        System.out.println("Ordine confermato: " + riepilogo);
        pizzaCorrente = null;
    }

    //stampa storico ordine
    public void visualizzaStorico() {
        if (storicoOrdini.isEmpty()) {
            System.out.println("Nessun ordine nello storico.");
            return;
        }

        System.out.println("── STORICO ORDINI ──");
        for (int i = 0; i < storicoOrdini.size(); i++) {
            System.out.println((i + 1) + ". " + storicoOrdini.get(i));
        }
    }
}
