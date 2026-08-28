package dosw.semana_3.patrones.ejercicio08;

public class KitchenService implements OrderObserver {
    @Override
    public void onConfirmed(Order order) {
        System.out.println("[Cocina] Preparando: " + order.describe());
    }
}
