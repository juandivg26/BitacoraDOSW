package dosw.semana_3.patrones.ejercicio01;

public class PayPalStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[PayPal] Procesando pago internacional de $" + amount + ".");
    }
}
