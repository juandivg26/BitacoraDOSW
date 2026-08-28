package dosw.semana_3.patrones.ejercicio10;

/** La imagen base nunca cambia; los filtros solo la envuelven. */
public class BaseImage implements Image {
    private final String fileName;

    public BaseImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String render() {
        return "imagen(" + fileName + ")";
    }
}
