# SEMANA No 3 — Patrones de Diseño Combinados (Taller 4)

## Datos personales:
- Nombre y Apellido: Juan Diego Valderrama Gaviria
- Curso: DOSW

Cada uno de los 10 ejercicios combina exactamente 2 patrones de diseño, cubriendo
**todas** las clases concretas mencionadas en el enunciado de cada caso (ej. los 5
medios de pago, los 5 métodos de autenticación, los 5 filtros de imagen, etc.). El
código completo, compilable y funcional de cada ejercicio vive en los archivos
`.java` de la carpeta indicada en cada sección.

**Paquete base:** `src/main/dosw/semana_3/patrones/ejercicioNN/`
(un paquete independiente por ejercicio para evitar colisiones de nombres de clases).

---

## Ejercicio #01 — Plataforma de Pagos Inteligentes

**Patrones combinados:** Strategy + Factory Method

### 1. Rol de cada patrón
**Strategy** encapsula cada algoritmo de pago (Tarjeta, PSE, Nequi, PayPal) en una clase independiente e intercambiable; el `Checkout` trabaja solo contra la interfaz `PaymentStrategy`. **Factory Method** construye el proveedor de pago correcto según el país del usuario (`ColombiaPaymentFactory`, `UsaPaymentFactory`), sin que el cliente conozca las clases concretas.

### 2. Cómo interactúan
El usuario indica su país → la Factory construye la Strategy correspondiente → el `Checkout` invoca `strategy.process(amount)` sin saber cuál implementación recibió. La Factory decide *qué* Strategy instanciar; el Checkout nunca cambia.

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, el Checkout tendría un if/else gigante por cada medio de pago y otro por cada país, mezclando la lógica de "cómo pagar" con "qué proveedor construir". Cada vez que se agrega un país o un medio, hay que tocar el Checkout. Con Strategy + Factory Method, el Checkout no cambia nunca: agregar Stripe fue una clase nueva y una línea en la Factory, sin tocar el flujo de compra.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio01/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, el Pedido tendría que conocer y llamar directamente a EmailService, SmsService, PushService, y cada uno tendría su propia lógica de formateo mezclada con el envío. Agregar un canal nuevo obligaría a modificar el Pedido. Con Observer + Factory Method, el Pedido solo notifica un evento genérico; agregar WhatsApp es una clase nueva sin tocar Pedido ni los demás canales.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio02/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, cada tipo de reporte reimplementaría los 4 pasos completos (obtener datos, procesar, formatear, exportar), duplicando la lógica de obtención/procesamiento en PDF, Excel y CSV. Con Template Method el algoritmo se escribe una sola vez en la clase base; con Factory Method el cliente ni siquiera necesita saber qué clase concreta existe para pedir un reporte nuevo.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio03/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, cada combinación de poderes (escudo+velocidad, velocidad+invisibilidad, los 3 juntos...) necesitaría su propia subclase: con 5 poderes son 32 combinaciones posibles. Con Decorator son 5 clases envoltorio reutilizables en cualquier combinación y orden. Builder, aparte, evita un constructor de personaje con 10 parámetros dificiles de recordar y ordenar.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio04/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, cada desarrollador tendría que conocer los 8 pasos de inicialización del banco legado y traducir manualmente amount a centavos en cada lugar donde se use, duplicando esa lógica y arriesgando errores de conversión. Facade concentra la complejidad de inicialización en un solo punto; Adapter concentra la traducción de formatos en un solo punto, así que un cambio en el banco legado solo se corrige en una clase.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio05/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, el motor de recomendaciones tendría un switch gigante por algoritmo, y la actualización de UI dependería de que cada pantalla haga polling constante para detectar cambios de preferencia. Strategy permite cambiar el algoritmo sin reiniciar ni tocar el motor; Observer elimina el polling porque cada componente se entera del cambio al instante.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio06/`

### 4. Demostración de ejecución
```
java -cp out dosw.semana_3.patrones.ejercicio06.Ejercicio06
```
_(agregar aquí la captura de consola con la salida real)_

---
## Ejercicio #07 — Flujo de Aprobación de Documentos

**Patrones combinados:** Chain of Responsibility + State

### 1. Rol de cada patrón
**Chain of Responsibility** encadena los validadores (`AutorHandler → LiderHandler → JuridicoHandler → FinancieroHandler`); cada uno decide si procesa el documento o lo pasa al siguiente — `FinancieroHandler` solo actúa si el documento requiere presupuesto, demostrando que no todos los documentos pasan por todas las etapas. **State** (`DraftState`, `InReviewState`, `ApprovedState`, `RejectedState`) maneja las transiciones de estado del documento, eliminando los switch/if de estado.

### 2. Cómo interactúan
Un handler de la cadena procesa el documento (o lo salta, según `canHandle()`) → invoca `document.approve()` → el State actual ejecuta la transición correspondiente → el siguiente handler continúa. El documento nunca sabe en qué estado está; su State sabe qué hacer.

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, el documento tendría un atributo `estado` de tipo String y cada método (`aprobar`, `rechazar`) necesitaría un switch para decidir qué transición es válida según el estado actual, repitiendo esa lógica en varios lugares. Con State esa lógica vive en un solo lugar por estado; con Chain of Responsibility, agregar o quitar una etapa de revisión (como Financiero) no obliga a modificar las demás etapas ni el documento.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio07/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, el pedido se armaría con un constructor de 10+ parámetros (tamaño, carne, 5 toppings, 3 sides...) fácil de usar mal, y la confirmación llamaría directamente a cocina, facturación y domicilio dentro del propio método `confirm()`, acoplando el pedido a esos 3 servicios. Builder da un pedido inmutable y legible paso a paso; Observer permite agregar un cuarto servicio (ej. encuestas de satisfacción) sin tocar la clase Order.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio08/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, `AuthService` tendría un switch para elegir el mecanismo de login y, después, una cadena de `if` anidados para validar credenciales, permisos, ubicación y horario, mezclando ambas responsabilidades en un solo método gigante y dificil de testear. Strategy aísla "cómo autentico"; Chain of Responsibility aísla "si tengo acceso", y cada validador se puede probar o reordenar de forma independiente.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio09/`

### 4. Demostración de ejecución
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

### 3. Justificación — por qué esta combinación es superior a una solución sin patrones
Sin patrones, cada combinación de filtros (b/n+sepia, sepia+contraste, los 5 juntos...) sería una clase nueva, y deshacer solo el penúltimo filtro sin afectar los demás sería casi imposible de programar con un simple stack de imágenes. Decorator permite apilar filtros en cualquier orden sin explosión de clases; Command guarda el estado anterior de cada operación, permitiendo un undo verdaderamente individual.

> Código completo, compilable y funcional en:
> `src/main/dosw/semana_3/patrones/ejercicio10/`

### 4. Demostración de ejecución
```
java -cp out dosw.semana_3.patrones.ejercicio10.Ejercicio10
```
_(agregar aquí la captura de consola con la salida real)_

---


## Cómo compilar y ejecutar

```bash
javac -d out $(find src -name "*.java")
java -cp out dosw.semana_3.patrones.ejercicio01.Ejercicio01
# reemplazar ejercicio01/Ejercicio01 por el ejercicio deseado (01 a 10)
```

Los 10 ejercicios fueron compilados y ejecutados para verificar que el código es
funcional antes de la entrega.
