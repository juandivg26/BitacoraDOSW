package dosw.semana_3.patrones.ejercicio01;

public class NequiStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[Nequi] Enviando solicitud de pago QR por $" + amount + ".");
    }
}
