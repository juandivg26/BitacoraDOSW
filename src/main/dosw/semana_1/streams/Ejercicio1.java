package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 01 - Numeros pares mayores a diez.
 * Dada una lista de numeros enteros, obtener una nueva lista solo
 * con los numeros pares mayores a 10.
 * Operador principal: filter()
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(3, 8, 10, 12, 15, 18, 20);

        List<Integer> paresMayoresADiez = numeros.stream()
                .filter(n -> n > 10 && n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Entrada: " + numeros);
        System.out.println("Pares mayores a 10: " + paresMayoresADiez);
    }
}
