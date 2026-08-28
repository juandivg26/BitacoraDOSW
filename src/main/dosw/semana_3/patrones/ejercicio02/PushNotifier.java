package dosw.semana_3.patrones.ejercicio02;

public class PushNotifier implements NotificationObserver {
    private final MessageFactory factory = new PushMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("[Push] Enviando -> " + msg.getContent());
    }
}
