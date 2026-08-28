package dosw.semana_1.streams;

/**
 * Clase de apoyo utilizada por los Ejercicio3 y Ejercicio4
 * (id, name, age, active).
 */
public class User {

    private final int id;
    private final String name;
    private final int age;
    private final boolean active;

    public User(int id, String name, int age, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return name;
    }
}
