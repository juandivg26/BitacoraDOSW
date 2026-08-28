package dosw.semana_3.patrones.ejercicio05;

/** Facade: oculta los pasos de inicializacion y expone un metodo simple. */
public class BankFacade {
    private final PaymentProcessor adapter;

    public BankFacade(String account) {
        System.out.println("[Facade] Paso 1-8: inicializando conexion, sesion, contexto y credenciales...");
        LegacyBankService legacyService = new LegacyBankService();
        this.adapter = new LegacyBankAdapter(legacyService, account);
        System.out.println("[Facade] Inicializacion completa.");
    }

    public void procesarPago(double monto) {
        adapter.pay(monto);
    }
}
