import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * La clase Modelo maneja la lógica de datos y la interacción con los archivos.
 */
public class Modelo {
    private Path rutaDirectorioEscenarios;
    private Path rutaDirectorioJugadores;
    private Path rutaDirectorioPartidas;

    /**
     * Constructor que inicializa las rutas de los directorios de escenarios y jugadores.
     */
    public Modelo() {
        rutaDirectorioEscenarios = Paths.get("escenarios");
        rutaDirectorioJugadores = Paths.get("jugadores");
        rutaDirectorioPartidas = Paths.get("partidas");

        verificarYCrearDirectorios();
    }

    /**
     * Verifica y crea los directorios necesarios si no existen.
     */
    private void verificarYCrearDirectorios() {
        try {
            if (Files.notExists(rutaDirectorioEscenarios)) {
                Files.createDirectories(rutaDirectorioEscenarios);
                System.out.println("Directorio de escenarios creado.");
            } else {
                System.out.println("Directorio de escenarios ya existe.");
            }

            if (Files.notExists(rutaDirectorioJugadores)) {
                Files.createDirectories(rutaDirectorioJugadores);
                System.out.println("Directorio de jugadores creado.");
            } else {
                System.out.println("Directorio de jugadores ya existe.");
            }

            if (Files.notExists(rutaDirectorioPartidas)) {
                Files.createDirectories(rutaDirectorioPartidas);
                System.out.println("Directorio de partidas creado.");
            } else {
                System.out.println("Directorio de partidas ya existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear los directorios: " + e.getMessage());
        }
    }

    /**
     * Crea un archivo de escenario con un nombre específico y dimensiones dadas.
     *
     * @param nombreEscenario El nombre del escenario.
     * @param codigoEscenario  La lista de códigos que define el escenario.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void crearEscenario(String nombreEscenario, List<String> codigoEscenario) throws IOException {
        Path rutaEscenario = rutaDirectorioEscenarios.resolve(nombreEscenario + ".txt");
        Files.write(rutaEscenario, codigoEscenario);
    }

    /**
     * Carga un escenario desde un archivo.
     *
     * @param nombreEscenario El nombre del escenario.
     * @return La lista de líneas que representan el escenario.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public List<String> cargarEscenario(String nombreEscenario) throws IOException {
        Path rutaEscenario = rutaDirectorioEscenarios.resolve(nombreEscenario + ".txt");
        return Files.readAllLines(rutaEscenario);
    }

    /**
     * Guarda los datos de un jugador en un archivo.
     *
     * @param jugador El jugador a guardar.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void guardarJugador(Jugador jugador) throws IOException {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(jugador.getNombre()) + ".bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(rutaJugador))) {
            oos.writeObject(jugador);
        }
    }

    /**
     * Carga los datos de un jugador desde un archivo.
     *
     * @param nombre El nombre del jugador.
     * @return El jugador cargado desde el archivo.
     * @throws IOException          Si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException Si la clase del objeto no se encuentra.
     */
    public Jugador cargarJugador(String nombre) throws IOException, ClassNotFoundException {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(nombre) + ".bin");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(rutaJugador))) {
            return (Jugador) ois.readObject();
        }
    }

    /**
     * Verifica si existe un archivo para un jugador.
     *
     * @param nombre El nombre del jugador.
     * @return true si el archivo existe, false en caso contrario.
     */
    public boolean existeJugador(String nombre) {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(nombre) + ".bin");
        return Files.exists(rutaJugador);
    }

    /**
     * Limpia el nombre del jugador para que sea un nombre de archivo válido.
     *
     * @param nombre El nombre del jugador.
     * @return El nombre limpio.
     */
    public static String limpiarNombre(String nombre) {
        return nombre.replaceAll("[^a-zA-Z0-9_-]", "_");
    }
}
