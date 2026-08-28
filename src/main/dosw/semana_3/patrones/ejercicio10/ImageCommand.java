package dosw.semana_3.patrones.ejercicio10;

/** Command: encapsula cada operacion del usuario, reversible con undo(). */
public interface ImageCommand {
    void execute();
    void undo();
}
