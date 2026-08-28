package dosw.semana_3.patrones.ejercicio08;

public class DeliveryService implements OrderObserver {
    @Override
    public void onConfirmed(Order order) {
        System.out.println("[Domicilio] Preparando ruta para: " + order.describe());
    }
}
