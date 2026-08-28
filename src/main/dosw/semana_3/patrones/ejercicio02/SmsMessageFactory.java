package dosw.semana_3.patrones.ejercicio02;

public class SmsMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        String texto = "Pedido " + event.getOrderId() + ": " + event.getNewStatus();
        if (texto.length() > 160) texto = texto.substring(0, 160);
        return new Message(texto);
    }
}
