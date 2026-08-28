package dosw.semana_3.patrones.ejercicio07;

/** El documento delega el comportamiento de transicion a su estado actual. */
public class Document {
    private final String id;
    private final boolean requierePresupuesto;
    private DocumentState state = new DraftState();

    public Document(String id, boolean requierePresupuesto) {
        this.id = id;
        this.requierePresupuesto = requierePresupuesto;
    }

    public boolean isRequierePresupuesto() {
        return requierePresupuesto;
    }

    public void setState(DocumentState state) {
        this.state = state;
        System.out.println("  -> Documento " + id + " ahora esta: " + state.name());
    }

    public void approve() { state.approve(this); }

    public void reject() { state.reject(this); }

    public String getEstado() { return state.name(); }
}
