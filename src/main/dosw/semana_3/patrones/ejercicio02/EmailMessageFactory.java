package dosw.semana_3.patrones.ejercicio02;

public class EmailMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        return new Message("<html><body>Tu pedido " + event.getOrderId()
                + " ahora esta: " + event.getNewStatus() + "</body></html>");
    }
}
