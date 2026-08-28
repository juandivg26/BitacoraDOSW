package dosw.semana_3.patrones.ejercicio05;

/** Adapter: traduce la interfaz moderna PaymentProcessor hacia LegacyBankService. */
public class LegacyBankAdapter implements PaymentProcessor {
    private final LegacyBankService legacy;
    private final String account;

    public LegacyBankAdapter(LegacyBankService legacy, String account) {
        this.legacy = legacy;
        this.account = account;
    }

    @Override
    public void pay(double amount) {
        int cents = (int) Math.round(amount * 100);
        if (legacy.verifyBalance(account, cents)) {
            legacy.executeTransaction(account, cents);
        }
    }
}
