package dosw.semana_4.abstractFactory;

public class GameEngine {
    private Controller controller;
    private Game game;
    private UI ui;

    public GameEngine(ConsoleFactory factory) {
        controller = factory.createController();
        game = factory.createGame();
        ui = factory.createUI();
    }

    public void run() {
        controller.connect();
        game.start();
        ui.render();
    }
}
