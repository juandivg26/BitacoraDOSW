package dosw.semana_4.factoryMethod;

public class CreditCardProcessor extends PaymentProcessor{

    @Override
    public Payment createPayment(double amount){
        return new CreditCardPayment();
    }
}
