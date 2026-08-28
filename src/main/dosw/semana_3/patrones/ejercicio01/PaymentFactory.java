package dosw.semana_3.patrones.ejercicio01;

/** Factory Method: cada pais construye el proveedor de pago correcto. */
public interface PaymentFactory {
    PaymentStrategy create(String type);
}
