package dosw.semana_3.patrones.ejercicio02;

/** Factory Method: cada canal construye el mensaje con su propio formato. */
public interface MessageFactory {
    Message build(OrderEvent event);
}
