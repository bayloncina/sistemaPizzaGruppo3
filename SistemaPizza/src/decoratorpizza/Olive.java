package decoratorpizza;

public class Olive extends DecoratorePizza {

    public Olive(Pizza pizza) {
        super(pizza);
    }

    public String getDescrizione() {
        return pizza.getDescrizione() + " + Olive";
    }

    public double getCosto() {
        return pizza.getCosto() + 0.8;
    }
}