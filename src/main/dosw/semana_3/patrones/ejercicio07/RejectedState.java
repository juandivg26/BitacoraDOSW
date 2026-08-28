package dosw.semana_3.patrones.ejercicio07;

public class RejectedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] Un documento rechazado no puede aprobarse directamente.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] El documento ya esta rechazado.");
    }

    @Override
    public String name() { return "RECHAZADO"; }
}
