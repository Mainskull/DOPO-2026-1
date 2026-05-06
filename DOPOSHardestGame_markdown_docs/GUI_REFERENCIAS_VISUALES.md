# Guia de referencias visuales para la interfaz

## Idea principal

Las imagenes del PDF o capturas del juego original se pueden usar como **referencia visual**, pero no es necesario ponerlas dentro del tablero.

Para la Version 1 conviene dibujar los elementos con Java Swing usando `Graphics`, porque:

- Es mas facil de programar.
- Es mas facil de explicar en la sustentacion.
- No depende de archivos externos.
- Se parece al estilo del juego original.
- Permite cambiar posiciones y tamanos desde el dominio.

---

## Como usar fotos o capturas

Las fotos se recomiendan solo para guiar el diseno.

Puedes guardar capturas en una carpeta como:

```text
docs/referencias-visuales/
```

Ejemplos de capturas utiles:

- Pantalla completa del primer nivel.
- Ejemplo de jugador rojo.
- Ejemplo de enemigo azul.
- Ejemplo de moneda amarilla.
- Ejemplo de zonas verdes.
- Ejemplo del panel de muertes, tiempo y monedas.

---

## Que elementos se dibujan en Java

En el codigo actual, el tablero se dibuja con formas simples:

- Fondo gris claro.
- Borde negro.
- Zonas seguras verdes.
- Jugador como cuadrado rojo.
- Enemigos como circulos azules.
- Monedas como circulos amarillos.
- Barra superior negra con tiempo, muertes y monedas.

Esto se implementa en:

```text
src/main/java/dopo/hardestgame/presentation/BoardPanel.java
```

---

## Por que no usar imagenes como sprites todavia

Para esta primera version, usar imagenes para cada elemento puede traer problemas extra:

- Hay que cargar archivos.
- Hay que cuidar rutas.
- Hay que escalar imagenes.
- Puede fallar si falta una imagen.
- Complica la explicacion del codigo.

Mas adelante, si el juego ya funciona bien, se pueden agregar sprites en una fase visual sin cambiar el dominio.

---

## Recomendacion para la entrega

Para Version 1:

1. Usar fotos y capturas como referencia.
2. Dibujar el juego con `Graphics`.
3. Mantener colores parecidos al juego original.
4. Priorizar que el nivel sea claro y jugable.

