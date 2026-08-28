package dosw.semana_1.streams;

import java.util.List;

/**
 * Ejercicio 05 - Transacciones bancarias.
 * Usar peek() para ver cada transaccion procesada, verificar si existe
 * al menos una transaccion no aprobada y retornar true/false indicando
 * si el lote es valido.
 * Operadores principales: peek() - anyMatch()
 */
public class Ejercicio5 {

    static class Transaction {
        String id;
        double amount;
        boolean approved;

        Transaction(String id, double amount, boolean approved) {
            this.id = id;
            this.amount = amount;
            this.approved = approved;
        }

        @Override
        public String toString() {
            return "Transaction{id='" + id + "', amount=" + amount + ", approved=" + approved + "}";
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("T1", 150.0, true),
                new Transaction("T2", 320.5, true),
                new Transaction("T3", 80.0, false),
                new Transaction("T4", 500.0, true)
        );

        boolean tieneNoAprobadas = transactions.stream()
                .peek(t -> System.out.println("Procesando: " + t))
                .anyMatch(t -> !t.approved);

        boolean loteValido = !tieneNoAprobadas;

        System.out.println("¿Existe alguna transaccion no aprobada?: " + tieneNoAprobadas);
        System.out.println("¿El lote de transacciones es valido?: " + loteValido);
    }
}
