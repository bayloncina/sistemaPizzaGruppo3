package decoratorpizza;

class MozzarellaExtra extends DecoratorePizza {

    public MozzarellaExtra(Pizza pizza) {
        super(pizza);
    }

    public String getDescrizione() {
        return pizza.getDescrizione() + " + Mozzarella extra";
    }

    public double getCosto() {
        return pizza.getCosto() + 0.5;
    }
}