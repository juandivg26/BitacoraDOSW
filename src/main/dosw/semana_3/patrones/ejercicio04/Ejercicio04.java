package dosw.semana_3.patrones.ejercicio04;

/**
 * #04 - Plataforma de Videojuegos - Personajes.
 * Patrones combinados: Builder + Decorator.
 * Builder construye el personaje base configurable al inicio de la
 * partida; Decorator envuelve ese personaje en runtime para agregar
 * poderes temporales sin tocar la clase base ni generar una explosion
 * combinatoria de subclases.
 */
public class Ejercicio04 {
    public static void main(String[] args) {
        CharacterDirector director = new CharacterDirector();

        BaseCharacter warrior = director.guerreroElite(new CharacterBuilder());
        System.out.println("Base: " + warrior.attack());

        Character powered = new ShieldDecorator(new SpeedDecorator(warrior));
        System.out.println("Con poderes: " + powered.attack());

        Character mago = director.magoDeFuego(new CharacterBuilder());
        Character magoInvisible = new InvisibilityDecorator(mago);
        System.out.println("Mago con sigilo: " + magoInvisible.attack());
    }
}
