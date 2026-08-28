package dosw.semana_3.patrones.ejercicio10;

public class NoiseReductionDecorator extends ImageDecorator {
    public NoiseReductionDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + reduccionRuido";
    }
}
