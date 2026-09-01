package dosw.semana_4.factoryMethod;

public class BankTransferPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Pago con TRANSFERENCIA BANCARIA por $" + amount);
    }
}