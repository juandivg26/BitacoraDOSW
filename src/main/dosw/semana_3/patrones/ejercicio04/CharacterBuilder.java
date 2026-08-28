package dosw.semana_3.patrones.ejercicio04;

/** Builder: construye el personaje paso a paso, evitando un constructor con muchos parametros. */
public class CharacterBuilder {
    private String name = "Unnamed";
    private String armor = "none";
    private String weapon = "fists";
    private String skill = "none";

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setArmor(String armor) {
        this.armor = armor;
        return this;
    }

    public CharacterBuilder setWeapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    public CharacterBuilder setSkill(String skill) {
        this.skill = skill;
        return this;
    }

    public BaseCharacter build() {
        return new BaseCharacter(name, armor, weapon, skill);
    }
}
