package dosw.semana_3.patrones.ejercicio05;

/** Servicio antiguo con una interfaz incompatible (metodos y unidades distintas). */
public class LegacyBankService {
    public void executeTransaction(String account, int cents) {
        System.out.println("[LegacyBank] Ejecutando transaccion en cuenta " + account
                + " por " + cents + " centavos.");
    }

    public boolean verifyBalance(String account, int cents) {
        System.out.println("[LegacyBank] Verificando saldo suficiente en " + account + "...");
        return true;
    }
}
