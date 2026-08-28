package dosw.semana_3.patrones.ejercicio01;

/**
 * #01 - Plataforma de Pagos Inteligentes.
 * Patrones combinados: Strategy + Factory Method.
 * Strategy encapsula cada algoritmo de pago; Factory Method decide,
 * segun el pais del usuario, que Strategy concreta construir.
 * El Checkout nunca cambia ni conoce las clases concretas.
 */
public class Ejercicio01 {
    public static void main(String[] args) {
        Checkout checkout = new Checkout();

        System.out.println("--- Usuario en Colombia paga con PSE ---");
        PaymentFactory colombia = new ColombiaPaymentFactory();
        checkout.pay(colombia.create("PSE"), 150000);

        System.out.println("--- Usuario en Colombia paga con Nequi ---");
        checkout.pay(colombia.create("NEQUI"), 50000);

        System.out.println("--- Usuario en USA paga con PayPal ---");
        PaymentFactory usa = new UsaPaymentFactory();
        checkout.pay(usa.create("PAYPAL"), 99.99);

        System.out.println("--- Usuario en USA paga con Stripe ---");
        checkout.pay(usa.create("STRIPE"), 149.90);
    }
}
