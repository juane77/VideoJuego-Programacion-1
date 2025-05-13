package base_datos;

/**
 * Clase que representa el tiempo registrado por un jugador en un escenario.
 * Contiene el nombre del jugador y el tiempo que tardó en completar el escenario.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class TiempoJugador {

    /**
     * Nombre del jugador.
     */
    private String nombreJugador;

    /**
     * Tiempo registrado en segundos.
     */
    private double tiempoSegundos;

    /**
     * Constructor de la clase TiempoJugador.
     * Inicializa el nombre del jugador y el tiempo registrado.
     * 
     * @param nombreJugador Nombre del jugador.
     * @param tiempoSegundos Tiempo registrado en segundos.
     */
    public TiempoJugador(String nombreJugador, double tiempoSegundos) {
        this.nombreJugador = nombreJugador;
        this.tiempoSegundos = tiempoSegundos;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return El nombre del jugador.
     */
    public String getNombreJugador() {
        return nombreJugador;
    }

    /**
     * Obtiene el tiempo registrado en segundos.
     * 
     * @return El tiempo registrado en segundos.
     */
    public double getTiempoSegundos() {
        return tiempoSegundos;
    }
}