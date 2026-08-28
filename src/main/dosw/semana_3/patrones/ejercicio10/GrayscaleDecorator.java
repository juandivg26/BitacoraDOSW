package dosw.semana_3.patrones.ejercicio10;

public class GrayscaleDecorator extends ImageDecorator {
    public GrayscaleDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + blancoYNegro";
    }
}
