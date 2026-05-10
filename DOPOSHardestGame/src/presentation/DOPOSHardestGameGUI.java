package presentation;

/**
 * clase principal que inicia la interfaz grafica del juego
 * 
 * @author Daniel Valero, Juan Nieto
 * @version 10.05.2026
 */
public class DOPOSHardestGameGUI {
    /**
     * metodo principal que crea la ventana inicial del juego
     * 
     * @param args argumentos de ejecucion del programa
     */
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.showStartScreen();
    }
}
