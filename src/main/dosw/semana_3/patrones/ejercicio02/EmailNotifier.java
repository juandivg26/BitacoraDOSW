package dosw.semana_3.patrones.ejercicio02;

public class EmailNotifier implements NotificationObserver {
    private final MessageFactory factory = new EmailMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("[Email] Enviando -> " + msg.getContent());
    }
}
