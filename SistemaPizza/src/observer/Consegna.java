package observer;

public class Consegna implements OrdineObserver{

    @Override
    public void statoOrdine(String stato) {
        if (stato.equals("PRONTO")) {
            System.out.println("L'ordine pronto per la consegna!");
        }
    }
    
}
