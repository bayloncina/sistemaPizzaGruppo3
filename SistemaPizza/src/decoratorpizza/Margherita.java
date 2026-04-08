package decoratorpizza;

class Margherita implements Pizza {

    public String getDescrizione() {
        return "Margherita";
    }

    public double getCosto() {
        return 4.5;
    }
}