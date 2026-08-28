package dosw.semana_3.patrones.ejercicio04;

/** Personaje base construido por el Builder al inicio de la partida. */
public class BaseCharacter implements Character {
    private final String name;
    private final String armor;
    private final String weapon;
    private final String skill;

    public BaseCharacter(String name, String armor, String weapon, String skill) {
        this.name = name;
        this.armor = armor;
        this.weapon = weapon;
        this.skill = skill;
    }

    @Override
    public String attack() {
        return name + " ataca con " + weapon + " (armadura: " + armor + ", habilidad: " + skill + ")";
    }
}
