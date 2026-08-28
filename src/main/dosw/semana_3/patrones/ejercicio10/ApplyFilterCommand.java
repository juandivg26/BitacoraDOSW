package dosw.semana_3.patrones.ejercicio10;

import java.util.function.Function;

/** Comando que aplica un filtro (Decorator) sobre la imagen actual del editor. */
public class ApplyFilterCommand implements ImageCommand {
    private final ImageEditor editor;
    private final Function<Image, Image> decoratorFactory;
    private Image previous;

    public ApplyFilterCommand(ImageEditor editor, Function<Image, Image> decoratorFactory) {
        this.editor = editor;
        this.decoratorFactory = decoratorFactory;
    }

    @Override
    public void execute() {
        previous = editor.getCurrent();
        Image decorated = decoratorFactory.apply(previous);
        editor.setCurrent(decorated);
        System.out.println("  [execute] " + decorated.render());
    }

    @Override
    public void undo() {
        editor.setCurrent(previous);
        System.out.println("  [undo] Vuelve a: " + previous.render());
    }
}
