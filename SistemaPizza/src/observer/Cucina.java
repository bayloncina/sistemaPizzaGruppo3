package observer;

public class Cucina implements OrdineObserver{

    @Override
    public void statoOrdine(String stato) {
        if (stato.equals("IN PREPARAZIONE")) {
            System.out.println("🍕 Cucina: ordine entrato in preparazione!");
        }
    }
    
}
