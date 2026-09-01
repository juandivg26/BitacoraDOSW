package dosw.semana_4.composite;

public class WarehouseApp {
    public static void main(String[] args) {
        Product laptop = new Product("Laptop", 1200);
        Product mouse = new Product("Mouse", 40);
        Product keyboard = new Product("Keyboard", 80);

        Box accessoriesBox = new Box("Accessories Box");
        accessoriesBox.add(mouse);
        accessoriesBox.add(keyboard);

        Box mainBox = new Box("Main Box");
        mainBox.add(laptop);
        mainBox.add(accessoriesBox);

        System.out.println("Total price: $" + mainBox.getPrice());
    }
}