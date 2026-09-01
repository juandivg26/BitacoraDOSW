package dosw.semana_4.abstractFactory;

public class PlayStationFactory implements ConsoleFactory {
    @Override
    public Controller createController() {
        return new PlayStationController();
    }
    @Override
    public Game createGame() {
        return new PlayStationGame();
    }
    @Override
    public UI createUI() {
        return new PlayStationUI();
    }
}
