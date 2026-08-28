package dosw.semana_3.patrones.ejercicio04;

public class ShieldDecorator extends CharacterDecorator {
    public ShieldDecorator(Character wrapped) {
        super(wrapped);
    }

    @Override
    public String attack() {
        return wrapped.attack() + " + [escudo de hielo activo]";
    }
}
