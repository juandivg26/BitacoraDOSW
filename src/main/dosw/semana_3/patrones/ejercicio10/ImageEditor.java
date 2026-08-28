package dosw.semana_3.patrones.ejercicio10;

import java.util.ArrayDeque;
import java.util.Deque;

/** Mantiene la imagen actual y el historial de comandos ejecutados para poder deshacer. */
public class ImageEditor {
    private Image current;
    private final Deque<ImageCommand> history = new ArrayDeque<>();

    public ImageEditor(Image initial) {
        this.current = initial;
    }

    public Image getCurrent() { return current; }

    public void setCurrent(Image current) { this.current = current; }

    public void executeCommand(ImageCommand command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }
}
