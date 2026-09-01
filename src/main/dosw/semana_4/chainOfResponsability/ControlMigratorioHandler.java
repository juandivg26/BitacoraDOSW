package dosw.semana_4.chainOfResponsability;

public abstract class ControlMigratorioHandler implements ControlMigratorio {
    protected ControlMigratorio siguiente;

    @Override
    public void setSiguiente(ControlMigratorio siguiente) {
        this.siguiente = siguiente;
    }
}
