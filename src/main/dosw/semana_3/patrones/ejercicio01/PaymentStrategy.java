package dosw.semana_3.patrones.ejercicio01;

/** Strategy: cada medio de pago encapsula su propio algoritmo. */
public interface PaymentStrategy {
    void process(double amount);
}
