import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un escenario en el juego.
 */
public class Escenario {
    private String nombre;
    private int filas;
    private int columnas;
    private String[][] matriz;
    private Jugador jugador;

    /**
     * Constructor para crear un nuevo escenario.
     *
     * @param nombre          El nombre del escenario.
     * @param codigoEscenario La lista de códigos que define el escenario.
     * @param jugador         El jugador que se colocará en el escenario.
     */
    public Escenario(String nombre, List<String> codigoEscenario, Jugador jugador) {
        this.nombre = nombre;
        this.filas = 10; // Fijo a 10 filas
        this.columnas = 40; // Fijo a 40 columnas
        this.matriz = new String[filas + 2][columnas + 2]; // +2 para las barras adicionales
        this.jugador = jugador;
        generarEscenario(codigoEscenario);
    }

    /**
     * Genera el escenario a partir del código proporcionado.
     *
     * @param codigoEscenario La lista de códigos que define el escenario.
     */
    private void generarEscenario(List<String> codigoEscenario) {
        // Rellenar la primera y última fila con barras
        for (int i = 0; i < columnas + 2; i++) {
            matriz[0][i] = "|";
            matriz[filas + 1][i] = "|";
        }

        // Rellenar las columnas iniciales y finales con barras
        for (int i = 0; i < filas + 2; i++) {
            matriz[i][0] = "|";
            matriz[i][columnas + 1] = "|";
        }

        // Generar el contenido del escenario
        for (int i = 0; i < filas; i++) {
            String lineaCodigo = codigoEscenario.get(i);
            int posicion = 1; // Comenzar desde la posición 1 para dejar espacio para la barra inicial

            int indice = 0;
            while (indice < lineaCodigo.length()) {
                // Leer el número
                int cantidad = 0;
                while (indice < lineaCodigo.length() && lineaCodigo.charAt(indice) >= '0' && lineaCodigo.charAt(indice) <= '9') {
                    cantidad = cantidad * 10 + (lineaCodigo.charAt(indice) - '0');
                    indice++;
                }

                // Leer el tipo (E o O)
                char tipo = lineaCodigo.charAt(indice);
                indice++;

                // Rellenar la matriz con el tipo correspondiente
                for (int k = 0; k < cantidad && posicion < columnas + 1; k++) {
                    matriz[i + 1][posicion++] = (tipo == 'E') ? "-" : "O";
                }
            }

            // Rellenar el resto de la fila con espacios si no se alcanzó el límite
            while (posicion < columnas + 1) {
                matriz[i + 1][posicion++] = "-";
            }
        }

        // Colocar al jugador en el mapa
        matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "@";
    }

    /**
     * Obtiene la representación del escenario como una lista de cadenas.
     *
     * @return La lista de cadenas que representan el escenario.
     */
    public List<String> obtenerEscenario() {
        List<String> escenario = new ArrayList<>();
        for (String[] fila : matriz) {
            escenario.add(String.join("", fila));
        }
        return escenario;
    }

    /**
     * Mueve al jugador en el escenario según la dirección especificada.
     *
     * @param direccion La dirección en la que mover al jugador ('W', 'A', 'S', 'D').
     */
    public void moverJugador(char direccion) {
        int nuevaPosicionX = jugador.getPosicionX();
        int nuevaPosicionY = jugador.getPosicionY();

        switch (direccion) {
            case 'W':
            case 'w':
                nuevaPosicionY--;
                break;
            case 'S':
            case 's':
                nuevaPosicionY++;
                break;
            case 'A':
            case 'a':
                nuevaPosicionX--;
                break;
            case 'D':
            case 'd':
                nuevaPosicionX++;
                break;
        }

        // Verificar que la nueva posición esté dentro de los límites del mapa
        if (nuevaPosicionX > 0 && nuevaPosicionX < columnas && nuevaPosicionY > 0 && nuevaPosicionY < filas) {
            // Verificar que la nueva posición no sea una pared
            if (!matriz[nuevaPosicionY + 1][nuevaPosicionX + 1].equals("O")) {
                // Actualizar la posición del jugador
                matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "-";
                jugador.setPosicionX(nuevaPosicionX);
                jugador.setPosicionY(nuevaPosicionY);
                matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "@";
            }
        }
    }
}