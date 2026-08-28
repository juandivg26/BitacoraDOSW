package dosw.semana_3.patrones.ejercicio10;

public class SepiaDecorator extends ImageDecorator {
    public SepiaDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + sepia";
    }
}
