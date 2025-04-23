import java.io.IOException;
import java.util.List;

public class Controlador {
    private Modelo modelo;
    private VistaInicio vistaInicio;
    private VistaJuego vistaJuego;
    private VistaFin vistaFin;

    public Controlador(Modelo modelo, VistaInicio vistaInicio, VistaJuego vistaJuego, VistaFin vistaFin) {
        this.modelo = modelo;
        this.vistaInicio = vistaInicio;
        this.vistaJuego = vistaJuego;
        this.vistaFin = vistaFin;
    }

    public void ejecutar(String nombreJugador) {
        Jugador jugador;

        if (modelo.existeJugador(nombreJugador)) {
            try {
                jugador = modelo.cargarJugador(nombreJugador);
                vistaInicio.mostrarMensaje("Bienvenido de nuevo, " + jugador.getNombre());
            } catch (IOException | ClassNotFoundException e) {
                vistaInicio.mostrarMensaje("Error al cargar los datos del jugador.");
                return;
            }
        } else {
            String gmail = vistaInicio.pedirGmail();
            jugador = new Jugador(nombreJugador, gmail);
            try {
                modelo.guardarJugador(jugador);
                vistaInicio.mostrarMensaje("Jugador registrado con éxito.");
            } catch (IOException e) {
                vistaInicio.mostrarMensaje("Error al guardar los datos del jugador.");
                return;
            }
        }

        try {
            List<String> codigoEscenario = modelo.cargarEscenario("escenario1");
            Escenario escenario = new Escenario("escenario1", codigoEscenario, jugador);
            vistaJuego.mostrarEscenario(escenario.obtenerEscenario());

            boolean jugando = true;
            while (jugando) {
                char direccion = vistaJuego.pedirDireccion();
                escenario.moverJugador(direccion);
                vistaJuego.mostrarEscenario(escenario.obtenerEscenario());
            }
        } catch (IOException e) {
            vistaJuego.mostrarMensaje("Error al manejar el escenario.");
        }

        vistaFin.mostrarResultados();
    }
}
