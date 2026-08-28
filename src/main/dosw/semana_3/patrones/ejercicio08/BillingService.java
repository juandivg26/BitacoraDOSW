package dosw.semana_3.patrones.ejercicio08;

public class BillingService implements OrderObserver {
    @Override
    public void onConfirmed(Order order) {
        System.out.println("[Facturacion] Generando cuenta para: " + order.describe());
    }
}
