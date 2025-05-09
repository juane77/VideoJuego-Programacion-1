package src.modelos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Modelo {
    private static Jugador jugadorActual;

    public static void setJugador(Jugador jugador) {
        jugadorActual = jugador;
    }

    public static Jugador getJugador() {
        return jugadorActual;
    }

    private final Path rutaDirectorioEscenarios = Paths.get("resources/escenarios");
    private final Path rutaDirectorioJugadores = Paths.get("jugadores");

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

    public void guardarJugador(Jugador jugador) throws IOException {
        Path ruta = rutaDirectorioJugadores.resolve(jugador.getNombre() + ".bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(ruta))) {
            oos.writeObject(jugador);
        }
    }

    public Jugador cargarJugador(String nombre) throws IOException, ClassNotFoundException {
        Path ruta = rutaDirectorioJugadores.resolve(nombre + ".bin");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(ruta))) {
            return (Jugador) ois.readObject();
        }
    }

    public boolean existeJugador(String nombre) {
        return Files.exists(rutaDirectorioJugadores.resolve(nombre + ".bin"));
    }

    public List<String> cargarEscenario(String nombre) throws IOException {
        return Files.readAllLines(rutaDirectorioEscenarios.resolve(nombre + ".txt"));
    }

    // Ruta del escenario seleccionado dinámicamente
    private static String rutaEscenario = "resources/escenarios/escenario1.txt";

    public static void setRutaEscenario(String ruta) {
        rutaEscenario = ruta;
    }

    public static String getRutaEscenario() {
        return rutaEscenario;
    }
}
