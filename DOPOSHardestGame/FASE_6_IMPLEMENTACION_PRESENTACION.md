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

- `GameWindow`
- `StartPanel`
- `GamePanel`
- `BoardPanel`
- `StatusPanel`
- `ControlPanel`
- `KeyboardController`
- `GameLoop`
- `MessageDialog`

---

## Pantalla inicial

La pantalla inicial muestra:

- Nombre del juego.
- Version del proyecto.
- Modalidad disponible.
- Jugador disponible.
- Nivel disponible.
- Boton `Iniciar`.
- Boton `Salir`.

Al presionar `Iniciar`, se crea la pantalla de juego.

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

Los mensajes aparecen cuando el dominio cambia a un estado final.

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

