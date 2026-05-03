# Fase 2 - Diseno del dominio

## Objetivo de la fase

Modelar la capa de dominio de la Version 1 de **The DOPO Hardest Game** antes de implementar la interfaz grafica.

El dominio debe representar las reglas principales del juego sin depender de Swing, JavaFX, consola ni ningun elemento visual. La presentacion solo debe consultar el estado del dominio y enviar acciones del usuario.

---

## Principios de diseno

1. **Separacion entre dominio y presentacion**
   - El dominio no dibuja elementos.
   - El dominio no conoce botones, ventanas ni eventos graficos.
   - La presentacion traduce teclas y botones en acciones del juego.

2. **Modelo extensible**
   - Los jugadores se modelan con una clase base para permitir futuros jugadores azul y verde.
   - Los enemigos se modelan con una clase base para permitir futuros enemigos patrulleros, rapidos y deslizadores.
   - Las entidades tienen posicion y tamano para reutilizar la logica de colisiones.

3. **Reglas concentradas en el dominio**
   - La recoleccion de monedas se decide en el dominio.
   - La muerte por colision con enemigos se decide en el dominio.
   - La victoria se decide en el dominio.
   - El tiempo restante y los estados del juego se controlan en el dominio.

4. **Version 1 simple, pero preparada**
   - Solo se implementa el modo Player.
   - Solo se implementa el jugador rojo.
   - Solo se implementa la moneda amarilla normal.
   - Solo se implementa el enemigo azul basico.
   - Las clases quedan preparadas para extenderse en versiones futuras.

---

## Paquete sugerido

```text
dopo.hardestgame.domain
```

---

## Clases principales

## `Game`

Representa la partida actual.

### Responsabilidades

- Iniciar el juego.
- Pausar el juego.
- Reanudar el juego.
- Terminar el juego manualmente.
- Actualizar el estado del nivel en cada ciclo.
- Recibir movimientos del jugador.
- Controlar el estado general de la partida.
- Llevar el contador de muertes.

### Atributos sugeridos

- `Level currentLevel`
- `GameState state`
- `int deaths`

### Metodos sugeridos

- `start()`
- `pause()`
- `resume()`
- `finish()`
- `update(double deltaTime)`
- `movePlayer(Direction direction)`
- `registerDeath()`
- `boolean isWon()`
- `boolean isLost()`

---

## `Level`

Representa el nivel que se esta jugando.

### Responsabilidades

- Mantener el tablero.
- Mantener el jugador.
- Mantener monedas.
- Mantener enemigos.
- Mantener zonas seguras.
- Actualizar enemigos.
- Detectar colisiones.
- Verificar si se recolectaron todas las monedas.
- Verificar si el jugador llego a la zona final.
- Controlar el tiempo limite del nivel.

### Atributos sugeridos

- `Board board`
- `Player player`
- `List<Coin> coins`
- `List<Enemy> enemies`
- `SafeZone initialSafeZone`
- `SafeZone finalSafeZone`
- `GameTimer timer`

### Metodos sugeridos

- `update(double deltaTime)`
- `movePlayer(Direction direction)`
- `collectCoins()`
- `boolean hasPlayerCollidedWithEnemy()`
- `boolean areAllCoinsCollected()`
- `boolean isPlayerInFinalZone()`
- `void respawnPlayer()`
- `int getCollectedCoinsCount()`
- `int getTotalCoinsCount()`

---

## `Board`

Representa los limites del escenario.

### Responsabilidades

- Definir ancho y alto del tablero.
- Validar si una entidad esta dentro de los limites.
- Evitar que el jugador salga del tablero.
- Permitir que los enemigos reboten al tocar limites.

### Atributos sugeridos

- `int width`
- `int height`

### Metodos sugeridos

- `boolean contains(Entity entity)`
- `boolean contains(Position position, Size size)`
- `Position clamp(Position position, Size size)`
- `boolean touchesHorizontalLimit(Entity entity)`
- `boolean touchesVerticalLimit(Entity entity)`

---

## `Entity`

Clase base abstracta para los objetos que tienen posicion, tamano y pueden colisionar.

### Responsabilidades

- Mantener posicion.
- Mantener tamano.
- Calcular colisiones con otras entidades.

### Atributos sugeridos

- `Position position`
- `Size size`

### Metodos sugeridos

- `Position getPosition()`
- `Size getSize()`
- `void setPosition(Position position)`
- `boolean collidesWith(Entity other)`

---

## `MovableEntity`

Clase base abstracta para entidades que se pueden mover.

### Responsabilidades

- Mantener velocidad.
- Mover una entidad en una direccion.

### Atributos sugeridos

- `double speed`

### Metodos sugeridos

- `void move(Direction direction, Board board)`
- `double getSpeed()`

---

## `Player`

Clase base abstracta para los jugadores.

### Responsabilidades

- Representar el jugador controlable.
- Mantener su punto de aparicion.
- Moverse segun la entrada del usuario.
- Reaparecer despues de morir.

### Atributos sugeridos

- `Position spawnPosition`
- `double baseSpeed`

### Metodos sugeridos

- `void move(Direction direction, Board board)`
- `void respawn()`
- `Position getSpawnPosition()`
- `void setSpawnPosition(Position spawnPosition)`

---

## `RedPlayer`

Representa el cuadrado rojo de la Version 1.

### Responsabilidades

- Definir el jugador estandar.
- Usar velocidad normal.
- Usar tamano normal.
- No tener habilidades especiales.

### Atributos sugeridos

- Hereda los atributos de `Player`.

### Metodos sugeridos

- Constructor con posicion inicial.

---

## `Enemy`

Clase base abstracta para enemigos.

### Responsabilidades

- Representar un obstaculo movil peligroso.
- Actualizar su movimiento.
- Permitir colision con el jugador.

### Atributos sugeridos

- `Direction direction`

### Metodos sugeridos

- `void update(Board board, double deltaTime)`
- `Direction getDirection()`
- `void setDirection(Direction direction)`

---

## `BasicBlueEnemy`

Representa el punto azul basico de la Version 1.

### Responsabilidades

- Moverse en linea recta horizontal o vertical.
- Rebotar al tocar limites del tablero.
- Mantener velocidad constante.

### Atributos sugeridos

- Hereda posicion, tamano, velocidad y direccion.

### Metodos sugeridos

- `void update(Board board, double deltaTime)`
- `void bounceIfNeeded(Board board)`

---

## `Coin`

Representa una moneda amarilla recolectable.

### Responsabilidades

- Mantener su posicion.
- Saber si ya fue recolectada.
- Marcarse como recolectada al colisionar con el jugador.

### Atributos sugeridos

- `boolean collected`

### Metodos sugeridos

- `boolean isCollected()`
- `void collect()`
- `boolean canBeCollectedBy(Player player)`

---

## `SafeZone`

Representa una zona segura del tablero.

### Responsabilidades

- Representar la zona inicial.
- Representar la zona final.
- Detectar si el jugador esta dentro de la zona.

### Atributos sugeridos

- `SafeZoneType type`

### Metodos sugeridos

- `SafeZoneType getType()`
- `boolean contains(Player player)`

---

## `GameTimer`

Controla el tiempo limite del nivel.

### Responsabilidades

- Mantener el tiempo restante.
- Disminuir el tiempo cuando el juego esta corriendo.
- Indicar si el tiempo se agoto.

### Atributos sugeridos

- `double remainingSeconds`

### Metodos sugeridos

- `void tick(double deltaTime)`
- `boolean isFinished()`
- `double getRemainingSeconds()`
- `void reset(double seconds)`

---

## `Position`

Representa una coordenada dentro del tablero.

### Responsabilidades

- Mantener coordenada `x`.
- Mantener coordenada `y`.
- Crear nuevas posiciones al desplazar una entidad.

### Atributos sugeridos

- `double x`
- `double y`

### Metodos sugeridos

- `double getX()`
- `double getY()`
- `Position translatedBy(double dx, double dy)`

---

## `Size`

Representa el ancho y alto de una entidad.

### Responsabilidades

- Mantener ancho.
- Mantener alto.

### Atributos sugeridos

- `double width`
- `double height`

### Metodos sugeridos

- `double getWidth()`
- `double getHeight()`

---

## Enumeraciones

## `Direction`

Representa las direcciones de movimiento.

Valores sugeridos:

- `UP`
- `DOWN`
- `LEFT`
- `RIGHT`
- `UP_LEFT`
- `UP_RIGHT`
- `DOWN_LEFT`
- `DOWN_RIGHT`
- `NONE`

## `GameState`

Representa el estado de la partida.

Valores sugeridos:

- `READY`
- `RUNNING`
- `PAUSED`
- `WON`
- `LOST`
- `FINISHED`

## `SafeZoneType`

Representa el tipo de zona segura.

Valores sugeridos:

- `INITIAL`
- `FINAL`

---

## Excepcion del dominio

## `GameException`

Excepcion propia para errores del juego.

### Casos posibles

- Intentar iniciar un juego sin nivel.
- Intentar mover el jugador cuando el juego no esta corriendo.
- Crear un nivel sin zona inicial.
- Crear un nivel sin zona final.
- Crear entidades con tamanos invalidos.
- Crear posiciones fuera del tablero.

---

## Relaciones principales

- `Game` tiene un `Level`.
- `Game` controla el `GameState`.
- `Game` registra muertes.
- `Level` tiene un `Board`.
- `Level` tiene un `Player`.
- `Level` tiene muchas `Coin`.
- `Level` tiene muchos `Enemy`.
- `Level` tiene una `SafeZone` inicial.
- `Level` tiene una `SafeZone` final.
- `Level` tiene un `GameTimer`.
- `RedPlayer` hereda de `Player`.
- `Player` hereda de `MovableEntity`.
- `BasicBlueEnemy` hereda de `Enemy`.
- `Enemy` hereda de `MovableEntity`.
- `MovableEntity` hereda de `Entity`.
- `Coin` hereda de `Entity`.
- `SafeZone` hereda de `Entity`.

---

## Flujo principal del dominio

1. La presentacion crea o solicita un `Game` con un `Level` basico.
2. El usuario inicia la partida.
3. `Game` cambia su estado a `RUNNING`.
4. En cada ciclo, `Game` llama a `Level.update(deltaTime)`.
5. `Level` actualiza el temporizador.
6. `Level` actualiza enemigos.
7. `Level` revisa colisiones entre jugador y monedas.
8. `Level` revisa colisiones entre jugador y enemigos.
9. Si hay colision con enemigo, `Game` registra muerte y el nivel reaparece el jugador.
10. Si todas las monedas estan recolectadas y el jugador llega a la zona final, `Game` cambia a `WON`.
11. Si el tiempo llega a cero antes de ganar, `Game` cambia a `LOST`.

---

## Decisiones para facilitar extension futura

### Nuevos jugadores

Los jugadores futuros, como azul y verde, pueden heredar de `Player`.

- `BluePlayer`: mayor velocidad y mayor tamano.
- `GreenPlayer`: puede absorber un golpe y luego reducir velocidad.

### Nuevos enemigos

Los enemigos futuros pueden heredar de `Enemy`.

- `PatrolEnemy`: sigue una ruta geometrica.
- `FastEnemy`: se mueve al doble de velocidad.
- `VerticalSliderEnemy`: se mueve solo verticalmente.

### Nuevas monedas

Para versiones futuras se puede crear una clase base o interfaz para objetos recolectables.

Ejemplo futuro:

- `Collectible`
- `YellowCoin`
- `SkinCoin`

En Version 1 basta con `Coin`, pero el diseno no impide crear monedas especializadas luego.

### Elementos especiales

Para versiones futuras se puede crear una clase `SpecialElement` que herede de `Entity`.

Ejemplos futuros:

- `LifeSource`
- `Bomb`
- `Wall`

---

## Criterio de terminado de la Fase 2

La Fase 2 se considera completa cuando:

- Las clases principales del dominio estan identificadas.
- Las responsabilidades de cada clase estan definidas.
- Las relaciones entre clases estan claras.
- Existe un diagrama de clases del dominio.
- El diseno permite explicar la Version 1 sin depender de la interfaz grafica.
- El diseno permite extender el juego en versiones futuras sin rehacer la base.

