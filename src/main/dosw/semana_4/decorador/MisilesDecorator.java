package dosw.semana_4.decorador;

public class MisilesDecorator extends BarcoBaseDecorator {
    public MisilesDecorator(Barco barco) { super(barco); }

    @Override
    public String getDescripcion() {
        return barco.getDescripcion() + " + Misiles";
    }
    @Override
    public int ataque() {
        return barco.ataque() + 40;
    }
}
