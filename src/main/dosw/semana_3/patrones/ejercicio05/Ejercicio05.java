package dosw.semana_3.patrones.ejercicio05;

/**
 * #05 - Integracion con Sistema Bancario Antiguo.
 * Patrones combinados: Adapter + Facade.
 * Adapter traduce la interfaz moderna PaymentProcessor hacia el
 * LegacyBankService (unidades y metodos incompatibles). Facade
 * esconde los 8 pasos de inicializacion y expone un unico metodo
 * simple, usando el Adapter internamente.
 */
public class Ejercicio05 {
    public static void main(String[] args) {
        BankFacade facade = new BankFacade("ACC-001");
        System.out.println("--- Desarrollador solo llama procesarPago() ---");
        facade.procesarPago(150.75);
    }
}
