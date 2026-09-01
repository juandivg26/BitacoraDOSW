package dosw.semana_4.composite;

import java.util.ArrayList;
import java.util.List;

public class Box implements Item {
    private String name;
    private List<Item> items;

    public Box(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
