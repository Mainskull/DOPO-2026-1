# Fase 6 - Implementacion de la presentacion

## Objetivo de la fase

Conectar la interfaz grafica con la capa de dominio para permitir jugar la Version 1 de **The DOPO Hardest Game**.

---

## Decision sobre imagenes y fotos

Para esta version se decidio usar las fotos o capturas del juego original solamente como **referencia visual**.

El juego no carga imagenes externas en el tablero. En cambio, los elementos se dibujan con Java Swing usando `Graphics`.

Esta decision mantiene el codigo mas sencillo y facil de explicar:

- El jugador se dibuja como cuadrado rojo.
- Los enemigos se dibujan como circulos azules.
- Las monedas se dibujan como circulos amarillos.
- Las zonas seguras se dibujan como rectangulos verdes.
- El tablero tiene fondo claro y borde negro.

Las referencias visuales se pueden guardar en:

```text
docs/referencias-visuales/
```

---

## Clases implementadas o conectadas

Paquete:

```text
dopo.hardestgame.presentation
```

Clases principales:

- `DOPOSHardestGameGUI`
- `GameWindow`
- `StartPanel`
- `ModeSelectionPanel`
- `PlayerSelectionPanel`
- `GamePanel`
- `BoardPanel`
- `StatusPanel`
- `ControlPanel`
- `KeyboardController`
- `GameLoop`
- `MessageDialog`

---

## Pantalla inicial

La pantalla inicial funciona como menu principal y muestra tres opciones:

- `Jugar`
- `Opciones`
- `Creditos`

Solo `Jugar` esta activo en la Version 1.

Si el usuario presiona `Opciones` o `Creditos`, se muestra un mensaje indicando que esa funcionalidad esta en construccion.

---

## Pantalla de seleccion de modo

Despues de presionar `Jugar`, aparece una pantalla para escoger modo de juego.

Opciones visibles:

- `Player individual`
- `Player vs Player`
- `Player vs Machine`

Solo `Player individual` esta activo en la Version 1.

Las otras opciones muestran un mensaje de funcionalidad en construccion.

---

## Pantalla de seleccion de jugador

Despues de escoger `Player individual`, aparece una pantalla para escoger el tipo de jugador.

Opciones visibles:

- `Cuadrado rojo`
- `Cuadrado azul`
- `Cuadrado verde`

Solo `Cuadrado rojo` esta activo en la Version 1.

Las otras opciones muestran un mensaje de funcionalidad en construccion.

---

## Pantalla de juego

La pantalla de juego contiene:

- Panel de estado superior.
- Tablero central.
- Controles inferiores.

El juego se crea usando:

```text
BasicLevelFactory.createVersionOneGame()
```

Esto conecta la presentacion con el nivel basico definido en el dominio.

---

## Tablero grafico

El tablero se dibuja en:

```text
BoardPanel
```

El panel consulta el dominio y dibuja:

- Zona segura inicial.
- Zona segura final.
- Monedas no recolectadas.
- Enemigos.
- Jugador.

El dibujo usa una escala calculada a partir del tamano real del tablero del dominio, por lo que si la ventana cambia de tamano, los elementos siguen ubicados proporcionalmente.

---

## Panel de estado

El panel de estado muestra:

- Tiempo restante.
- Muertes.
- Monedas recolectadas y total de monedas.

La informacion viene directamente desde la clase `Game`.

---

## Controles

Los controles implementados son:

- `Pausar`
- `Reanudar`
- `Terminar`

Estos botones llaman metodos de `GamePanel`, y `GamePanel` llama al dominio.

---

## Movimiento por teclado

La clase `KeyboardController` traduce las flechas del teclado a direcciones del dominio.

Controles disponibles:

- Arriba.
- Abajo.
- Izquierda.
- Derecha.
- Diagonales combinando dos flechas.

---

## Mensajes

La clase `MessageDialog` muestra:

- Mensaje de victoria.
- Mensaje de tiempo agotado.
- Mensaje de partida terminada.
- Mensaje de funcionalidad en construccion.

Los mensajes aparecen cuando el dominio cambia a un estado final.

Para las opciones que todavia no estan implementadas se usa `FeatureInConstructionException`, que se captura para mostrar un mensaje amigable sin cerrar el programa.

---

## Clase que activa el juego

La clase que activa la interfaz grafica ahora es:

```text
dopo.hardestgame.presentation.DOPOSHardestGameGUI
```

Esta clase esta dentro de la capa de presentacion y tiene un `main` simple:

```java
public static void main(String[] args) {
    GameWindow window = new GameWindow();
    window.showStartScreen();
}
```

Esta forma se usa para que el inicio del programa sea mas parecido a la forma basica vista en clase.

---

## Verificacion realizada

Se compilo la capa principal con `javac` usando Java 21.

Resultado:

```text
Compilacion exitosa.
```

No se ejecuto la ventana automaticamente desde la terminal para no dejar un proceso grafico abierto. El programa se puede ejecutar desde IntelliJ usando la clase `Main`.

---

## Criterio de terminado de la Fase 6

La Fase 6 se considera completa porque:

- La interfaz grafica esta conectada con el dominio.
- La pantalla inicial existe.
- La pantalla de juego existe.
- El tablero dibuja los elementos principales.
- El panel de estado muestra tiempo, muertes y monedas.
- Los controles de pausa, reanudacion y terminacion existen.
- El teclado permite mover al jugador.
- Los mensajes finales estan implementados.
- El menu principal, seleccion de modo y seleccion de jugador estan implementados.
- Las opciones futuras muestran mensaje de construccion.
