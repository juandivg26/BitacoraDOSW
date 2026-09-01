package dosw.semana_4.command;

public class DefendCommand implements Command {
    private GameCharacter character;
    public DefendCommand(GameCharacter character) { this.character = character; }
    @Override
    public void execute() { character.defend(); }
}
