package dosw.semana_4.factoryMethod;

public class PayPalProcessor extends PaymentProcessor {
    @Override
    public Payment createPayment(double amount) {
        return new PayPalPayment();
    }
}