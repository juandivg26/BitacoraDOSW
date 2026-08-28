SEMANA No 2 — Bitácora Pokémon
Datos de Entrenador:
Nombre y Apellido: Juan Diego Valderrama Gaviria
Curso: DOSW
Nivel 1 — Entrenador Novato · Operaciones básicas con Streams
Ejercicio 01 — Pokémon Tipo Fuego

Dada una lista de Pokémon con nombre y tipo, obtener únicamente aquellos cuyo tipo sea Fuego.

Explicación: filter() compara el tipo de cada Pokémon con "Fuego" y map() extrae solo el nombre.

Ejercicio 02 — Pokédex Gritona

Transformar todos los nombres de Pokémon a mayúsculas.

Explicación: map(String::toUpperCase) transforma cada nombre del stream.

Ejercicio 03 — Poder Total del Equipo

Calcular la suma total de niveles del equipo.

Explicación: reduce(0, Integer::sum) acumula el total de niveles.

Ejercicio 04 — Pokémon Alfa

Encontrar el Pokémon con el nivel más alto dentro del equipo.

Explicación: max(Comparator.comparingInt(...)) obtiene el elemento con mayor nivel.

Ejercicio 05 — Pokémon Legendarios

Contar cuántos Pokémon del equipo tienen nivel superior a 80.

Explicación: filter() deja solo los de nivel > 80 y count() cuenta cuántos quedaron.

Nivel 2 — Entrenador Intermedio · Filtrado y ordenamiento avanzado
Ejercicio 06 — Pokédex Sin Duplicados

Generar una colección donde cada Pokémon aparezca una sola vez.

Explicación: distinct() elimina los elementos repetidos del stream.

Ejercicio 07 — Orden del Profesor Oak

Ordenar alfabéticamente los nombres de los Pokémon.

Explicación: sorted() usa el orden natural de String (alfabético).

Ejercicio 08 — Evoluciones Preparadas

Obtener únicamente los Pokémon listos para evolucionar.

Explicación: filter(PokemonEvo::puedeEvolucionar) deja pasar solo los que pueden evolucionar.

Nivel 3 — Líder de Gimnasio · Manipulación de objetos complejos
Ejercicio 09 — Equipo Élite

Mostrar únicamente los Pokémon cuyo poderCombate sea superior a 500.

Explicación: filter() compara getPoderCombate() contra 500.

Ejercicio 10 — Pokédex Compacta

Generar una lista que contenga únicamente los nombres de todos los Pokémon del equipo.

Explicación: map(Pokemon::getNombre) proyecta cada objeto a su nombre y collect() arma la lista final.

Ejercicio 11 — Poder Promedio

Calcular el promedio de poderCombate de todos los Pokémon del equipo.

Explicación: mapToDouble() convierte a DoubleStream y average() calcula el promedio.

Ejercicio 12 — Campeón Regional

Obtener el Pokémon con mayor poderCombate de toda la lista.

Explicación: max(Comparator.comparingDouble(...)) obtiene el elemento con mayor poder de combate.

Ejercicio 13 — Organizar por Tipo

Agrupar todos los Pokémon por su tipo.

Explicación: groupingBy(Pokemon::getTipo, ...) agrupa y mapping() proyecta cada grupo a solo los nombres.

Ejercicio 14 — Organizar por Región

Agrupar los Pokémon según su región de origen.

Explicación: Mismo patrón que el ejercicio 13, agrupando por getRegion().

Nivel 4 — Alto Mando · Objetos anidados y comparaciones
Ejercicio 15 — Maestro de Gimnasios

Encontrar el entrenador con más medallas.

Explicación: max(Comparator.comparingInt(Entrenador::getMedallas)) obtiene el entrenador con más medallas.

Ejercicio 16 — Entrenadores Experimentados

Mostrar únicamente los entrenadores con más de 5 medallas.

Explicación: filter() compara getMedallas() contra 5.

Ejercicio 17 — Equipo Más Poderoso

Calcular qué entrenador tiene la suma total de poderCombate más alta entre todos sus Pokémon.

Explicación: Se usa max() con un comparador que calcula, por cada entrenador, la suma (mapToDouble + sum) del poder de combate de su equipo.

Nivel 5 — Campeón de la Liga Pokémon DOSW · Análisis avanzado y rankings
Ejercicio 18 — Top 5 Pokémon Más Fuertes

Generar un ranking de los 5 Pokémon con mayor poderCombate de toda la Pokédex.

Explicación: sorted() con comparador invertido por poder de combate y limit(5) recorta el top 5; el ranking se imprime con IntStream.range() para no usar ciclos tradicionales.

Ejercicio 19 — Top 3 Entrenadores

Ranking de los 3 mejores entrenadores: 1° más medallas, 2° mayor poder acumulado, 3° orden alfabético como desempate.

Explicación: Se construye un Comparator combinado con thenComparing() aplicando los tres criterios de desempate en orden, y sorted()+limit(3) arma el ranking.

Ejercicio 20 — Pokédex Analítica

Construir una estructura que muestre cantidad de Pokémon por tipo, por región, cantidad de legendarios, promedio de nivel y el Pokémon más fuerte.

Explicación: Se combinan varios streams independientes sobre la misma lista: groupingBy+counting() para tipo y región, filter+count() para legendarios, mapToInt+average() para el promedio y max(Comparator) para el más fuerte.
