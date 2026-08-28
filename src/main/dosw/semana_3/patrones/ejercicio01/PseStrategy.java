package dosw.semana_3.patrones.ejercicio01;

public class PseStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[PSE] Redirigiendo a portal bancario por $" + amount + ".");
    }
}
