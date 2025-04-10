import java.io.IOException;
import java.util.List;

/**
 * La clase Controlador coordina las operaciones entre el Modelo y la Vista.
 */
public class Controlador {
    private Modelo modelo;
    private Vista vista;

    /**
     * Constructor para crear un nuevo controlador.
     *
     * @param modelo El objeto Modelo.
     * @param vista  El objeto Vista.
     */
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    /**
     * Ejecuta el flujo principal del programa.
     */
    public void ejecutar() {
        String nombreJugador = vista.pedirNombreJugador();
        Jugador jugador;

        if (modelo.existeJugador(nombreJugador)) {
            try {
                jugador = modelo.cargarJugador(nombreJugador);
                vista.mostrarMensaje("Bienvenido de nuevo, " + jugador.getNombre());
            } catch (IOException e) {
                e.printStackTrace();
                vista.mostrarMensaje("Error al cargar los datos del jugador.");
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                vista.mostrarMensaje("Error al cargar los datos del jugador.");
                return;
            }
        } else {
            String gmail = vista.pedirGmail();
            jugador = new Jugador(nombreJugador, gmail);
            try {
                modelo.guardarJugador(jugador);
                vista.mostrarMensaje("Jugador registrado con éxito.");
            } catch (IOException e) {
                e.printStackTrace();
                vista.mostrarMensaje("Error al guardar los datos del jugador.");
                return;
            }
        }

        try {
            List<String> codigoEscenario = modelo.cargarEscenario("escenario1");
            Escenario escenario = new Escenario("escenario1", codigoEscenario, jugador);
            vista.mostrarEscenario(escenario.obtenerEscenario());

            boolean jugando = true;
            while (jugando) {
                char direccion = vista.pedirDireccion();
                escenario.moverJugador(direccion);
                vista.mostrarEscenario(escenario.obtenerEscenario());
            }
        } catch (IOException e) {
            e.printStackTrace();
            vista.mostrarMensaje("Error al manejar el escenario.");
        }
    }
}