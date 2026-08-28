package dosw.semana_3.patrones.ejercicio02;

public class PushMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        return new Message("{\"orderId\":\"" + event.getOrderId()
                + "\",\"status\":\"" + event.getNewStatus() + "\"}");
    }
}
