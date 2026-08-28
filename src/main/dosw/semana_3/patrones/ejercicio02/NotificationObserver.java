package dosw.semana_3.patrones.ejercicio02;

/** Observer: cada canal reacciona al cambio de estado del pedido. */
public interface NotificationObserver {
    void notify(OrderEvent event);
}
