package dosw.semana_4.command;

public class JumpCommand implements Command {
    private GameCharacter character;
    public JumpCommand(GameCharacter character) { this.character = character; }
    @Override
    public void execute() { character.jump(); }
}
