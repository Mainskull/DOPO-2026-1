# ROADMAP - Version 1: Presentacion + Dominio

## Objetivo de la Version 1

Construir la primera version funcional de **The DOPO Hardest Game**, enfocada en el modo basico de un solo jugador. Esta entrega debe demostrar una separacion clara entre la capa de presentacion y la capa de dominio, con un nivel basico jugable que incluya jugador, monedas, enemigos simples, zonas seguras y configuracion inicial del juego.

## Alcance de la Version 1

La Version 1 incluye:

- Modo de juego: **Player**.
- Jugador: **cuadrado rojo**, con velocidad y tamano normales.
- Movimiento del jugador en ocho direcciones: norte, sur, este, oeste y diagonales.
- Nivel basico.
- Monedas amarillas recolectables.
- Punto azul basico como enemigo.
- Zonas seguras inicial y final.
- Contador de muertes.
- Contador de monedas recolectadas.
- Tiempo limite del nivel.
- Pausa del juego.
- Terminacion manual del juego.
- Mensaje de victoria.
- Maqueta completa de interfaz.
- Diseno de dominio mediante diagrama de clases.
- Codigo inicial de presentacion y dominio.
- Pruebas unitarias basicas del dominio.

Fuera del alcance de esta version:

- Modo Player vs Player.
- Modo Player vs Machine.
- Maquinas aleatoria y experta.
- Cuadrados azul y verde como seleccionables.
- Monedas de skins.
- Zonas seguras intermedias.
- Enemigos patrulleros o acelerados.
- Fuentes de vida.
- Bombas.
- Guardar y abrir partidas.
- Multiples configuraciones cargadas desde archivos.

---

## Fase 1 - Comprension y delimitacion del problema

### Objetivo

Definir con claridad que debe hacer la primera version y que se dejara preparado para versiones futuras.

### Pasos

1. Leer la guia del proyecto y extraer los requisitos de la Version 1.
2. Separar requisitos actuales de requisitos futuros.
3. Definir las reglas minimas del nivel basico.
4. Definir los elementos obligatorios del dominio:
   - Juego.
   - Nivel.
   - Jugador.
   - Enemigo basico.
   - Moneda.
   - Zona segura.
   - Tablero o escenario.
   - Temporizador.
5. Definir los elementos obligatorios de la presentacion:
   - Pantalla inicial.
   - Pantalla de juego.
   - Panel de estado.
   - Botones de pausa y terminar.
   - Mensajes de victoria o fin de juego.

### Entregables

- Lista de requisitos de la Version 1.
- Lista de requisitos aplazados para versiones posteriores.
- Descripcion breve del nivel basico.

### Criterio de terminado

La Version 1 queda delimitada sin mezclar funcionalidades de ciclos futuros.

---

## Fase 2 - Diseno del dominio

### Objetivo

Modelar las clases principales del juego antes de implementar la interfaz grafica.

### Pasos

1. Identificar clases del dominio.
2. Definir responsabilidades de cada clase.
3. Definir atributos principales.
4. Definir metodos principales.
5. Definir relaciones entre clases:
   - Asociacion entre juego y nivel.
   - Composicion del nivel con monedas, enemigos y zonas.
   - Asociacion entre jugador y posicion.
   - Relacion entre entidades movibles y colisiones.
6. Crear el diagrama de clases del dominio.
7. Revisar que el diseno permita extension futura:
   - Nuevos tipos de jugadores.
   - Nuevos tipos de enemigos.
   - Nuevos tipos de monedas.
   - Nuevos elementos especiales.

### Clases sugeridas

- `Game`
- `Level`
- `Board`
- `Position`
- `Size`
- `Player`
- `RedPlayer`
- `Enemy`
- `BasicBlueEnemy`
- `Coin`
- `SafeZone`
- `GameTimer`
- `GameState`
- `Direction`
- `GameException`

### Entregables

- Diagrama de clases del dominio.
- Descripcion corta de responsabilidades por clase.

### Criterio de terminado

El dominio puede explicar las reglas del juego sin depender de la interfaz grafica.

---

## Fase 3 - Diseno de la presentacion

### Objetivo

Crear una maqueta completa de la interfaz de usuario para la Version 1.

### Pasos

1. Definir la estructura visual general.
2. Disenar la pantalla inicial:
   - Nombre del juego.
   - Seleccion de modo Player.
   - Seleccion de nivel basico.
   - Boton de iniciar.
3. Disenar la pantalla de juego:
   - Area del tablero.
   - Jugador rojo.
   - Monedas amarillas.
   - Enemigos azules.
   - Zona segura inicial.
   - Zona segura final.
4. Disenar el panel de estado:
   - Tiempo restante.
   - Muertes.
   - Monedas recolectadas.
5. Disenar controles:
   - Pausar.
   - Reanudar.
   - Terminar.
6. Disenar mensajes:
   - Victoria.
   - Tiempo agotado.
   - Juego terminado por el usuario.

### Entregables

- Boceto o maqueta de la interfaz.
- Diagrama de clases de presentacion, si es requerido en la revision.

### Criterio de terminado

La interfaz propuesta permite entender como se jugara la Version 1 antes de programarla completamente.

---

## Fase 4 - Implementacion base del proyecto Java

### Objetivo

Crear la estructura inicial del proyecto y preparar paquetes, clases base y pruebas.

### Pasos

1. Crear la estructura de paquetes.
2. Separar la capa de dominio de la capa de presentacion.
3. Crear las clases principales vacias o con comportamiento minimo.
4. Crear enumeraciones necesarias:
   - `Direction`.
   - `GameState`.
5. Crear la excepcion personalizada del juego.
6. Configurar JUnit para pruebas unitarias.

### Estructura sugerida

```text
src/
  main/
    java/
      dopo/hardestgame/
        domain/
        presentation/
  test/
    java/
      dopo/hardestgame/
        domain/
```

### Entregables

- Proyecto Java organizado.
- Paquetes de dominio y presentacion.
- Clase principal para ejecutar el programa.
- Configuracion inicial de pruebas.

### Criterio de terminado

El proyecto compila y tiene una estructura clara para continuar la implementacion.

---

## Fase 5 - Implementacion del dominio jugable

### Objetivo

Implementar las reglas principales del modo Player para que el juego pueda funcionar sin depender todavia de una interfaz avanzada.

### Pasos

1. Implementar posiciones y tamanos.
2. Implementar jugador rojo:
   - Posicion.
   - Velocidad normal.
   - Movimiento en ocho direcciones.
   - Reinicio al punto de aparicion.
3. Implementar monedas:
   - Posicion.
   - Estado recolectada/no recolectada.
   - Recoleccion por colision con el jugador.
4. Implementar enemigo azul basico:
   - Movimiento horizontal o vertical.
   - Rebote contra limites del tablero.
   - Colision con el jugador.
5. Implementar zonas seguras:
   - Zona inicial.
   - Zona final.
6. Implementar nivel basico:
   - Dimensiones del tablero.
   - Posiciones iniciales.
   - Lista de monedas.
   - Lista de enemigos.
   - Tiempo limite.
7. Implementar reglas de victoria:
   - Todas las monedas recolectadas.
   - Jugador llega a zona final.
8. Implementar reglas de muerte:
   - Colision con enemigo.
   - Incremento del contador de muertes.
   - Reaparicion en zona inicial.
9. Implementar avance del juego por ciclos o ticks.
10. Implementar pausa y reanudacion desde el estado del juego.

### Entregables

- Dominio funcional del nivel basico.
- Reglas principales implementadas.
- Estado del juego consultable desde la presentacion.

### Criterio de terminado

El dominio permite simular una partida basica: moverse, recoger monedas, morir, reiniciar y ganar.

---

## Fase 6 - Implementacion de la presentacion

### Objetivo

Conectar la interfaz grafica con el dominio para permitir jugar la Version 1.

### Pasos

1. Crear ventana principal.
2. Crear pantalla inicial.
3. Crear panel del tablero.
4. Dibujar elementos del juego:
   - Jugador rojo.
   - Monedas amarillas.
   - Enemigos azules.
   - Zonas seguras verdes.
   - Paredes o limites visuales si aplican.
5. Capturar teclado para mover al jugador.
6. Ejecutar el ciclo de actualizacion del juego.
7. Actualizar panel de estado:
   - Tiempo.
   - Muertes.
   - Monedas.
8. Implementar boton de pausa.
9. Implementar boton de terminar.
10. Mostrar mensaje de victoria.
11. Mostrar mensaje cuando se agote el tiempo.

### Entregables

- Interfaz grafica funcional.
- Nivel basico visible y jugable.
- Estado del juego actualizado en pantalla.

### Criterio de terminado

Una persona puede ejecutar el programa y jugar el nivel basico desde inicio hasta victoria o derrota.

---

## Fase 7 - Pruebas unitarias del dominio

### Objetivo

Verificar que las reglas principales del juego funcionen correctamente.

### Pasos

1. Probar movimiento del jugador.
2. Probar que el jugador no salga de los limites del tablero.
3. Probar recoleccion de monedas.
4. Probar que el nivel no se completa si faltan monedas.
5. Probar que el nivel se completa al recolectar todas las monedas y llegar a la zona final.
6. Probar colision con enemigo.
7. Probar incremento de muertes.
8. Probar reaparicion del jugador.
9. Probar movimiento y rebote del enemigo basico.
10. Probar pausa y reanudacion del estado del juego.

### Entregables

- Pruebas JUnit del dominio.
- Evidencia de ejecucion de pruebas.

### Criterio de terminado

Las reglas principales de la Version 1 estan cubiertas por pruebas automatizadas.

---

## Fase 8 - Documentacion y diagramas finales

### Objetivo

Preparar los documentos necesarios para sustentar la entrega.

### Pasos

1. Actualizar el diagrama de clases del dominio segun el codigo real.
2. Crear o actualizar el diagrama de clases de presentacion.
3. Crear diagramas de secuencia para casos principales:
   - Iniciar partida.
   - Mover jugador.
   - Recolectar moneda.
   - Colisionar con enemigo.
   - Completar nivel.
4. Documentar decisiones de diseno.
5. Documentar requisitos no incluidos en esta version.
6. Revisar consistencia entre PDF, diagramas y codigo.

### Entregables

- Diagrama de clases del dominio.
- Diagrama de clases de presentacion.
- Diagramas de secuencia.
- Descripcion de decisiones de diseno.
- Lista de requisitos aplazados.

### Criterio de terminado

La documentacion coincide con el codigo implementado y explica claramente el alcance de la Version 1.

---

## Fase 9 - Revision final de la entrega

### Objetivo

Validar que la Version 1 cumple lo solicitado antes de presentarla.

### Pasos

1. Ejecutar el juego desde cero.
2. Verificar que el jugador rojo se mueve correctamente.
3. Verificar que las monedas se recolectan.
4. Verificar que los enemigos se mueven y causan muerte.
5. Verificar que el contador de muertes cambia.
6. Verificar que el contador de monedas cambia.
7. Verificar que el tiempo disminuye.
8. Verificar que la pausa funciona.
9. Verificar que terminar juego funciona.
10. Verificar que aparece victoria al cumplir las reglas.
11. Ejecutar pruebas JUnit.
12. Revisar que no haya errores visibles en consola.
13. Revisar ortografia y nombres de clases.
14. Confirmar que los diagramas estan actualizados.

### Entregables

- Version 1 ejecutable.
- Pruebas ejecutadas.
- Diagramas actualizados.
- Roadmap y alcance documentados.

### Criterio de terminado

La entrega puede ser presentada y defendida explicando claramente que se hizo, como funciona y que queda para versiones posteriores.

---

## Checklist general de Version 1

- [x] Requisitos de Version 1 identificados.
- [x] Requisitos futuros separados.
- [x] Nivel basico definido.
- [x] Diagrama de clases del dominio creado.
- [x] Maqueta de interfaz creada.
- [x] Proyecto Java configurado.
- [ ] Capa de dominio implementada.
- [ ] Capa de presentacion implementada.
- [ ] Movimiento del jugador implementado.
- [ ] Monedas implementadas.
- [ ] Enemigo azul basico implementado.
- [ ] Zonas seguras implementadas.
- [ ] Tiempo limite implementado.
- [ ] Contador de muertes implementado.
- [ ] Contador de monedas implementado.
- [ ] Pausa implementada.
- [ ] Terminar juego implementado.
- [ ] Victoria implementada.
- [ ] Pruebas JUnit creadas.
- [ ] Diagramas de secuencia creados.
- [ ] Revision final realizada.
