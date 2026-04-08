package singletonpizza;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static DbConnection istanzaDb;
    // rappresenta il canale aperto verso il database
    private Connection connection;

    // ho già creato il db pizzeria_db con la tabella ordini
    /*
     * CREATE DATABASE pizzeria_db;
     * USE pizzeria_db;
     * CREATE TABLE ordini (
     * id INT AUTO_INCREMENT PRIMARY KEY,
     * descrizione VARCHAR(255) NOT NULL,
     * costo DOUBLE NOT NULL,
     * data_ordine TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     * );
     */

    // credenziali di accesso al database
    private static final String URL = "jdbc:mysql://localhost:3306/pizzeria_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DbConnection() {
    }

    // restituisce sempre la stessa istanza, la crea solo la prima volta
    public static DbConnection getIstanzaDb() {
        if (istanzaDb == null) {
            istanzaDb = new DbConnection();
        }
        return istanzaDb;
    }

    // apre la connessione reale
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione stabilita. Connessioni attive: ");
        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }

}