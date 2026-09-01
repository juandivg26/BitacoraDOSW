package dosw.semana_4.factoryMethod;

public class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Pago con TARJETA DE CRÉDITO por $" + amount);
    }
}