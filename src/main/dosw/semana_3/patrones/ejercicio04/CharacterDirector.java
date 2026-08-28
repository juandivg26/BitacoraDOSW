package dosw.semana_3.patrones.ejercicio04;

/** Director: arma arquetipos predefinidos usando el Builder. */
public class CharacterDirector {
    public BaseCharacter guerreroElite(CharacterBuilder builder) {
        return builder.setName("Guerrero Elite")
                .setArmor("steel")
                .setWeapon("sword")
                .setSkill("rage")
                .build();
    }

    public BaseCharacter magoDeFuego(CharacterBuilder builder) {
        return builder.setName("Mago de Fuego")
                .setArmor("robe")
                .setWeapon("staff")
                .setSkill("fireball")
                .build();
    }
}
