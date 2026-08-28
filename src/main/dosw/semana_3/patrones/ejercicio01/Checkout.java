package dosw.semana_3.patrones.ejercicio01;

/** El Checkout solo conoce PaymentStrategy; nunca decide que implementacion usar. */
public class Checkout {
    public void pay(PaymentStrategy strategy, double amount) {
        strategy.process(amount);
    }
}
