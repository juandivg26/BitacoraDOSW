package dosw.semana_3.patrones.ejercicio10;

/** Decorator base: envuelve una Image y expone el wrapper anterior para poder deshacer. */
public abstract class ImageDecorator implements Image {
    protected final Image wrapped;

    protected ImageDecorator(Image wrapped) {
        this.wrapped = wrapped;
    }

    public Image getWrapped() {
        return wrapped;
    }
}
