package dosw.semana_4.decorador;

public class BlindajeDecorator extends BarcoBaseDecorator {
    public BlindajeDecorator(Barco barco) { super(barco); }

    @Override
    public String getDescripcion() {
        return barco.getDescripcion() + " + Blindaje Reforzado";
    }
    @Override
    public int defensa() {
        return barco.defensa() + 30;
    }
}
