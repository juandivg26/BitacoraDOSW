package dosw.semana_3.patrones.ejercicio02;

public class OrderEvent {
    private final String orderId;
    private final String newStatus;

    public OrderEvent(String orderId, String newStatus) {
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    public String getOrderId() { return orderId; }
    public String getNewStatus() { return newStatus; }
}
