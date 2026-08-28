package dosw.semana_3.patrones.ejercicio08;

import java.util.ArrayList;
import java.util.List;

/** Order inmutable (construido por OrderBuilder) que actua como Subject al confirmarse. */
public class Order {
    private final String size;
    private final String meat;
    private final List<String> toppings;
    private final List<String> sides;
    private final List<OrderObserver> observers = new ArrayList<>();

    Order(String size, String meat, List<String> toppings, List<String> sides) {
        this.size = size;
        this.meat = meat;
        this.toppings = List.copyOf(toppings);
        this.sides = List.copyOf(sides);
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void confirm() {
        System.out.println("--- Pedido confirmado ---");
        for (OrderObserver observer : observers) {
            observer.onConfirmed(this);
        }
    }

    public String describe() {
        return "Hamburguesa " + size + " de " + meat + ", toppings=" + toppings + ", sides=" + sides;
    }
}
