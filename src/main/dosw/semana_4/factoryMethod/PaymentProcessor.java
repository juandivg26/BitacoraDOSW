package dosw.semana_4.factoryMethod;

public abstract class PaymentProcessor {
    public abstract Payment createPayment(double amount);
    public void processPayment(double amount) {
        Payment payment = createPayment(amount);
        payment.pay(amount);
    }
}
