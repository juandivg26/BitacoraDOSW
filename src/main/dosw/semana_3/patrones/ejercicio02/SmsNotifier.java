package dosw.semana_3.patrones.ejercicio02;

public class SmsNotifier implements NotificationObserver {
    private final MessageFactory factory = new SmsMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("[SMS] Enviando -> " + msg.getContent());
    }
}
