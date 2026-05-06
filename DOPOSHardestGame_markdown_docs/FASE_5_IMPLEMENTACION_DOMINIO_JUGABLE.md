# Fase 5 - Implementacion del dominio jugable

## Objetivo de la fase

Implementar las reglas principales del modo **Player** para que la capa de dominio permita simular una partida basica sin depender de la interfaz grafica.

---

## Funcionalidades implementadas

## Movimiento del jugador

El jugador rojo ahora puede moverse cuando el juego recibe una direccion.

Clases relacionadas:

- `Player`
- `RedPlayer`
- `Direction`
- `MovableEntity`
- `Board`

Comportamiento:

- El jugador recibe una direccion desde el juego.
- El nivel llama directamente a `player.move(direction, board)`.
- El movimiento se hace por pasos simples.
- El jugador no puede salir de los limites del tablero.

---

## Monedas

Las monedas amarillas ya pueden ser recolectadas.

Clases relacionadas:

- `Coin`
- `Level`
- `Player`

Comportamiento:

- Cada moneda sabe si fue recolectada.
- Una moneda se recolecta cuando colisiona con el jugador.
- El nivel cuenta monedas recolectadas y monedas totales.
- El nivel puede validar si todas las monedas fueron recolectadas.

---

## Enemigo azul basico

El enemigo azul basico ya tiene movimiento propio.

Clases relacionadas:

- `Enemy`
- `BasicBlueEnemy`
- `Direction`
- `Board`

Comportamiento:

- El enemigo se mueve en linea recta.
- Puede moverse horizontal o verticalmente.
- Su movimiento usa `deltaTime`, porque los enemigos si se actualizan automaticamente en cada ciclo del juego.
- Rebota al tocar los limites del tablero.
- Puede colisionar con el jugador.

---

## Zonas seguras

El nivel ya contiene zona segura inicial y zona segura final.

Clases relacionadas:

- `SafeZone`
- `SafeZoneType`
- `Level`

Comportamiento:

- La zona inicial define el punto de reaparicion del jugador.
- La zona final permite validar la condicion de victoria.
- El jugador gana solo si llega a la zona final despues de recolectar todas las monedas.

---

## Reglas de muerte

El dominio ya registra muertes por colision con enemigos.

Clases relacionadas:

- `Game`
- `Level`
- `Player`
- `Enemy`

Comportamiento:

- Si el jugador colisiona con un enemigo, se registra una muerte.
- El contador de muertes aumenta.
- El jugador reaparece en la zona segura inicial.
- El jugador vuelve a la posicion de la zona segura inicial.

---

## Reglas de victoria

El dominio ya puede detectar victoria.

Clases relacionadas:

- `Game`
- `Level`
- `Coin`
- `SafeZone`

Comportamiento:

El juego cambia a estado `WON` cuando:

1. Todas las monedas estan recolectadas.
2. El jugador esta en la zona segura final.

---

## Reglas de derrota por tiempo

El dominio ya puede detectar derrota por tiempo agotado.

Clases relacionadas:

- `Game`
- `GameTimer`
- `Level`

Comportamiento:

- El temporizador disminuye en cada actualizacion.
- Si el tiempo llega a cero antes de ganar, el juego cambia a estado `LOST`.

---

## Estados del juego

La clase `Game` controla los estados principales:

- `READY`
- `RUNNING`
- `PAUSED`
- `WON`
- `LOST`
- `FINISHED`

Comportamiento implementado:

- `start()` inicia la partida.
- `pause()` pausa la partida.
- `resume()` reanuda la partida.
- `finish()` termina la partida manualmente.
- `update(deltaTime)` actualiza el nivel solo si el juego esta en estado `RUNNING`.

---

## Nivel basico de Version 1

Se creo la clase:

```text
BasicLevelFactory
```

Esta clase construye la configuracion base de la Version 1:

- Tablero de `800 x 500`.
- Zona segura inicial.
- Zona segura final.
- Jugador rojo.
- Cuatro monedas amarillas.
- Tres enemigos azules basicos.
- Temporizador de 60 segundos.

Metodos disponibles:

- `createVersionOneGame()`
- `createVersionOneLevel()`

---

## Pruebas agregadas

Se ampliaron las pruebas JUnit del dominio en:

```text
src/test/java/dopo/hardestgame/domain/GameTest.java
```

Casos cubiertos:

- El juego inicia en estado `READY`.
- El jugador se mueve cuando el juego esta corriendo.
- El jugador recolecta monedas al colisionar con ellas.
- El juego registra muerte y reaparece al jugador.
- El juego gana al recolectar monedas y llegar a la zona final.
- El juego pierde cuando se agota el tiempo.
- El juego pausa y reanuda correctamente.

Nota: las pruebas quedan listas para ejecutarse con Maven o IntelliJ. En esta maquina Maven no esta disponible desde la terminal.

---

## Verificacion realizada

Se compilo la capa principal con `javac` usando Java 21.

Resultado:

```text
Compilacion exitosa.
```

Tambien se ejecuto una verificacion rapida con JShell usando `BasicLevelFactory`:

- Se creo una partida de Version 1.
- Se inicio el juego.
- Se envio movimiento hacia la derecha.
- Se movio el jugador hacia la derecha.
- Se confirmo que el jugador se movio y que el estado se mantuvo en `RUNNING`.

---

## Criterio de terminado de la Fase 5

La Fase 5 se considera completa porque:

- El dominio permite simular una partida basica.
- El jugador puede moverse.
- Las monedas pueden recolectarse.
- Los enemigos se mueven y rebotan.
- Las colisiones con enemigos causan muerte.
- El jugador reaparece tras morir.
- El temporizador funciona.
- La victoria puede detectarse.
- La derrota por tiempo puede detectarse.
- La pausa, reanudacion y terminacion manual existen en el dominio.
