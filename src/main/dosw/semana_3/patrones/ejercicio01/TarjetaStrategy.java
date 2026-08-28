package dosw.semana_3.patrones.ejercicio01;

public class TarjetaStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[Tarjeta] Cobrando $" + amount + " a la tarjeta del cliente.");
    }
}
