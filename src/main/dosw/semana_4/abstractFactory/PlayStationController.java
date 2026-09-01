package dosw.semana_4.abstractFactory;

public class PlayStationController implements Controller{
    @Override
    public void connect() {
        System.out.println("Conectando control de PlayStation...");
    }
}
