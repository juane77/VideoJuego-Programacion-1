import java.io.IOException;
import java.util.List;

/**
 * La clase Controlador coordina las operaciones entre el Modelo y la Vista.
 */
public class Controlador {
    private Modelo modelo;
    private Vista vista;

    /**
     * Constructor que inicializa el Modelo y la Vista.
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
        if (modelo.existeJugador(nombreJugador)) {
            try {
                List<String> datosJugador = modelo.cargarJugador(nombreJugador);
                vista.mostrarMensaje("Bienvenido de nuevo, " + datosJugador.get(0));
            } catch (IOException e) {
                e.printStackTrace(); // Muestra detalles del error en consola
                vista.mostrarMensaje("Error al cargar los datos del jugador.");
            }
        } else {
            String gmail = vista.pedirGmail();
            try {
                modelo.guardarJugador(nombreJugador, gmail);
                vista.mostrarMensaje("Jugador registrado con éxito.");
            } catch (IOException e) {
                e.printStackTrace(); // Muestra detalles del error en consola
                vista.mostrarMensaje("Error al guardar los datos del jugador.");
            }
        }

        // Crear y mostrar un escenario de ejemplo
        try {
            modelo.crearEscenario("escenario1", 5, 10);
            List<String> escenario = modelo.cargarEscenario("escenario1");
            vista.mostrarEscenario(escenario);
        } catch (IOException e) {
            e.printStackTrace();
            vista.mostrarMensaje("Error al manejar el escenario.");
        }
    }
}
