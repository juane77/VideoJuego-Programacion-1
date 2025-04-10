import java.io.Serializable;

/**
 * Clase que representa a un jugador en el juego.
 */
public class Jugador implements Serializable {
    private String nombre;
    private String gmail;
    private int posicionX;
    private int posicionY;

    /**
     * Constructor para crear un nuevo jugador.
     *
     * @param nombre El nombre del jugador.
     * @param gmail  El correo electrónico del jugador.
     */
    public Jugador(String nombre, String gmail) {
        this.nombre = nombre;
        this.gmail = gmail;
        this.posicionX = 1;
        this.posicionY = 1; 
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el correo electrónico del jugador.
     *
     * @return El correo electrónico del jugador.
     */
    public String getGmail() {
        return gmail;
    }

    /**
     * Obtiene la posición X del jugador en el mapa.
     *
     * @return La posición X del jugador.
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * Obtiene la posición Y del jugador en el mapa.
     *
     * @return La posición Y del jugador.
     */
    public int getPosicionY() {
        return posicionY;
    }

    /**
     * Establece la posición X del jugador en el mapa.
     *
     * @param posicionX La nueva posición X del jugador.
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    /**
     * Establece la posición Y del jugador en el mapa.
     *
     * @param posicionY La nueva posición Y del jugador.
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
