import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Modelo {
    private Path rutaDirectorioEscenarios;
    private Path rutaDirectorioJugadores;
    private Path rutaDirectorioPartidas;

    public Modelo() {
        rutaDirectorioEscenarios = Paths.get("recursos/escenarios");
        rutaDirectorioJugadores = Paths.get("compilados/jugadores");
        rutaDirectorioPartidas = Paths.get("compilados/partidas");
        verificarYCrearDirectorios();
    }

    private void verificarYCrearDirectorios() {
        try {
            if (Files.notExists(rutaDirectorioEscenarios)) {
                Files.createDirectories(rutaDirectorioEscenarios);
            }

            if (Files.notExists(rutaDirectorioJugadores)) {
                Files.createDirectories(rutaDirectorioJugadores);
            }

            if (Files.notExists(rutaDirectorioPartidas)) {
                Files.createDirectories(rutaDirectorioPartidas);
            }
        } catch (IOException e) {
            System.err.println("Error al crear los directorios: " + e.getMessage());
        }
    }

    public void crearEscenario(String nombreEscenario, List<String> codigoEscenario) throws IOException {
        Path rutaEscenario = rutaDirectorioEscenarios.resolve(nombreEscenario + ".txt");
        Files.write(rutaEscenario, codigoEscenario);
    }

    public List<String> cargarEscenario(String nombreEscenario) throws IOException {
        Path rutaEscenario = rutaDirectorioEscenarios.resolve(nombreEscenario + ".txt");
        return Files.readAllLines(rutaEscenario);
    }

    public void guardarJugador(Jugador jugador) throws IOException {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(jugador.getNombre()) + ".bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(rutaJugador))) {
            oos.writeObject(jugador);
        }
    }

    public Jugador cargarJugador(String nombre) throws IOException, ClassNotFoundException {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(nombre) + ".bin");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(rutaJugador))) {
            return (Jugador) ois.readObject();
        }
    }

    public boolean existeJugador(String nombre) {
        Path rutaJugador = rutaDirectorioJugadores.resolve(limpiarNombre(nombre) + ".bin");
        return Files.exists(rutaJugador);
    }

    public static String limpiarNombre(String nombre) {
        return nombre.replaceAll("[^a-zA-Z0-9_-]", "_");
    }
}
