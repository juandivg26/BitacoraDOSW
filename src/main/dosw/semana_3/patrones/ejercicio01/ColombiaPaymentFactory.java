package dosw.semana_3.patrones.ejercicio01;

public class ColombiaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        return switch (type) {
            case "PSE" -> new PseStrategy();
            case "NEQUI" -> new NequiStrategy();
            case "TARJETA" -> new TarjetaStrategy();
            default -> throw new IllegalArgumentException("Medio no soportado en Colombia: " + type);
        };
    }
}
