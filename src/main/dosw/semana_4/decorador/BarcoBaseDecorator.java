package dosw.semana_4.decorador;

public abstract class BarcoBaseDecorator implements Barco {
    protected Barco barco;

    public BarcoBaseDecorator(Barco barco) {
        this.barco = barco;
    }
    @Override
    public String getDescripcion() {
        return barco.getDescripcion();
    }
    @Override
    public int ataque() {
        return barco.ataque();
    }
    @Override
    public int defensa() {
        return barco.defensa();
    }
}
