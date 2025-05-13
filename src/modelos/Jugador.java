package src.modelos;

import java.io.Serializable;

/**
 * Clase que representa a un jugador en el juego.
 * Implementa la interfaz {@link Serializable} para permitir la serialización de los datos del jugador.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class Jugador implements Serializable {

    /**
     * Nombre del jugador.
     */
    private String nombre;

    /**
     * Gmail del jugador.
     */
    private String gmail;

    /**
     * Posición X del jugador en el escenario.
     */
    private int posicionX;

    /**
     * Posición Y del jugador en el escenario.
     */
    private int posicionY;

    /**
     * Constructor de la clase Jugador.
     * Inicializa el nombre, el gmail y establece la posición inicial del jugador en (1, 1).
     * 
     * @param nombre Nombre del jugador.
     * @param gmail Gmail del jugador.
     */
    public Jugador(String nombre, String gmail) {
        this.nombre = nombre;
        this.gmail = gmail;
        this.posicionX = 1; // Posición inicial en X
        this.posicionY = 1; // Posición inicial en Y
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
     * Obtiene el gmail del jugador.
     * 
     * @return El gmail del jugador.
     */
    public String getGmail() {
        return gmail;
    }

    /**
     * Obtiene la posición X del jugador en el escenario.
     * 
     * @return La posición X del jugador.
     */
    public int getPosicionX() {
        return posicionX;
    }

    /**
     * Obtiene la posición Y del jugador en el escenario.
     * 
     * @return La posición Y del jugador.
     */
    public int getPosicionY() {
        return posicionY;
    }

    /**
     * Establece la posición X del jugador en el escenario.
     * 
     * @param posicionX La nueva posición X del jugador.
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    /**
     * Establece la posición Y del jugador en el escenario.
     * 
     * @param posicionY La nueva posición Y del jugador.
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}