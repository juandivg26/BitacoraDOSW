package dosw.semana_4.bridge;

public class Circulo extends Forma {
    public Circulo(Color color) {
        super(color);
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando círculo de color " + color.aplicarColor());
    }
}
