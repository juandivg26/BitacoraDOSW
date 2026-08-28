package dosw.semana_3.patrones.ejercicio04;

/** Decorator base: envuelve un Character y delega, agregando comportamiento. */
public abstract class CharacterDecorator implements Character {
    protected final Character wrapped;

    protected CharacterDecorator(Character wrapped) {
        this.wrapped = wrapped;
    }

    public Character getWrapped() {
        return wrapped;
    }
}
