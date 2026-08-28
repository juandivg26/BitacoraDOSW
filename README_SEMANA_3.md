# SEMANA No 3 — Patrones de Diseño Combinados (Taller 4)

## Datos personales:
- Nombre y Apellido: Juan Diego Valderrama Gaviria
- Código de Estudiante: ___________
- Curso: DOSW

Cada uno de los 10 ejercicios combina exactamente 2 patrones de diseño, siguiendo la
estructura pedida por el taller: rol de cada patrón, cómo interactúan, esquema de
clases y demostración de ejecución funcional (compilado y verificado, ver sección
"Cómo compilar y ejecutar" abajo).

**Paquete base:** `src/main/dosw/semana_3/patrones/ejercicioNN/`
(un paquete independiente por ejercicio para evitar colisiones de nombres de clases).

---

## Ejercicio #01 — Plataforma de Pagos Inteligentes

**Patrones combinados:** Strategy + Factory Method

### 1. Rol de cada patrón
**Strategy** encapsula cada algoritmo de pago (Tarjeta, PSE, Nequi, PayPal) en una clase independiente e intercambiable; el `Checkout` trabaja solo contra la interfaz `PaymentStrategy`. **Factory Method** construye el proveedor de pago correcto según el país del usuario (`ColombiaPaymentFactory`, `UsaPaymentFactory`), sin que el cliente conozca las clases concretas.

### 2. Cómo interactúan
El usuario indica su país → la Factory construye la Strategy correspondiente → el `Checkout` invoca `strategy.process(amount)` sin saber cuál implementación recibió. La Factory decide *qué* Strategy instanciar; el Checkout nunca cambia.

### 3. Esquema de código
<details>
<summary><code>Checkout.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

/** El Checkout solo conoce PaymentStrategy; nunca decide que implementacion usar. */
public class Checkout {
    public void pay(PaymentStrategy strategy, double amount) {
        strategy.process(amount);
    }
}
```
</details>

<details>
<summary><code>ColombiaPaymentFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

public class ColombiaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        return switch (type) {
            case "PSE" -> new PseStrategy();
            case "NEQUI" -> new NequiStrategy();
            case "TARJETA" -> new TarjetaStrategy();
            default -> throw new IllegalArgumentException("Medio no soportado en Colombia: " + type);
        };
    }
}
```
</details>

<details>
<summary><code>Ejercicio01.java</code></summary>

```java
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
    }
}
```
</details>

<details>
<summary><code>NequiStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

public class NequiStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[Nequi] Enviando solicitud de pago QR por $" + amount + ".");
    }
}
```
</details>

<details>
<summary><code>PayPalStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

public class PayPalStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[PayPal] Procesando pago internacional de $" + amount + ".");
    }
}
```
</details>

<details>
<summary><code>PaymentFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

/** Factory Method: cada pais construye el proveedor de pago correcto. */
public interface PaymentFactory {
    PaymentStrategy create(String type);
}
```
</details>

<details>
<summary><code>PaymentStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

/** Strategy: cada medio de pago encapsula su propio algoritmo. */
public interface PaymentStrategy {
    void process(double amount);
}
```
</details>

<details>
<summary><code>PseStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

public class PseStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[PSE] Redirigiendo a portal bancario por $" + amount + ".");
    }
}
```
</details>

<details>
<summary><code>TarjetaStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

public class TarjetaStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("[Tarjeta] Cobrando $" + amount + " a la tarjeta del cliente.");
    }
}
```
</details>

<details>
<summary><code>UsaPaymentFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio01;

public class UsaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        return switch (type) {
            case "PAYPAL" -> new PayPalStrategy();
            case "TARJETA" -> new TarjetaStrategy();
            default -> throw new IllegalArgumentException("Medio no soportado en USA: " + type);
        };
    }
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio01.Ejercicio01`

```
java -cp out dosw.semana_3.patrones.ejercicio01.Ejercicio01
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #02 — Sistema de Notificaciones Multicanal

**Patrones combinados:** Observer + Factory Method

### 1. Rol de cada patrón
**Observer** desacopla el `Pedido` (Subject) de los canales de notificación (`EmailNotifier`, `SmsNotifier`, `PushNotifier`), que reaccionan automáticamente al cambio de estado. **Factory Method** construye, dentro de cada Observer, el mensaje con el formato correcto para su canal (HTML, texto plano, JSON).

### 2. Cómo interactúan
El pedido cambia de estado → notifica a todos los Observers activos → cada Observer usa su propia `MessageFactory` para construir el mensaje adecuado a su canal → lo envía.

### 3. Esquema de código
<details>
<summary><code>Ejercicio02.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

/**
 * #02 - Sistema de Notificaciones Multicanal.
 * Patrones combinados: Observer + Factory Method.
 * Observer desacopla el Pedido de los canales de notificacion;
 * cada Observer usa su propia Factory Method para construir el
 * mensaje con el formato correcto de su canal.
 */
public class Ejercicio02 {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("ORD-100", "pendiente");
        pedido.addObserver(new EmailNotifier());
        pedido.addObserver(new SmsNotifier());
        pedido.addObserver(new PushNotifier());

        System.out.println("--- Pedido pasa a 'enviado' ---");
        pedido.cambiarEstado("enviado");

        System.out.println("--- Pedido pasa a 'entregado' ---");
        pedido.cambiarEstado("entregado");
    }
}
```
</details>

<details>
<summary><code>EmailMessageFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class EmailMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        return new Message("<html><body>Tu pedido " + event.getOrderId()
                + " ahora esta: " + event.getNewStatus() + "</body></html>");
    }
}
```
</details>

<details>
<summary><code>EmailNotifier.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class EmailNotifier implements NotificationObserver {
    private final MessageFactory factory = new EmailMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("[Email] Enviando -> " + msg.getContent());
    }
}
```
</details>

<details>
<summary><code>Message.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class Message {
    private final String content;

    public Message(String content) { this.content = content; }

    public String getContent() { return content; }
}
```
</details>

<details>
<summary><code>MessageFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

/** Factory Method: cada canal construye el mensaje con su propio formato. */
public interface MessageFactory {
    Message build(OrderEvent event);
}
```
</details>

<details>
<summary><code>NotificationObserver.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

/** Observer: cada canal reacciona al cambio de estado del pedido. */
public interface NotificationObserver {
    void notify(OrderEvent event);
}
```
</details>

<details>
<summary><code>OrderEvent.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class OrderEvent {
    private final String orderId;
    private final String newStatus;

    public OrderEvent(String orderId, String newStatus) {
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    public String getOrderId() { return orderId; }
    public String getNewStatus() { return newStatus; }
}
```
</details>

<details>
<summary><code>Pedido.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

import java.util.ArrayList;
import java.util.List;

/** Subject: el Pedido notifica a sus Observers activos cuando cambia de estado. */
public class Pedido {
    private final String id;
    private String estado;
    private final List<NotificationObserver> observers = new ArrayList<>();

    public Pedido(String id, String estadoInicial) {
        this.id = id;
        this.estado = estadoInicial;
    }

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        OrderEvent event = new OrderEvent(id, nuevoEstado);
        for (NotificationObserver observer : observers) {
            observer.notify(event);
        }
    }
}
```
</details>

<details>
<summary><code>PushMessageFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class PushMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        return new Message("{\"orderId\":\"" + event.getOrderId()
                + "\",\"status\":\"" + event.getNewStatus() + "\"}");
    }
}
```
</details>

<details>
<summary><code>PushNotifier.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class PushNotifier implements NotificationObserver {
    private final MessageFactory factory = new PushMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("[Push] Enviando -> " + msg.getContent());
    }
}
```
</details>

<details>
<summary><code>SmsMessageFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class SmsMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        String texto = "Pedido " + event.getOrderId() + ": " + event.getNewStatus();
        if (texto.length() > 160) texto = texto.substring(0, 160);
        return new Message(texto);
    }
}
```
</details>

<details>
<summary><code>SmsNotifier.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio02;

public class SmsNotifier implements NotificationObserver {
    private final MessageFactory factory = new SmsMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("[SMS] Enviando -> " + msg.getContent());
    }
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio02.Ejercicio02`

```
java -cp out dosw.semana_3.patrones.ejercicio02.Ejercicio02
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #03 — Sistema de Reportes Empresariales

**Patrones combinados:** Template Method + Factory Method

### 1. Rol de cada patrón
**Template Method** fija en `ReportGenerator` los 4 pasos del algoritmo (`fetchData → processData → applyFormat → exportFile`) en un método `final generate()`; las subclases solo sobreescriben los pasos variables. **Factory Method** (`ReportFactory.create(tipo)`) decide qué subclase concreta instanciar según el tipo solicitado.

### 2. Cómo interactúan
El cliente pide un tipo de reporte → la Factory construye la subclase correcta (PdfReport/ExcelReport/CsvReport) → el cliente llama `generate()` → el Template Method ejecuta los 4 pasos en orden, usando la implementación específica para los pasos variables.

### 3. Esquema de código
<details>
<summary><code>CsvReport.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio03;

public class CsvReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3) Aplicando formato CSV (separador por comas)...");
    }

    @Override
    protected void exportFile() {
        System.out.println("  4) Exportando reporte.csv");
    }
}
```
</details>

<details>
<summary><code>Ejercicio03.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio03;

/**
 * #03 - Sistema de Reportes Empresariales.
 * Patrones combinados: Template Method + Factory Method.
 * Template Method fija los 4 pasos del algoritmo en ReportGenerator;
 * Factory Method decide, segun el tipo pedido, que subclase concreta
 * (PDF/Excel/CSV) construir para ejecutar esos pasos.
 */
public class Ejercicio03 {
    public static void main(String[] args) {
        System.out.println("--- Cliente pide reporte PDF ---");
        ReportGenerator pdf = ReportFactory.create("PDF");
        pdf.generate();

        System.out.println("--- Cliente pide reporte CSV ---");
        ReportGenerator csv = ReportFactory.create("CSV");
        csv.generate();

        System.out.println("--- Cliente pide reporte Excel ---");
        ReportGenerator excel = ReportFactory.create("EXCEL");
        excel.generate();
    }
}
```
</details>

<details>
<summary><code>ExcelReport.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio03;

public class ExcelReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3) Aplicando formato Excel (hojas, celdas, formulas)...");
    }

    @Override
    protected void exportFile() {
        System.out.println("  4) Exportando reporte.xlsx");
    }
}
```
</details>

<details>
<summary><code>PdfReport.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio03;

public class PdfReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3) Aplicando formato PDF (paginas, encabezados)...");
    }

    @Override
    protected void exportFile() {
        System.out.println("  4) Exportando reporte.pdf");
    }
}
```
</details>

<details>
<summary><code>ReportFactory.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio03;

/** Factory Method: crea la instancia de reporte correcta segun el tipo solicitado. */
public class ReportFactory {
    public static ReportGenerator create(String type) {
        return switch (type) {
            case "PDF" -> new PdfReport();
            case "EXCEL" -> new ExcelReport();
            case "CSV" -> new CsvReport();
            default -> throw new IllegalArgumentException("Tipo de reporte no soportado: " + type);
        };
    }
}
```
</details>

<details>
<summary><code>ReportGenerator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio03;

/** Template Method: define el esqueleto fijo del algoritmo de reporte. */
public abstract class ReportGenerator {

    public final void generate() {
        fetchData();
        processData();
        applyFormat();
        exportFile();
    }

    protected void fetchData() {
        System.out.println("  1) Obteniendo datos crudos de la base de datos...");
    }

    protected void processData() {
        System.out.println("  2) Procesando y consolidando informacion...");
    }

    protected abstract void applyFormat();

    protected abstract void exportFile();
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio03.Ejercicio03`

```
java -cp out dosw.semana_3.patrones.ejercicio03.Ejercicio03
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #04 — Plataforma de Videojuegos — Personajes

**Patrones combinados:** Builder + Decorator

### 1. Rol de cada patrón
**Builder** construye el personaje base configurable al inicio de la partida (`CharacterBuilder`, con `CharacterDirector` para arquetipos predefinidos), evitando un constructor con muchos parámetros. **Decorator** agrega poderes temporales en tiempo de ejecución (`ShieldDecorator`, `SpeedDecorator`, `InvisibilityDecorator`) sin modificar la clase base del personaje.

### 2. Cómo interactúan
El Builder crea el personaje base → durante la partida, uno o varios Decorators envuelven ese personaje con poderes → al terminar el efecto, el wrapper se descarta sin afectar la clase base.

### 3. Esquema de código
<details>
<summary><code>BaseCharacter.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

/** Personaje base construido por el Builder al inicio de la partida. */
public class BaseCharacter implements Character {
    private final String name;
    private final String armor;
    private final String weapon;
    private final String skill;

    public BaseCharacter(String name, String armor, String weapon, String skill) {
        this.name = name;
        this.armor = armor;
        this.weapon = weapon;
        this.skill = skill;
    }

    @Override
    public String attack() {
        return name + " ataca con " + weapon + " (armadura: " + armor + ", habilidad: " + skill + ")";
    }
}
```
</details>

<details>
<summary><code>Character.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

public interface Character {
    String attack();
}
```
</details>

<details>
<summary><code>CharacterBuilder.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

/** Builder: construye el personaje paso a paso, evitando un constructor con muchos parametros. */
public class CharacterBuilder {
    private String name = "Unnamed";
    private String armor = "none";
    private String weapon = "fists";
    private String skill = "none";

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setArmor(String armor) {
        this.armor = armor;
        return this;
    }

    public CharacterBuilder setWeapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    public CharacterBuilder setSkill(String skill) {
        this.skill = skill;
        return this;
    }

    public BaseCharacter build() {
        return new BaseCharacter(name, armor, weapon, skill);
    }
}
```
</details>

<details>
<summary><code>CharacterDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

/** Decorator base: envuelve un Character y delega, agregando comportamiento. */
public abstract class CharacterDecorator implements Character {
    protected final Character wrapped;

    protected CharacterDecorator(Character wrapped) {
        this.wrapped = wrapped;
    }

    public Character getWrapped() {
        return wrapped;
    }
}
```
</details>

<details>
<summary><code>CharacterDirector.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

/** Director: arma arquetipos predefinidos usando el Builder. */
public class CharacterDirector {
    public BaseCharacter guerreroElite(CharacterBuilder builder) {
        return builder.setName("Guerrero Elite")
                .setArmor("steel")
                .setWeapon("sword")
                .setSkill("rage")
                .build();
    }

    public BaseCharacter magoDeFuego(CharacterBuilder builder) {
        return builder.setName("Mago de Fuego")
                .setArmor("robe")
                .setWeapon("staff")
                .setSkill("fireball")
                .build();
    }
}
```
</details>

<details>
<summary><code>Ejercicio04.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

/**
 * #04 - Plataforma de Videojuegos - Personajes.
 * Patrones combinados: Builder + Decorator.
 * Builder construye el personaje base configurable al inicio de la
 * partida; Decorator envuelve ese personaje en runtime para agregar
 * poderes temporales sin tocar la clase base ni generar una explosion
 * combinatoria de subclases.
 */
public class Ejercicio04 {
    public static void main(String[] args) {
        CharacterDirector director = new CharacterDirector();

        BaseCharacter warrior = director.guerreroElite(new CharacterBuilder());
        System.out.println("Base: " + warrior.attack());

        Character powered = new ShieldDecorator(new SpeedDecorator(warrior));
        System.out.println("Con poderes: " + powered.attack());

        Character mago = director.magoDeFuego(new CharacterBuilder());
        Character magoInvisible = new InvisibilityDecorator(mago);
        System.out.println("Mago con sigilo: " + magoInvisible.attack());
    }
}
```
</details>

<details>
<summary><code>InvisibilityDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

public class InvisibilityDecorator extends CharacterDecorator {
    public InvisibilityDecorator(Character wrapped) {
        super(wrapped);
    }

    @Override
    public String attack() {
        return wrapped.attack() + " + [ataque sorpresa invisible]";
    }
}
```
</details>

<details>
<summary><code>ShieldDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

public class ShieldDecorator extends CharacterDecorator {
    public ShieldDecorator(Character wrapped) {
        super(wrapped);
    }

    @Override
    public String attack() {
        return wrapped.attack() + " + [escudo de hielo activo]";
    }
}
```
</details>

<details>
<summary><code>SpeedDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio04;

public class SpeedDecorator extends CharacterDecorator {
    public SpeedDecorator(Character wrapped) {
        super(wrapped);
    }

    @Override
    public String attack() {
        return wrapped.attack() + " + [velocidad extra]";
    }
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio04.Ejercicio04`

```
java -cp out dosw.semana_3.patrones.ejercicio04.Ejercicio04
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #05 — Integración con Sistema Bancario Antiguo

**Patrones combinados:** Adapter + Facade

### 1. Rol de cada patrón
**Adapter** (`LegacyBankAdapter`) hace compatible `LegacyBankService` (métodos y unidades distintas) con la interfaz moderna `PaymentProcessor`, traduciendo `amount→cents` y `pay()→executeTransaction()`. **Facade** (`BankFacade`) expone un único método `procesarPago(monto)` que oculta los 8 pasos de inicialización y el uso interno del Adapter.

### 2. Cómo interactúan
El desarrollador llama `BankFacade.procesarPago(monto)` → la Facade inicializa conexión/sesión/contexto → delega al `LegacyBankAdapter` → el Adapter traduce la llamada al formato legacy → `LegacyBankService` ejecuta. El desarrollador nunca toca el servicio legacy directamente.

### 3. Esquema de código
<details>
<summary><code>BankFacade.java</code></summary>

```java
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
```
</details>

<details>
<summary><code>Ejercicio05.java</code></summary>

```java
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
```
</details>

<details>
<summary><code>LegacyBankAdapter.java</code></summary>

```java
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
```
</details>

<details>
<summary><code>LegacyBankService.java</code></summary>

```java
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
```
</details>

<details>
<summary><code>PaymentProcessor.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio05;

/** Interfaz moderna que espera usar el resto del sistema. */
public interface PaymentProcessor {
    void pay(double amount);
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio05.Ejercicio05`

```
java -cp out dosw.semana_3.patrones.ejercicio05.Ejercicio05
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #06 — Motor de Recomendaciones

**Patrones combinados:** Strategy + Observer

### 1. Rol de cada patrón
**Strategy** permite intercambiar el algoritmo de recomendación en tiempo de ejecución (`GenreStrategy`, `HistoryStrategy`, `PopularityStrategy`) sin reiniciar el sistema. **Observer** notifica automáticamente a los componentes (`HomePageComponent`, `SuggestedListComponent`, `NotificationService`) cuando el usuario cambia sus preferencias.

### 2. Cómo interactúan
El usuario cambia de algoritmo → `User` (Subject) notifica a sus Observers → cada Observer vuelve a pedir recomendaciones usando la nueva Strategy configurada → la UI se actualiza sin polling.

### 3. Esquema de código
<details>
<summary><code>Content.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

public record Content(String titulo) {}
```
</details>

<details>
<summary><code>Ejercicio06.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

/**
 * #06 - Motor de Recomendaciones.
 * Patrones combinados: Strategy + Observer.
 * Strategy permite intercambiar el algoritmo de recomendacion en
 * tiempo de ejecucion; Observer notifica automaticamente a la
 * pagina principal, notificaciones y sugeridos cuando el usuario
 * cambia sus preferencias, sin acoplar al usuario con esos componentes.
 */
public class Ejercicio06 {
    public static void main(String[] args) {
        User user = new User("Camila", new GenreStrategy());
        user.addObserver(new HomePageComponent());
        user.addObserver(new SuggestedListComponent());
        user.addObserver(new NotificationService());

        System.out.println("--- Usuario cambia a recomendacion por Historial ---");
        user.cambiarPreferencia(new HistoryStrategy());

        System.out.println("--- Usuario cambia a recomendacion por Popularidad ---");
        user.cambiarPreferencia(new PopularityStrategy());
    }
}
```
</details>

<details>
<summary><code>GenreStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

import java.util.List;

public class GenreStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        return List.of(new Content("Recomendado por genero para " + user.getNombre()));
    }
}
```
</details>

<details>
<summary><code>HistoryStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

import java.util.List;

public class HistoryStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        return List.of(new Content("Recomendado por historial para " + user.getNombre()));
    }
}
```
</details>

<details>
<summary><code>HomePageComponent.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

public class HomePageComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        var contenido = user.getAlgoritmo().recommend(user);
        System.out.println("[HomePage] Re-renderizando con: " + contenido);
    }
}
```
</details>

<details>
<summary><code>NotificationService.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

public class NotificationService implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println("[Notificaciones] Avisando a " + user.getNombre() + " que sus preferencias cambiaron.");
    }
}
```
</details>

<details>
<summary><code>PopularityStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

import java.util.List;

public class PopularityStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        return List.of(new Content("Recomendado por popularidad para " + user.getNombre()));
    }
}
```
</details>

<details>
<summary><code>PreferenceObserver.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

/** Observer: reacciona cuando el usuario cambia su algoritmo de recomendacion. */
public interface PreferenceObserver {
    void onPreferenceChanged(User user);
}
```
</details>

<details>
<summary><code>RecommendationAlgorithm.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

import java.util.List;

/** Strategy: cada algoritmo de recomendacion es intercambiable. */
public interface RecommendationAlgorithm {
    List<Content> recommend(User user);
}
```
</details>

<details>
<summary><code>SuggestedListComponent.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

public class SuggestedListComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        var contenido = user.getAlgoritmo().recommend(user);
        System.out.println("[Sugeridos] Actualizando lista con: " + contenido);
    }
}
```
</details>

<details>
<summary><code>User.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio06;

import java.util.ArrayList;
import java.util.List;

/** Subject: notifica a sus Observers cuando cambian las preferencias. */
public class User {
    private final String nombre;
    private RecommendationAlgorithm algoritmo;
    private final List<PreferenceObserver> observers = new ArrayList<>();

    public User(String nombre, RecommendationAlgorithm algoritmoInicial) {
        this.nombre = nombre;
        this.algoritmo = algoritmoInicial;
    }

    public String getNombre() { return nombre; }

    public RecommendationAlgorithm getAlgoritmo() { return algoritmo; }

    public void addObserver(PreferenceObserver observer) {
        observers.add(observer);
    }

    public void cambiarPreferencia(RecommendationAlgorithm nuevoAlgoritmo) {
        this.algoritmo = nuevoAlgoritmo;
        for (PreferenceObserver observer : observers) {
            observer.onPreferenceChanged(this);
        }
    }
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio06.Ejercicio06`

```
java -cp out dosw.semana_3.patrones.ejercicio06.Ejercicio06
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #07 — Flujo de Aprobación de Documentos

**Patrones combinados:** Chain of Responsibility + State

### 1. Rol de cada patrón
**Chain of Responsibility** encadena los validadores (`AutorHandler → LiderHandler → JuridicoHandler`); cada uno decide si procesa el documento o lo pasa al siguiente. **State** (`DraftState`, `InReviewState`, `ApprovedState`, `RejectedState`) maneja las transiciones de estado del documento, eliminando los switch/if de estado.

### 2. Cómo interactúan
Un handler de la cadena procesa el documento → invoca `document.approve()` → el State actual ejecuta la transición correspondiente → el siguiente handler continúa. El documento nunca sabe en qué estado está; su State sabe qué hacer.

### 3. Esquema de código
<details>
<summary><code>ApprovedState.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

public class ApprovedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] El documento ya esta aprobado.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] Un documento aprobado no puede rechazarse.");
    }

    @Override
    public String name() { return "APROBADO"; }
}
```
</details>

<details>
<summary><code>AutorHandler.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

public class AutorHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) { return true; }

    @Override
    protected void process(Document doc) {
        System.out.println("[AutorHandler] Revision del autor: OK.");
        doc.approve();
    }
}
```
</details>

<details>
<summary><code>Document.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

/** El documento delega el comportamiento de transicion a su estado actual. */
public class Document {
    private final String id;
    private DocumentState state = new DraftState();

    public Document(String id) {
        this.id = id;
    }

    public void setState(DocumentState state) {
        this.state = state;
        System.out.println("  -> Documento " + id + " ahora esta: " + state.name());
    }

    public void approve() { state.approve(this); }

    public void reject() { state.reject(this); }

    public String getEstado() { return state.name(); }
}
```
</details>

<details>
<summary><code>DocumentHandler.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

/** Chain of Responsibility: cada handler decide si procesa o pasa al siguiente. */
public abstract class DocumentHandler {
    private DocumentHandler next;

    public DocumentHandler setNext(DocumentHandler next) {
        this.next = next;
        return next;
    }

    public void handle(Document doc) {
        if (canHandle(doc)) {
            process(doc);
        }
        if (next != null) {
            next.handle(doc);
        } else {
            doc.approve();
        }
    }

    protected abstract boolean canHandle(Document doc);

    protected abstract void process(Document doc);
}
```
</details>

<details>
<summary><code>DocumentState.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

/** State: cada estado sabe a que estado puede transicionar. */
public interface DocumentState {
    void approve(Document doc);
    void reject(Document doc);
    String name();
}
```
</details>

<details>
<summary><code>DraftState.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

public class DraftState implements DocumentState {
    @Override
    public void approve(Document doc) {
        doc.setState(new InReviewState());
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] Un borrador no puede rechazarse, sigue en Draft.");
    }

    @Override
    public String name() { return "BORRADOR"; }
}
```
</details>

<details>
<summary><code>Ejercicio07.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

/**
 * #07 - Flujo de Aprobacion de Documentos.
 * Patrones combinados: Chain of Responsibility + State.
 * La cadena de handlers (Autor -> Lider -> Juridico) procesa el
 * documento en secuencia; cada handler invoca approve()/reject(),
 * y el objeto State actual del documento decide la transicion,
 * eliminando switch/if de estado dentro del documento.
 */
public class Ejercicio07 {
    public static void main(String[] args) {
        Document doc = new Document("DOC-01");
        System.out.println("Estado inicial: " + doc.getEstado());

        AutorHandler autor = new AutorHandler();
        LiderHandler lider = new LiderHandler();
        JuridicoHandler juridico = new JuridicoHandler();
        autor.setNext(lider).setNext(juridico);

        autor.handle(doc);
        System.out.println("Estado final: " + doc.getEstado());
    }
}
```
</details>

<details>
<summary><code>InReviewState.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

public class InReviewState implements DocumentState {
    @Override
    public void approve(Document doc) {
        doc.setState(new ApprovedState());
    }

    @Override
    public void reject(Document doc) {
        doc.setState(new RejectedState());
    }

    @Override
    public String name() { return "EN_REVISION"; }
}
```
</details>

<details>
<summary><code>JuridicoHandler.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

public class JuridicoHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) { return true; }

    @Override
    protected void process(Document doc) {
        System.out.println("[JuridicoHandler] Revision juridica: OK.");
    }
}
```
</details>

<details>
<summary><code>LiderHandler.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

public class LiderHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) { return true; }

    @Override
    protected void process(Document doc) {
        System.out.println("[LiderHandler] Revision del lider: OK.");
    }
}
```
</details>

<details>
<summary><code>RejectedState.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio07;

public class RejectedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] Un documento rechazado no puede aprobarse directamente.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] El documento ya esta rechazado.");
    }

    @Override
    public String name() { return "RECHAZADO"; }
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio07.Ejercicio07`

```
java -cp out dosw.semana_3.patrones.ejercicio07.Ejercicio07
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #08 — Sistema de Pedidos en Restaurante

**Patrones combinados:** Builder + Observer

### 1. Rol de cada patrón
**Builder** (`OrderBuilder`) construye el pedido personalizado paso a paso y produce un `Order` inmutable, evitando un constructor caótico con todos los ingredientes. **Observer** notifica a los subsistemas (`KitchenService`, `BillingService`, `DeliveryService`) cuando el pedido se confirma, sin que `Order` los conozca directamente.

### 2. Cómo interactúan
El cliente configura el pedido con el Builder → `build()` retorna un `Order` inmutable → se registran los Observers → `order.confirm()` notifica a los 3 subsistemas, cada uno reaccionando de forma independiente.

### 3. Esquema de código
<details>
<summary><code>BillingService.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio08;

public class BillingService implements OrderObserver {
    @Override
    public void onConfirmed(Order order) {
        System.out.println("[Facturacion] Generando cuenta para: " + order.describe());
    }
}
```
</details>

<details>
<summary><code>DeliveryService.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio08;

public class DeliveryService implements OrderObserver {
    @Override
    public void onConfirmed(Order order) {
        System.out.println("[Domicilio] Preparando ruta para: " + order.describe());
    }
}
```
</details>

<details>
<summary><code>Ejercicio08.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio08;

/**
 * #08 - Sistema de Pedidos en Restaurante.
 * Patrones combinados: Builder + Observer.
 * Builder garantiza que el pedido este completo y sea inmutable antes
 * de existir; Observer notifica a cocina, facturacion y domicilio
 * cuando el pedido se confirma, sin que Order los conozca directamente.
 */
public class Ejercicio08 {
    public static void main(String[] args) {
        Order order = new OrderBuilder()
                .setSize("grande")
                .setMeat("doble carne")
                .addTopping("queso", "lechuga")
                .addSide("papas", "gaseosa")
                .build();

        order.addObserver(new KitchenService());
        order.addObserver(new BillingService());
        order.addObserver(new DeliveryService());

        order.confirm();
    }
}
```
</details>

<details>
<summary><code>KitchenService.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio08;

public class KitchenService implements OrderObserver {
    @Override
    public void onConfirmed(Order order) {
        System.out.println("[Cocina] Preparando: " + order.describe());
    }
}
```
</details>

<details>
<summary><code>Order.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio08;

import java.util.ArrayList;
import java.util.List;

/** Order inmutable (construido por OrderBuilder) que actua como Subject al confirmarse. */
public class Order {
    private final String size;
    private final String meat;
    private final List<String> toppings;
    private final List<String> sides;
    private final List<OrderObserver> observers = new ArrayList<>();

    Order(String size, String meat, List<String> toppings, List<String> sides) {
        this.size = size;
        this.meat = meat;
        this.toppings = List.copyOf(toppings);
        this.sides = List.copyOf(sides);
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void confirm() {
        System.out.println("--- Pedido confirmado ---");
        for (OrderObserver observer : observers) {
            observer.onConfirmed(this);
        }
    }

    public String describe() {
        return "Hamburguesa " + size + " de " + meat + ", toppings=" + toppings + ", sides=" + sides;
    }
}
```
</details>

<details>
<summary><code>OrderBuilder.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio08;

import java.util.ArrayList;
import java.util.List;

/** Builder: arma el pedido personalizado paso a paso; el resultado es inmutable. */
public class OrderBuilder {
    private String size = "mediana";
    private String meat = "res";
    private final List<String> toppings = new ArrayList<>();
    private final List<String> sides = new ArrayList<>();

    public OrderBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public OrderBuilder setMeat(String meat) {
        this.meat = meat;
        return this;
    }

    public OrderBuilder addTopping(String... items) {
        toppings.addAll(List.of(items));
        return this;
    }

    public OrderBuilder addSide(String... items) {
        sides.addAll(List.of(items));
        return this;
    }

    public Order build() {
        return new Order(size, meat, toppings, sides);
    }
}
```
</details>

<details>
<summary><code>OrderObserver.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio08;

public interface OrderObserver {
    void onConfirmed(Order order);
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio08.Ejercicio08`

```
java -cp out dosw.semana_3.patrones.ejercicio08.Ejercicio08
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #09 — Sistema de Autenticación Empresarial

**Patrones combinados:** Strategy + Chain of Responsibility

### 1. Rol de cada patrón
**Strategy** selecciona el mecanismo de autenticación (`PasswordStrategy`, `GoogleStrategy`, `BiometricStrategy`) según el tipo de usuario. **Chain of Responsibility** procesa, tras la autenticación exitosa, las validaciones en secuencia (`CredentialValidator → PermissionValidator → LocationValidator → TimeValidator`), lanzando `AccessDeniedException` si algún eslabón falla.

### 2. Cómo interactúan
El usuario intenta acceder → `AuthService` usa la Strategy correcta para autenticar → si es exitosa, el resultado pasa por la cadena de validadores → si todos aprueban, se concede acceso. Strategy decide 'cómo autentico'; Chain decide 'si tengo acceso'.

### 3. Esquema de código
<details>
<summary><code>AccessDeniedException.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
```
</details>

<details>
<summary><code>AuthResult.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public record AuthResult(boolean success, String username) {}
```
</details>

<details>
<summary><code>AuthService.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class AuthService {
    public void login(AuthStrategy strategy, Validator chain, Credentials c) {
        AuthResult result = strategy.authenticate(c);
        if (!result.success()) {
            System.out.println("Autenticacion fallida para " + c.username());
            return;
        }
        try {
            chain.validate(c);
            System.out.println("Acceso concedido a " + c.username());
        } catch (AccessDeniedException e) {
            System.out.println("Acceso denegado a " + c.username() + ": " + e.getMessage());
        }
    }
}
```
</details>

<details>
<summary><code>AuthStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

/** Strategy: cada mecanismo de autenticacion decide "como" autenticar. */
public interface AuthStrategy {
    AuthResult authenticate(Credentials c);
}
```
</details>

<details>
<summary><code>BiometricStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class BiometricStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando huella biometrica de " + c.username());
        return new AuthResult(true, c.username());
    }
}
```
</details>

<details>
<summary><code>CredentialValidator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class CredentialValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        System.out.println("  [CredentialValidator] Credenciales OK.");
    }
}
```
</details>

<details>
<summary><code>Credentials.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public record Credentials(String username, String location, int hour) {}
```
</details>

<details>
<summary><code>Ejercicio09.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

/**
 * #09 - Sistema de Autenticacion Empresarial.
 * Patrones combinados: Strategy + Chain of Responsibility.
 * Strategy decide "como autentico" (password, Google, biometria);
 * una vez autenticado, la Chain of Responsibility valida en secuencia
 * credenciales, permisos, ubicacion y horario laboral, lanzando
 * AccessDeniedException si algun eslabon falla.
 */
public class Ejercicio09 {
    public static void main(String[] args) {
        AuthService service = new AuthService();

        CredentialValidator cred = new CredentialValidator();
        PermissionValidator perm = new PermissionValidator();
        LocationValidator loc = new LocationValidator();
        TimeValidator time = new TimeValidator();
        cred.setNext(perm).setNext(loc).setNext(time);

        System.out.println("--- Login con Password, dentro de horario y ubicacion validas ---");
        service.login(new PasswordStrategy(), cred, new Credentials("jvalderrama", "oficina", 10));

        System.out.println("--- Login con Google, fuera de ubicacion autorizada ---");
        service.login(new GoogleStrategy(), cred, new Credentials("jvalderrama", "casa", 10));

        System.out.println("--- Login biometrico, fuera de horario laboral ---");
        service.login(new BiometricStrategy(), cred, new Credentials("jvalderrama", "oficina", 23));
    }
}
```
</details>

<details>
<summary><code>GoogleStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class GoogleStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando token de Google para " + c.username());
        return new AuthResult(true, c.username());
    }
}
```
</details>

<details>
<summary><code>LocationValidator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class LocationValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        if (!"oficina".equals(c.location())) {
            throw new AccessDeniedException("Ubicacion no autorizada: " + c.location());
        }
        System.out.println("  [LocationValidator] Ubicacion OK (" + c.location() + ").");
    }
}
```
</details>

<details>
<summary><code>PasswordStrategy.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class PasswordStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando usuario/contrasena de " + c.username());
        return new AuthResult(true, c.username());
    }
}
```
</details>

<details>
<summary><code>PermissionValidator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class PermissionValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        System.out.println("  [PermissionValidator] Permisos OK.");
    }
}
```
</details>

<details>
<summary><code>TimeValidator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

public class TimeValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        if (c.hour() < 6 || c.hour() > 20) {
            throw new AccessDeniedException("Fuera de horario laboral: " + c.hour() + "h");
        }
        System.out.println("  [TimeValidator] Horario OK (" + c.hour() + "h).");
    }
}
```
</details>

<details>
<summary><code>Validator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio09;

/** Chain of Responsibility: cada validador decide si pasa al siguiente o niega el acceso. */
public abstract class Validator {
    private Validator next;

    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    public void validate(Credentials c) {
        check(c);
        if (next != null) {
            next.validate(c);
        }
    }

    protected abstract void check(Credentials c);
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio09.Ejercicio09`

```
java -cp out dosw.semana_3.patrones.ejercicio09.Ejercicio09
```

_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #10 — Aplicación de Edición de Imágenes

**Patrones combinados:** Decorator + Command

### 1. Rol de cada patrón
**Decorator** aplica filtros acumulativos (`GrayscaleDecorator`, `SepiaDecorator`, `BrightnessDecorator`) envolviendo la imagen en cualquier orden sin modificar la clase base ni los filtros existentes. **Command** (`ApplyFilterCommand`) encapsula cada aplicación de filtro como una operación con `execute()`/`undo()`, guardando el wrapper anterior en el historial del `ImageEditor` para permitir deshacer individualmente.

### 2. Cómo interactúan
El usuario aplica un filtro → se crea un `ApplyFilterCommand` que envuelve la imagen con un Decorator → el comando se agrega al historial → al hacer undo, el Command restaura el wrapper anterior guardado, sin afectar los demás filtros aplicados.

### 3. Esquema de código
<details>
<summary><code>ApplyFilterCommand.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

import java.util.function.Function;

/** Comando que aplica un filtro (Decorator) sobre la imagen actual del editor. */
public class ApplyFilterCommand implements ImageCommand {
    private final ImageEditor editor;
    private final Function<Image, Image> decoratorFactory;
    private Image previous;

    public ApplyFilterCommand(ImageEditor editor, Function<Image, Image> decoratorFactory) {
        this.editor = editor;
        this.decoratorFactory = decoratorFactory;
    }

    @Override
    public void execute() {
        previous = editor.getCurrent();
        Image decorated = decoratorFactory.apply(previous);
        editor.setCurrent(decorated);
        System.out.println("  [execute] " + decorated.render());
    }

    @Override
    public void undo() {
        editor.setCurrent(previous);
        System.out.println("  [undo] Vuelve a: " + previous.render());
    }
}
```
</details>

<details>
<summary><code>BaseImage.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

/** La imagen base nunca cambia; los filtros solo la envuelven. */
public class BaseImage implements Image {
    private final String fileName;

    public BaseImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String render() {
        return "imagen(" + fileName + ")";
    }
}
```
</details>

<details>
<summary><code>BrightnessDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

public class BrightnessDecorator extends ImageDecorator {
    public BrightnessDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + brillo";
    }
}
```
</details>

<details>
<summary><code>Ejercicio10.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

/**
 * #10 - Aplicacion de Edicion de Imagenes.
 * Patrones combinados: Decorator + Command.
 * Decorator aplica filtros acumulativos envolviendo la imagen sin
 * modificar la clase base; Command encapsula cada aplicacion de
 * filtro como una operacion reversible, guardando el wrapper anterior
 * para permitir undo individual (no solo el ultimo cambio global).
 */
public class Ejercicio10 {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor(new BaseImage("foto.png"));
        System.out.println("Inicial: " + editor.getCurrent().render());

        ImageCommand grayscale = new ApplyFilterCommand(editor, GrayscaleDecorator::new);
        ImageCommand sepia = new ApplyFilterCommand(editor, SepiaDecorator::new);
        ImageCommand brightness = new ApplyFilterCommand(editor, BrightnessDecorator::new);

        System.out.println("--- Aplicando filtros ---");
        editor.executeCommand(grayscale);
        editor.executeCommand(sepia);
        editor.executeCommand(brightness);

        System.out.println("Resultado final: " + editor.getCurrent().render());

        System.out.println("--- Deshaciendo el ultimo filtro (brillo) ---");
        editor.undoLast();
        System.out.println("Resultado tras undo: " + editor.getCurrent().render());
    }
}
```
</details>

<details>
<summary><code>GrayscaleDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

public class GrayscaleDecorator extends ImageDecorator {
    public GrayscaleDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + blancoYNegro";
    }
}
```
</details>

<details>
<summary><code>Image.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

public interface Image {
    String render();
}
```
</details>

<details>
<summary><code>ImageCommand.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

/** Command: encapsula cada operacion del usuario, reversible con undo(). */
public interface ImageCommand {
    void execute();
    void undo();
}
```
</details>

<details>
<summary><code>ImageDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

/** Decorator base: envuelve una Image y expone el wrapper anterior para poder deshacer. */
public abstract class ImageDecorator implements Image {
    protected final Image wrapped;

    protected ImageDecorator(Image wrapped) {
        this.wrapped = wrapped;
    }

    public Image getWrapped() {
        return wrapped;
    }
}
```
</details>

<details>
<summary><code>ImageEditor.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

import java.util.ArrayDeque;
import java.util.Deque;

/** Mantiene la imagen actual y el historial de comandos ejecutados para poder deshacer. */
public class ImageEditor {
    private Image current;
    private final Deque<ImageCommand> history = new ArrayDeque<>();

    public ImageEditor(Image initial) {
        this.current = initial;
    }

    public Image getCurrent() { return current; }

    public void setCurrent(Image current) { this.current = current; }

    public void executeCommand(ImageCommand command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }
}
```
</details>

<details>
<summary><code>SepiaDecorator.java</code></summary>

```java
package dosw.semana_3.patrones.ejercicio10;

public class SepiaDecorator extends ImageDecorator {
    public SepiaDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public String render() {
        return wrapped.render() + " + sepia";
    }
}
```
</details>


### 4. Demostración de ejecución
Clase ejecutable: `dosw.semana_3.patrones.ejercicio10.Ejercicio10`

```
java -cp out dosw.semana_3.patrones.ejercicio10.Ejercicio10
```

_(agregar aquí la captura de consola con la salida real)_

---


## Cómo compilar y ejecutar

```bash
javac -d out $(find src -name "*.java")
java -cp out dosw.semana_3.patrones.ejercicio01.Ejercicio01
# ... reemplazar ejercicio01/Ejercicio01 por el ejercicio deseado (01 a 10)
```

Los 10 ejercicios fueron compilados y ejecutados para verificar que el código es
funcional antes de la entrega.
