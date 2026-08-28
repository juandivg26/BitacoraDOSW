package dosw.semana_3.patrones.ejercicio10;

public class BrightnessDecorator extends ImageDecorator {
    public BrightnessDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + brillo";
    }
}
