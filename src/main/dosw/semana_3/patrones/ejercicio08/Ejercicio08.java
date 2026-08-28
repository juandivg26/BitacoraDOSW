package dosw.semana_3.patrones.ejercicio08;

/**
 * #08 - Sistema de Pedidos en Restaurante.
 * Patrones combinados: Builder + Observer.
 * Builder garantiza que el pedido este completo y sea inmutable antes
 * de existir; Observer notifica a cocina, facturacion y domicilio
 * cuando el pedido se confirma, sin que Order los conozca directamente.
 */
public class Ejercicio08 {
    public static void main(String[] args) {
        Order order = new OrderBuilder()
                .setSize("grande")
                .setMeat("doble carne")
                .addTopping("queso", "lechuga")
                .addSide("papas", "gaseosa")
                .build();

        order.addObserver(new KitchenService());
        order.addObserver(new BillingService());
        order.addObserver(new DeliveryService());

        order.confirm();
    }
}
