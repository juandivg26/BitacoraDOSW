package dosw.semana_3.patrones.ejercicio08;

import java.util.ArrayList;
import java.util.List;

/** Builder: arma el pedido personalizado paso a paso; el resultado es inmutable. */
public class OrderBuilder {
    private String size = "mediana";
    private String meat = "res";
    private final List<String> toppings = new ArrayList<>();
    private final List<String> sides = new ArrayList<>();

    public OrderBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public OrderBuilder setMeat(String meat) {
        this.meat = meat;
        return this;
    }

    public OrderBuilder addTopping(String... items) {
        toppings.addAll(List.of(items));
        return this;
    }

    public OrderBuilder addSide(String... items) {
        sides.addAll(List.of(items));
        return this;
    }

    public Order build() {
        return new Order(size, meat, toppings, sides);
    }
}
