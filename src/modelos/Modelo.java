package src.modelos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Clase que gestiona la lógica principal del modelo del juego.
 * Se encarga de manejar los datos relacionados con los jugadores y escenarios,
 * incluyendo su almacenamiento y recuperación desde el sistema de archivos.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class Modelo {

    /**
     * Jugador actual que está en sesión.
     */
    private static Jugador jugadorActual;

    /**
     * Establece el jugador actual.
     * 
     * @param jugador El jugador que se establecerá como actual.
     */
    public static void setJugador(Jugador jugador) {
        jugadorActual = jugador;
    }

    /**
     * Obtiene el jugador actual.
     * 
     * @return El jugador actual.
     */
    public static Jugador getJugador() {
        return jugadorActual;
    }

    /**
     * Ruta del directorio donde se almacenan los escenarios.
     */
    private final Path rutaDirectorioEscenarios = Paths.get("resources/escenarios");

    /**
     * Ruta del directorio donde se almacenan los datos de los jugadores.
     */
    private final Path rutaDirectorioJugadores = Paths.get("jugadores");

    /**
     * Constructor de la clase Modelo.
     * Crea los directorios necesarios para almacenar escenarios y jugadores si no existen.
     */
    public Modelo() {
        try {
            if (Files.notExists(rutaDirectorioEscenarios))
                Files.createDirectories(rutaDirectorioEscenarios);
            if (Files.notExists(rutaDirectorioJugadores))
                Files.createDirectories(rutaDirectorioJugadores);
        } catch (IOException e) {
            System.err.println("Error al crear directorios: " + e.getMessage());
        }
    }

    /**
     * Guarda los datos de un jugador en un archivo binario.
     * 
     * @param jugador El jugador que se desea guardar.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    public void guardarJugador(Jugador jugador) throws IOException {
        Path ruta = rutaDirectorioJugadores.resolve(jugador.getNombre() + ".bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(ruta))) {
            oos.writeObject(jugador);
        }
    }

    /**
     * Carga los datos de un jugador desde un archivo binario.
     * 
     * @param nombre El nombre del jugador que se desea cargar.
     * @return El objeto Jugador cargado desde el archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException Si la clase Jugador no se encuentra.
     */
    public Jugador cargarJugador(String nombre) throws IOException, ClassNotFoundException {
        Path ruta = rutaDirectorioJugadores.resolve(nombre + ".bin");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            return (Jugador) ois.readObject();
        }
    }

    /**
     * Verifica si existe un archivo de datos para un jugador específico.
     * 
     * @param nombre El nombre del jugador.
     * @return {@code true} si el archivo del jugador existe, {@code false} en caso contrario.
     */
    public boolean existeJugador(String nombre) {
        return Files.exists(rutaDirectorioJugadores.resolve(nombre + ".bin"));
    }

    /**
     * Carga las líneas de un archivo de escenario.
     * 
     * @param nombre El nombre del archivo del escenario (sin extensión).
     * @return Una lista de cadenas que representan las líneas del archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public List<String> cargarEscenario(String nombre) throws IOException {
        return Files.readAllLines(rutaDirectorioEscenarios.resolve(nombre + ".txt"));
    }

    /**
     * Ruta del escenario que se utiliza actualmente en el juego.
     */
    private static String rutaEscenario = "escenarios/escenario1.txt";

    /**
     * Establece la ruta del escenario actual.
     * 
     * @param ruta La nueva ruta del escenario.
     */
    public static void setRutaEscenario(String ruta) {
        rutaEscenario = ruta;
    }

    /**
     * Obtiene la ruta del escenario actual.
     * 
     * @return La ruta del escenario actual.
     */
    public static String getRutaEscenario() {
        return rutaEscenario;
    }
}