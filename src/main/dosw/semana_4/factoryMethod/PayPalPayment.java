package dosw.semana_4.factoryMethod;

public class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Pago con PAYPAL por $" + amount);
    }
}