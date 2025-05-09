package base_datos;

public class TiempoJugador {
    private String nombreJugador;
    private double tiempoSegundos; // CAMBIADO a double

    public TiempoJugador(String nombreJugador, double tiempoSegundos) {
        this.nombreJugador = nombreJugador;
        this.tiempoSegundos = tiempoSegundos;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public double getTiempoSegundos() {
        return tiempoSegundos;
    }
}
