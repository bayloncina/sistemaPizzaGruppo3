package observer;

public class Cucina implements OrdineObserver{

    @Override
    public void statoOrdine() {
        System.out.println("L'ordine è Preparazione");
    }
    
}
