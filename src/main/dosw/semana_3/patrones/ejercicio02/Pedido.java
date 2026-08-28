package dosw.semana_3.patrones.ejercicio02;

import java.util.ArrayList;
import java.util.List;

/** Subject: el Pedido notifica a sus Observers activos cuando cambia de estado. */
public class Pedido {
    private final String id;
    private String estado;
    private final List<NotificationObserver> observers = new ArrayList<>();

    public Pedido(String id, String estadoInicial) {
        this.id = id;
        this.estado = estadoInicial;
    }

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        OrderEvent event = new OrderEvent(id, nuevoEstado);
        for (NotificationObserver observer : observers) {
            observer.notify(event);
        }
    }
}
