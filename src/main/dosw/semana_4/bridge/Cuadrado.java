package dosw.semana_4.bridge;

public class Cuadrado extends Forma {
    public Cuadrado(Color color) {
        super(color);
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando cuadrado de color " + color.aplicarColor());
    }
}
