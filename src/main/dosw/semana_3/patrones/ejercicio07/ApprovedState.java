package dosw.semana_3.patrones.ejercicio07;

public class ApprovedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] El documento ya esta aprobado.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] Un documento aprobado no puede rechazarse.");
    }

    @Override
    public String name() { return "APROBADO"; }
}
