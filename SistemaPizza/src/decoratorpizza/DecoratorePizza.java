package decoratorpizza;

abstract class DecoratorePizza implements Pizza {

    protected Pizza pizza;

    public DecoratorePizza(Pizza pizza) {
        this.pizza = pizza;
    }
}