package dosw.semana_4.abstractFactory;

public class PlayStationGame implements Game{
    @Override
    public void start() {
        System.out.println("Iniciando juego en PlayStation...");
    }
}
