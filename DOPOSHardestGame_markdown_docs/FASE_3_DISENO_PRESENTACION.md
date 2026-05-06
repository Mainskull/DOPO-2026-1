# Fase 3 - Diseno de la presentacion

## Objetivo de la fase

Definir la interfaz grafica de la Version 1 de **The DOPO Hardest Game** antes de implementar el codigo.

La presentacion debe permitir iniciar una partida, jugar el nivel basico, consultar el estado del juego, pausar, reanudar, terminar y visualizar los mensajes principales de victoria o derrota.

---

## Alcance de la presentacion en Version 1

La interfaz de la Version 1 incluye:

- Pantalla inicial.
- Pantalla de juego.
- Panel del tablero.
- Panel de estado.
- Controles de pausa, reanudacion y terminacion.
- Mensaje de victoria.
- Mensaje de tiempo agotado.
- Mensaje de juego terminado por el usuario.

No incluye todavia:

- Pantalla de seleccion de varios modos.
- Seleccion real de varios personajes.
- Seleccion de color de borde.
- Selector de varias configuraciones.
- Pantalla para abrir partidas guardadas.
- Pantalla para guardar partidas.

Estos elementos quedan aplazados para versiones posteriores.

---

## Tecnologia sugerida

Para la Version 1 se recomienda usar **Java Swing**, porque permite construir una interfaz grafica completa con Java estandar y es suficiente para representar el tablero, los controles y el ciclo del juego.

La presentacion debe estar en un paquete separado:

```text
dopo.hardestgame.presentation
```

---

## Principios de diseno de presentacion

1. **La presentacion no contiene reglas del juego**
   - No decide si el jugador gana.
   - No decide si una moneda fue recolectada.
   - No decide si un enemigo mata al jugador.
   - Solo consulta el dominio y dibuja el estado actual.

2. **La presentacion envia acciones al dominio**
   - Teclas de movimiento.
   - Boton iniciar.
   - Boton pausar.
   - Boton reanudar.
   - Boton terminar.

3. **El tablero se dibuja a partir del estado del dominio**
   - Posicion del jugador.
   - Posicion de monedas no recolectadas.
   - Posicion de enemigos.
   - Zonas seguras.

4. **La interfaz debe ser clara**
   - El jugador rojo debe distinguirse facilmente.
   - Los enemigos azules deben distinguirse facilmente.
   - Las monedas amarillas deben distinguirse facilmente.
   - Las zonas seguras verdes deben distinguirse facilmente.
   - El tiempo, muertes y monedas deben estar siempre visibles.

---

## Pantallas de la Version 1

## Pantalla inicial

### Proposito

Permitir iniciar el nivel basico de la Version 1.

### Elementos

- Titulo del juego.
- Texto del modo disponible: `Modo Player`.
- Texto del jugador disponible: `Cuadrado rojo`.
- Texto del nivel disponible: `Nivel basico`.
- Boton `Iniciar`.
- Boton `Salir`.

### Comportamiento

- Al presionar `Iniciar`, se crea la partida y se muestra la pantalla de juego.
- Al presionar `Salir`, se cierra la aplicacion.

---

## Pantalla de juego

### Proposito

Permitir jugar el nivel basico.

### Elementos

- Panel del tablero.
- Panel de estado.
- Boton `Pausar`.
- Boton `Reanudar`.
- Boton `Terminar`.

### Comportamiento

- El panel del tablero se actualiza en cada ciclo del juego.
- El teclado mueve al jugador.
- El panel de estado muestra tiempo, muertes y monedas.
- Al pausar, el ciclo de actualizacion se detiene.
- Al reanudar, el ciclo de actualizacion continua.
- Al terminar, la partida cambia a estado finalizado y se muestra un mensaje.

---

## Dialogos o mensajes

## Mensaje de victoria

Se muestra cuando el dominio informa que el estado del juego es `WON`.

Texto sugerido:

```text
Ganaste el nivel.
Monedas recolectadas: X/Y
Muertes: N
```

Opciones:

- Volver al inicio.
- Salir.

## Mensaje de tiempo agotado

Se muestra cuando el dominio informa que el estado del juego es `LOST`.

Texto sugerido:

```text
Tiempo agotado.
Intentalo nuevamente.
```

Opciones:

- Reintentar.
- Volver al inicio.

## Mensaje de juego terminado

Se muestra cuando el usuario presiona `Terminar`.

Texto sugerido:

```text
La partida fue terminada.
```

Opciones:

- Volver al inicio.
- Salir.

---

## Maqueta de interfaz

## Pantalla inicial

```text
+------------------------------------------------------+
|                THE DOPO HARDEST GAME                 |
+------------------------------------------------------+
|                                                      |
|  Version 1                                           |
|                                                      |
|  Modalidad:  Player                                  |
|  Jugador:    Cuadrado rojo                           |
|  Nivel:      Nivel basico                            |
|                                                      |
|                  [ Iniciar ]                         |
|                  [ Salir   ]                         |
|                                                      |
+------------------------------------------------------+
```

## Pantalla de juego

```text
+---------------------------------------------------------------+
| THE DOPO HARDEST GAME                                         |
+---------------------------------------------------------------+
| Tiempo: 60 s      Muertes: 0      Monedas: 0 / 4              |
+---------------------------------------------------------------+
|                                                               |
|  +---------------------------------------------------------+  |
|  | ZONA INICIAL                                            |  |
|  | [verde]                                                 |  |
|  |                                                         |  |
|  |        o moneda        O enemigo                        |  |
|  |                                                         |  |
|  |                 jugador rojo                            |  |
|  |                                                         |  |
|  |        o moneda        O enemigo        o moneda        |  |
|  |                                                         |  |
|  |                                      ZONA FINAL         |  |
|  |                                      [verde]            |  |
|  +---------------------------------------------------------+  |
|                                                               |
+---------------------------------------------------------------+
|              [ Pausar ]   [ Reanudar ]   [ Terminar ]         |
+---------------------------------------------------------------+
```

---

## Representacion visual de elementos

| Elemento | Representacion | Color sugerido |
| --- | --- | --- |
| Jugador rojo | Cuadrado | Rojo |
| Enemigo basico | Circulo | Azul |
| Moneda | Circulo pequeno | Amarillo |
| Zona segura inicial | Rectangulo | Verde |
| Zona segura final | Rectangulo | Verde |
| Tablero | Area rectangular | Fondo claro |

---

## Controles de teclado

| Tecla | Accion |
| --- | --- |
| Flecha arriba | Mover arriba |
| Flecha abajo | Mover abajo |
| Flecha izquierda | Mover izquierda |
| Flecha derecha | Mover derecha |
| Arriba + izquierda | Mover diagonal arriba izquierda |
| Arriba + derecha | Mover diagonal arriba derecha |
| Abajo + izquierda | Mover diagonal abajo izquierda |
| Abajo + derecha | Mover diagonal abajo derecha |
| P | Pausar o reanudar |
| Esc | Terminar o volver |

---

## Clases sugeridas de presentacion

## `GameWindow`

Ventana principal de la aplicacion.

### Responsabilidades

- Crear la ventana.
- Cambiar entre pantalla inicial y pantalla de juego.
- Centralizar la navegacion general.

### Atributos sugeridos

- `StartPanel startPanel`
- `GamePanel gamePanel`

### Metodos sugeridos

- `showStartScreen()`
- `showGameScreen()`
- `closeApplication()`

---

## `StartPanel`

Pantalla inicial del juego.

### Responsabilidades

- Mostrar informacion inicial de la Version 1.
- Permitir iniciar la partida.
- Permitir salir de la aplicacion.

### Atributos sugeridos

- `JButton startButton`
- `JButton exitButton`

### Metodos sugeridos

- `configureComponents()`
- `configureEvents()`

---

## `GamePanel`

Pantalla principal de juego.

### Responsabilidades

- Agrupar tablero, estado y controles.
- Conectar botones con acciones del dominio.
- Coordinar actualizacion grafica.

### Atributos sugeridos

- `Game game`
- `BoardPanel boardPanel`
- `StatusPanel statusPanel`
- `ControlPanel controlPanel`
- `GameLoop gameLoop`

### Metodos sugeridos

- `startGame()`
- `pauseGame()`
- `resumeGame()`
- `finishGame()`
- `refresh()`

---

## `BoardPanel`

Panel encargado de dibujar el tablero.

### Responsabilidades

- Dibujar zonas seguras.
- Dibujar monedas no recolectadas.
- Dibujar enemigos.
- Dibujar jugador.
- Mantener el foco para capturar teclado o apoyar al controlador de teclado.

### Atributos sugeridos

- `Game game`

### Metodos sugeridos

- `paintComponent(Graphics graphics)`
- `drawPlayer(Graphics graphics)`
- `drawCoins(Graphics graphics)`
- `drawEnemies(Graphics graphics)`
- `drawSafeZones(Graphics graphics)`

---

## `StatusPanel`

Panel que muestra el estado actual de la partida.

### Responsabilidades

- Mostrar tiempo restante.
- Mostrar muertes.
- Mostrar monedas recolectadas.
- Actualizar textos desde el dominio.

### Atributos sugeridos

- `JLabel timeLabel`
- `JLabel deathsLabel`
- `JLabel coinsLabel`

### Metodos sugeridos

- `updateStatus(Game game)`

---

## `ControlPanel`

Panel de botones de control.

### Responsabilidades

- Mostrar botones de pausa, reanudacion y terminacion.
- Conectar acciones del usuario con el `GamePanel`.

### Atributos sugeridos

- `JButton pauseButton`
- `JButton resumeButton`
- `JButton finishButton`

### Metodos sugeridos

- `configureEvents(GamePanel gamePanel)`

---

## `KeyboardController`

Clase encargada de traducir teclas en direcciones del dominio.

### Responsabilidades

- Detectar teclas presionadas.
- Detectar combinaciones para diagonales.
- Llamar a `game.movePlayer(direction)`.

### Atributos sugeridos

- `Game game`

### Metodos sugeridos

- `keyPressed(KeyEvent event)`
- `keyReleased(KeyEvent event)`
- `getCurrentDirection()`

---

## `GameLoop`

Clase encargada del ciclo visual y de actualizacion.

### Responsabilidades

- Ejecutar actualizaciones periodicas.
- Llamar a `game.update(deltaTime)`.
- Pedir redibujado del tablero.
- Pedir actualizacion del panel de estado.
- Detectar estados finales para mostrar mensajes.

### Atributos sugeridos

- `Timer timer`
- `Game game`
- `GamePanel gamePanel`

### Metodos sugeridos

- `start()`
- `stop()`
- `restart()`
- `tick()`

---

## `MessageDialog`

Clase de apoyo para mostrar mensajes finales.

### Responsabilidades

- Mostrar victoria.
- Mostrar derrota por tiempo.
- Mostrar partida terminada.

### Metodos sugeridos

- `showVictory(Game game)`
- `showTimeOver(Game game)`
- `showGameFinished()`

---

## Flujo de interaccion

1. El usuario abre la aplicacion.
2. `GameWindow` muestra `StartPanel`.
3. El usuario presiona `Iniciar`.
4. `GameWindow` crea y muestra `GamePanel`.
5. `GamePanel` crea el juego basico de Version 1.
6. `GameLoop` inicia las actualizaciones.
7. `KeyboardController` envia movimientos al dominio.
8. `BoardPanel` dibuja el estado del dominio.
9. `StatusPanel` muestra tiempo, muertes y monedas.
10. Si el dominio cambia a `WON`, `MessageDialog` muestra victoria.
11. Si el dominio cambia a `LOST`, `MessageDialog` muestra tiempo agotado.
12. Si el usuario presiona `Terminar`, `MessageDialog` muestra partida terminada.

---

## Criterio de terminado de la Fase 3

La Fase 3 se considera completa cuando:

- La pantalla inicial esta definida.
- La pantalla de juego esta definida.
- La maqueta de interfaz esta documentada.
- Los elementos visuales tienen una representacion clara.
- Los controles de teclado estan definidos.
- Las clases de presentacion estan identificadas.
- La presentacion queda separada del dominio.

