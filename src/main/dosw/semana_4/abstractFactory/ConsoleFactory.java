package dosw.semana_4.abstractFactory;

public interface ConsoleFactory {
    Controller createController();
    UI createUI();
    Game createGame();
}
