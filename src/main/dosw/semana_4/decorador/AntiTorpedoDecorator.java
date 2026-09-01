package dosw.semana_4.decorador;

public class AntiTorpedoDecorator extends BarcoBaseDecorator {
    public AntiTorpedoDecorator(Barco barco) { super(barco); }

    @Override
    public String getDescripcion() {
        return barco.getDescripcion() + " + Sistema Antitorpedos";
    }
    @Override
    public int ataque() {
        return barco.ataque() + 20;
    }
}
