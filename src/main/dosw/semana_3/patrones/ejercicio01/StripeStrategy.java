package dosw.semana_3.patrones.ejercicio01;

public class StripeStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[Stripe] Procesando pago con tarjeta internacional por $" + amount + ".");
    }
}
