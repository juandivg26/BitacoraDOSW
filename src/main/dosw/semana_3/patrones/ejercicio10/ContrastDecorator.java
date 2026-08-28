package dosw.semana_3.patrones.ejercicio10;

public class ContrastDecorator extends ImageDecorator {
    public ContrastDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + contraste";
    }
}
