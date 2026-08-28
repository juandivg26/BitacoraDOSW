package dosw.semana_3.patrones.ejercicio07;

/** State: cada estado sabe a que estado puede transicionar. */
public interface DocumentState {
    void approve(Document doc);
    void reject(Document doc);
    String name();
}
