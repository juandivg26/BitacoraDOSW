package dosw.semana_4.factoryMethod;

public class BankTransferProcessor extends PaymentProcessor {
    @Override
    public Payment createPayment(double amount) {
        return new BankTransferPayment();
    }
}