# Fase 4 - Implementacion base del proyecto Java

## Objetivo de la fase

Crear la estructura inicial del proyecto Java para **The DOPO Hardest Game**, separando la capa de dominio de la capa de presentacion y dejando configuradas las bases para pruebas unitarias con JUnit.

---

## Estructura creada

```text
src/
  main/
    java/
      dopo/
        hardestgame/
          Main.java
          domain/
          presentation/
  test/
    java/
      dopo/
        hardestgame/
          domain/
```

---

## Configuracion del proyecto

Se creo el archivo `pom.xml` para que el proyecto pueda ser reconocido como un proyecto Maven por IntelliJ IDEA u otros entornos compatibles.

La configuracion incluye:

- Java 21.
- Codificacion UTF-8.
- Dependencia de JUnit Jupiter.
- Plugin Maven Surefire para ejecutar pruebas.

Aunque Maven no esta disponible actualmente desde la terminal del sistema, el proyecto queda preparado para usarlo desde un IDE o desde una maquina que tenga Maven instalado.

---

## Capa de dominio creada

Paquete:

```text
dopo.hardestgame.domain
```

Clases y enumeraciones creadas:

- `Game`
- `Level`
- `Board`
- `Position`
- `Size`
- `Entity`
- `MovableEntity`
- `Player`
- `RedPlayer`
- `Enemy`
- `BasicBlueEnemy`
- `Coin`
- `SafeZone`
- `GameTimer`
- `Direction`
- `GameState`
- `SafeZoneType`
- `GameException`

La capa de dominio ya contiene comportamiento base para:

- Estado inicial del juego.
- Inicio, pausa, reanudacion y terminacion de partida.
- Movimiento de entidades.
- Movimiento del jugador.
- Movimiento basico de enemigos.
- Rebote de enemigo contra limites.
- Colisiones rectangulares.
- Recoleccion de monedas.
- Contador de muertes.
- Temporizador.
- Estados de victoria y derrota.

Este comportamiento todavia se seguira completando y probando en la Fase 5.

---

## Capa de presentacion creada

Paquete:

```text
dopo.hardestgame.presentation
```

Clase creada:

- `GameWindow`

Por ahora, `GameWindow` muestra una ventana inicial simple con el nombre del juego. La implementacion completa de los paneles de interfaz se desarrollara en la Fase 6.

---

## Punto de entrada

Se creo la clase:

```text
dopo.hardestgame.Main
```

Esta clase inicia la aplicacion grafica usando Swing.

---

## Pruebas

Se creo una prueba inicial:

```text
src/test/java/dopo/hardestgame/domain/GameTest.java
```

La prueba valida que una partida nueva inicia en estado `READY`.

Las pruebas completas del dominio se construiran en la Fase 7.

---

## Verificacion realizada

Se compilo la capa principal con `javac` usando Java 21.

Resultado:

```text
Compilacion exitosa.
```

Nota: no se ejecutaron pruebas JUnit desde terminal porque Maven no esta instalado en el sistema.

---

## Criterio de terminado de la Fase 4

La Fase 4 se considera completa porque:

- El proyecto Java tiene estructura base.
- Existe separacion entre dominio y presentacion.
- Existen clases iniciales del dominio.
- Existen enumeraciones necesarias.
- Existe una excepcion propia del juego.
- Existe un punto de entrada ejecutable.
- Existe configuracion para JUnit en `pom.xml`.
- La capa principal compila correctamente.

