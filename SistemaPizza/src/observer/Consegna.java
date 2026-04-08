package observer;

public class Consegna implements OrdineObserver{

    @Override
    public void statoOrdine() {
        System.out.println("L'ordine è pronto");
    }
    
}
