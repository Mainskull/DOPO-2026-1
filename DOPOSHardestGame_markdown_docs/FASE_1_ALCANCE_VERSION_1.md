# Fase 1 - Comprension y delimitacion del problema

## Proyecto

**The DOPO Hardest Game** es una version academica del juego *The World's Hardest Game*, desarrollada para la materia Desarrollo Orientado por Objetos.

El juego completo tendra varias modalidades, tipos de jugadores, enemigos, objetivos, elementos especiales y persistencia. Sin embargo, la **Version 1** se enfocara en construir una primera base jugable, con una separacion clara entre la capa de dominio y la capa de presentacion.

---

## Objetivo de la Version 1

Construir una version inicial del juego que permita jugar un nivel basico en modalidad **Player**, usando un jugador rojo, monedas amarillas, enemigos azules basicos, zonas seguras y un tiempo limite.

Esta version debe servir como base para extender el proyecto en las siguientes entregas.

---

## Requisitos incluidos en la Version 1

### Modalidad de juego

- El juego tendra unicamente la modalidad **Player**.
- Habra un solo jugador controlado por teclado.
- El objetivo sera completar el nivel antes de que termine el tiempo.

### Jugador

- El jugador sera el **cuadrado rojo**.
- Tendra velocidad normal.
- Tendra tamano normal.
- No tendra habilidades especiales.
- Podra moverse en ocho direcciones:
  - Arriba.
  - Abajo.
  - Izquierda.
  - Derecha.
  - Diagonal arriba izquierda.
  - Diagonal arriba derecha.
  - Diagonal abajo izquierda.
  - Diagonal abajo derecha.

### Nivel basico

- El juego tendra un nivel basico inicial.
- El nivel tendra una zona segura inicial.
- El nivel tendra una zona segura final.
- El nivel tendra monedas amarillas.
- El nivel tendra enemigos azules basicos.
- El nivel tendra un tiempo limite.

### Monedas

- Las monedas seran amarillas.
- El jugador debera recolectar todas las monedas para poder completar el nivel.
- El estado del juego mostrara cuantas monedas se han recolectado.

### Enemigos

- La Version 1 incluira el **punto azul basico**.
- El enemigo se movera en linea recta horizontal o vertical.
- El enemigo rebotara al chocar con los limites permitidos del tablero.
- Si el enemigo toca al jugador, el jugador muere y reaparece en la zona segura inicial.

### Zonas seguras

- La zona segura inicial sera el punto de aparicion del jugador.
- La zona segura final sera el destino del jugador.
- El jugador solo gana si llega a la zona final despues de recolectar todas las monedas.

### Reglas de muerte

- Si el jugador toca un enemigo, muere.
- Al morir, se incrementa el contador de muertes.
- Al morir, el jugador reaparece en la zona segura inicial.
- No hay limite de vidas en esta version.
- El juego termina si se agota el tiempo.

### Reglas de victoria

El jugador gana cuando se cumplen estas dos condiciones:

1. Todas las monedas del nivel han sido recolectadas.
2. El jugador llega a la zona segura final antes de que se agote el tiempo.

### Estado del juego

La interfaz debera mostrar:

- Tiempo restante.
- Cantidad de muertes.
- Monedas recolectadas.
- Estado general de la partida.

### Controles de juego

La Version 1 debera permitir:

- Iniciar una partida.
- Mover el jugador.
- Pausar la partida.
- Reanudar la partida.
- Terminar la partida manualmente.
- Mostrar mensaje de victoria.
- Mostrar mensaje cuando el tiempo se agote.

---

## Requisitos aplazados para versiones posteriores

Los siguientes requisitos aparecen en la guia del proyecto, pero no hacen parte del alcance principal de la Version 1:

- Modo **Player vs Player**.
- Modo **Player vs Machine**.
- Maquina aleatoria.
- Maquina experta.
- Seleccion de diferentes skins de jugador.
- Cuadrado azul.
- Cuadrado verde.
- Monedas de skins.
- Zonas seguras intermedias.
- Punto azul patrullero.
- Enemigo acelerado.
- Deslizador vertical como tipo especializado.
- Paredes solidas complejas.
- Fuentes de vida.
- Bombas.
- Guardar partidas.
- Abrir partidas guardadas.
- Multiples configuraciones cargadas desde archivos `.txt`.
- Configuracion original completa del nivel uno.

Estos requisitos se tendran en cuenta al disenar el dominio, para que el codigo pueda crecer sin rehacerse desde cero.

---

## Descripcion del nivel basico de la Version 1

El nivel basico sera un escenario rectangular en el que el jugador inicia dentro de una zona segura verde. En el tablero habra varias monedas amarillas que el jugador debe recolectar. Tambien habra uno o mas enemigos azules basicos moviendose en linea recta.

El jugador debera planear su movimiento para evitar los enemigos, recoger todas las monedas y llegar a la zona segura final. Si un enemigo toca al jugador, el jugador vuelve a aparecer en la zona inicial y aumenta el contador de muertes. El nivel se pierde si el tiempo llega a cero antes de completar el objetivo.

### Elementos minimos del nivel

- Un tablero rectangular.
- Una zona segura inicial.
- Una zona segura final.
- Un jugador rojo.
- Varias monedas amarillas.
- Al menos un enemigo azul basico.
- Un tiempo limite.

---

## Elementos obligatorios del dominio

Para esta version, el dominio debe representar como minimo:

- Juego.
- Nivel.
- Tablero o escenario.
- Jugador.
- Posicion.
- Tamano o area de colision.
- Direccion de movimiento.
- Moneda.
- Enemigo.
- Enemigo azul basico.
- Zona segura.
- Temporizador.
- Estado del juego.
- Excepcion propia del juego.

---

## Elementos obligatorios de la presentacion

Para esta version, la presentacion debe representar como minimo:

- Ventana principal.
- Pantalla inicial.
- Panel del tablero.
- Panel de estado.
- Boton de iniciar.
- Boton de pausar.
- Boton de reanudar.
- Boton de terminar.
- Mensaje de victoria.
- Mensaje de derrota por tiempo agotado.

---

## Criterio de terminado de la Fase 1

La Fase 1 se considera completa cuando:

- El alcance de la Version 1 esta definido.
- Los requisitos actuales estan separados de los requisitos futuros.
- El nivel basico esta descrito.
- Se conocen los elementos minimos de dominio y presentacion.
- El equipo puede pasar al diseno del dominio sin dudas sobre que debe implementar primero.

