package decoratorpizza;

class Funghi extends DecoratorePizza {

    public Funghi(Pizza pizza) {
        super(pizza);
    }

    public String getDescrizione() {
        return pizza.getDescrizione() + " + funghi";
    }

    public double getCosto() {
        return pizza.getCosto() + 0.5;
    }
}