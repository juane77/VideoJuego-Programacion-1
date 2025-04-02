import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

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

        // Crear directorios si no existen
        try {
            Files.createDirectories(rutaDirectorioEscenarios);
            Files.createDirectories(rutaDirectorioJugadores);
            Files.createDirectories(rutaDirectorioPartidas);
        } catch (IOException e) {
            System.err.println("Error al crear los directorios: " + e.getMessage());
        }
    }

    /**
     * Crea un archivo de escenario con un nombre específico y dimensiones dadas.
     *
     * @param nombreEscenario El nombre del escenario.
     * @param filas           El número de filas del escenario.
     * @param columnas        El número de columnas del escenario.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void crearEscenario(String nombreEscenario, int filas, int columnas) throws IOException {
        Path rutaEscenario = rutaDirectorioEscenarios.resolve(nombreEscenario + ".txt");
        Files.write(rutaEscenario, List.of(filas + " " + columnas));

        for (int i = 0; i < filas; i++) {
            String linea = ""; 
            for (int j = 0; j < columnas; j++) {
                if (Math.random() < 0.2) {
                    linea = linea + "O"; // Obstáculo
                } else {
                    linea = linea + "-"; // Espacio
                }
            }
            Files.write(rutaEscenario, List.of(linea), StandardOpenOption.APPEND);
        }
    }

    /**
     * Carga un escenario desde un archivo.
     */
    public List<String> cargarEscenario(String nombreEscenario) throws IOException {
        Path rutaEscenario = rutaDirectorioEscenarios.resolve(nombreEscenario + ".txt");
        return Files.readAllLines(rutaEscenario);
    }

    /**
     * Guarda los datos de un jugador en un archivo.
     */
    public void guardarJugador(String nombre, String gmail) throws IOException {
        if (!Files.exists(rutaDirectorioJugadores)) {
            Files.createDirectories(rutaDirectorioJugadores);
        }
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(nombre) + ".txt");
        Files.write(rutaJugador, List.of(nombre, gmail));
    }

    /**
     * Carga los datos de un jugador desde un archivo.
     */
    public List<String> cargarJugador(String nombre) throws IOException {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(nombre) + ".txt");
        return Files.readAllLines(rutaJugador);
    }

    /**
     * Verifica si existe un archivo para un jugador.
     */
    public boolean existeJugador(String nombre) {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(nombre) + ".txt");
        return Files.exists(rutaJugador);
    }

    /**
     * Limpia el nombre del jugador para que sea un nombre de archivo válido.
     */
    public static String limpiarNombre(String nombre) {
        return nombre.replaceAll("[^a-zA-Z0-9_-]", "_"); 
    }
}
