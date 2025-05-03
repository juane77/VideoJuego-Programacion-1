// filepath: c:\Users\ferna\Programacion\Programación 3 Trimestre\VideojuegoProgramacion\VideoJuego-Programacion-1\src\base_datos\TiempoJugador.java
package base_datos;


public class TiempoJugador {
    private String nombreJugador;
    private long tiempoSegundos;

    public TiempoJugador(String nombreJugador, long tiempoSegundos) {
        this.nombreJugador = nombreJugador;
        this.tiempoSegundos = tiempoSegundos;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public long getTiempoSegundos() {
        return tiempoSegundos;
    }
}