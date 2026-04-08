package singletonpizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import decoratorpizza.Pizza;
import observer.OrdineObserver;

public class GestoreOrdini {
    private static GestoreOrdini instance;

    private Pizza pizzaCorrente;
    private ArrayList<String> storicoOrdini = new ArrayList<>();

    private List<OrdineObserver> observerList = new ArrayList<>();

    public void aggiungiObserver(OrdineObserver o) {
        observerList.add(o);
    }

    // rimuove un observer
    public void rimuoviObserver(OrdineObserver o) {
        observerList.remove(o);
    }

    // notifica tutti gli observer con il nuovo stato
    private void notificaObservers(String stato) {
        for (OrdineObserver o : observerList) {
            o.statoOrdine(stato);
        }
    }

    // cambia lo stato dell'ordine e notifica gli observer
    public void cambiaStato(String stato) {
        System.out.println("Stato ordine aggiornato: " + stato);
        notificaObservers(stato);
    }

    // costruttore privato impedisce new GestoreOrdini all'esterno
    private GestoreOrdini() {
    }

    public static GestoreOrdini getInstance() {
        if (instance == null) {
            instance = new GestoreOrdini();
        }
        return instance;
    }

    // imposta oggetto base corrente
    public void setPizzaCorrente(Pizza pizza) {
        pizzaCorrente = pizza;
    }

    // aggiungi decoratore all'oggetto corrente
    public void decoraPizzaCorrente(Pizza pizzaDecorata) {
        pizzaCorrente = pizzaDecorata;
    }

    // getObjCorrente
    public Pizza getPizzaCorrente() {
        return pizzaCorrente;
    }

    // stampa ordine corrente
    public void visualizzaOrdineCorrente() {
        if (pizzaCorrente == null) {
            System.out.println("Nessuna pizza selezionata.");
            return;
        }
        System.out.println("Ordine corrente: " + pizzaCorrente.getDescrizione());
        System.out.printf("Costo totale: " + pizzaCorrente.getCosto());
    }

    // conferma ordine corrente
    public void confermaOrdine() {
        if (pizzaCorrente == null) {
            System.out.println("Nessuna pizza selezionata.");
            return;
        }

        String riepilogo = pizzaCorrente.getDescrizione() + " | " + pizzaCorrente.getCosto() + "E";

        try {
            Connection conn = DbConnection.getIstanzaDb().getConnection();
            String query = "INSERT INTO ordini (descrizione, costo, stato) VALUES (?, ?, 'in_preparazione')";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, pizzaCorrente.getDescrizione());
            ps.setDouble(2, pizzaCorrente.getCosto());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Errore salvataggio ordine: " + e.getMessage());
        }

        storicoOrdini.add(riepilogo);
        System.out.println("Ordine confermato: " + riepilogo);
        pizzaCorrente = null;
    }

    // stampa storico ordine
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

    public void visualizzaStoricoDb() {

        try {
            Connection conn = DbConnection.getIstanzaDb().getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ordini");
            System.out.println("\n--- STORICO ORDINI ---");
            while (rs.next()) {
                System.out.printf("[%d] %s - %.2f euro %s (%s)%n",
                        rs.getInt("id"),
                        rs.getString("descrizione"),
                        rs.getDouble("costo"),
                        rs.getString("stato"),
                        rs.getTimestamp("data_ordine"));
            }
            System.out.println("----------------------");
        } catch (SQLException e) {
            System.out.println("Errore DB: " + e.getMessage());
        }

    }

    public void cambiaStatoOrdineDb(int id, String stato) {
        Connection conn = DbConnection.getIstanzaDb().getConnection();
        String query = "UPDATE ordini SET stato = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, stato);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Errore DB: " + e.getMessage());
        }
    }

}
