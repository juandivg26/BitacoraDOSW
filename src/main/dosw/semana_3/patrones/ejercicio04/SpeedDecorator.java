package dosw.semana_3.patrones.ejercicio04;

public class SpeedDecorator extends CharacterDecorator {
    public SpeedDecorator(Character wrapped) {
        super(wrapped);
    }

    @Override
    public String attack() {
        return wrapped.attack() + " + [velocidad extra]";
    }
}
