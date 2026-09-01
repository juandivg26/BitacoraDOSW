package dosw.semana_4.decorador;

import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Barco barcoBase = new BarcoBase();

        Map<String, Function<Barco, Barco>> mejoras = Map.of(
                "BLINDAJE", BlindajeDecorator::new,
                "RADAR", RadarDecorator::new,
                "MISILES", MisilesDecorator::new,
                "ANTITORPEDOS", AntiTorpedoDecorator::new
        );

        List<String> configuracion = List.of("BLINDAJE", "RADAR", "MISILES");

        Barco barcoFinal = configuracion.stream()
                .map(mejoras::get)
                .reduce(barcoBase, (barco, decorador) -> decorador.apply(barco), (a, b) -> b);

        System.out.println(barcoFinal.getDescripcion());
        System.out.println("Ataque: " + barcoFinal.ataque());
        System.out.println("Defensa: " + barcoFinal.defensa());
    }
}
