package dosw.semana_3.patrones.ejercicio01;

public class UsaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        return switch (type) {
            case "PAYPAL" -> new PayPalStrategy();
            case "STRIPE" -> new StripeStrategy();
            case "TARJETA" -> new TarjetaStrategy();
            default -> throw new IllegalArgumentException("Medio no soportado en USA: " + type);
        };
    }
}
