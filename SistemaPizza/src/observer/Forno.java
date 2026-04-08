package observer;

public class Forno implements OrdineObserver {

    @Override
    public void statoOrdine() {
        System.out.println("L'ordine è in cottura");
    
}}
