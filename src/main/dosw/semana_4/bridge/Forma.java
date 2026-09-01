package dosw.semana_4.bridge;

public abstract class Forma {
    protected Color color;

    public Forma(Color color) {
        this.color = color;
    }

    public abstract void dibujar();
}
