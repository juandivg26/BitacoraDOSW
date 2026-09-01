package dosw.semana_4.abstractFactory;

public class XboxFactory implements ConsoleFactory {
    @Override
    public Controller createController() {
        return new XboxController();
    }
    @Override
    public Game createGame() {
        return new XboxGame();
    }
    @Override
    public UI createUI() {
        return new XboxUI();
    }
}
