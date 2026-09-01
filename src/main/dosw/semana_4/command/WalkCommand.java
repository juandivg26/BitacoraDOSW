package dosw.semana_4.command;

public class WalkCommand implements Command {
    private GameCharacter character;
    public WalkCommand(GameCharacter character) { this.character = character; }
    @Override
    public void execute() { character.walk(); }
}
