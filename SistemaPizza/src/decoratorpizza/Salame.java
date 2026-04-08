package decoratorpizza;

public class Salame extends DecoratorePizza {

    public Salame(Pizza pizza) {
        super(pizza);
    }

    public String getDescrizione() {
        return pizza.getDescrizione() + " + salame";
    }

    public double getCosto() {
        return pizza.getCosto() + 1.0;
    }
}