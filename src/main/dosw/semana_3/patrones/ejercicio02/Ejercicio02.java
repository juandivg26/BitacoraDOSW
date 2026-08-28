package dosw.semana_3.patrones.ejercicio02;

/**
 * #02 - Sistema de Notificaciones Multicanal.
 * Patrones combinados: Observer + Factory Method.
 * Observer desacopla el Pedido de los canales de notificacion;
 * cada Observer usa su propia Factory Method para construir el
 * mensaje con el formato correcto de su canal.
 */
public class Ejercicio02 {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("ORD-100", "pendiente");
        // Este usuario solo tiene activos Email y Push (no todos los canales estan activos)
        pedido.addObserver(new EmailNotifier());
        pedido.addObserver(new PushNotifier());

        System.out.println("--- Pedido pasa a 'enviado' (canales activos: Email, Push) ---");
        pedido.cambiarEstado("enviado");

        System.out.println("--- Otro usuario, con SMS tambien activo ---");
        Pedido pedido2 = new Pedido("ORD-101", "pendiente");
        pedido2.addObserver(new EmailNotifier());
        pedido2.addObserver(new SmsNotifier());
        pedido2.addObserver(new PushNotifier());
        pedido2.cambiarEstado("enviado");
    }
}
