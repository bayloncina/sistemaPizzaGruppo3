package observer;

public class Forno implements OrdineObserver {

    @Override
    public void statoOrdine(String stato) {
        if (stato.equals("IN COTTURA")) {
            System.out.println("L'ordine in cottura!");
        }
    }
}
