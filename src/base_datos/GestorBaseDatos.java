package base_datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la conexión y las operaciones con la base de datos.
 * Permite inicializar la base de datos, insertar tiempos de jugadores y obtener
 * el Top 10 de jugadores por escenario.
 * 
 * @version 1.0
 */
public class GestorBaseDatos {

    /**
     * URL de conexión a la base de datos SQLite.
     */
    private static final String URL = "jdbc:sqlite:tiempos.db";

    /**
     * Inicializa la base de datos creando la tabla "tiempos" si no existe.
     * La tabla almacena los tiempos registrados por los jugadores.
     */
    public static void inicializarBaseDatos() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sql = """
                CREATE TABLE IF NOT EXISTS tiempos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre_jugador TEXT NOT NULL,
                    tiempo_segundos REAL NOT NULL,
                    escenario TEXT NOT NULL
                );
            """;
            conn.createStatement().execute(sql); // Ejecuta la creación de la tabla
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza de la excepción en caso de error
        }
    }

    /**
     * Inserta un nuevo tiempo registrado por un jugador en la base de datos.
     * 
     * @param nombre Nombre del jugador.
     * @param tiempo Tiempo registrado en segundos.
     * @param escenario Escenario en el que se registró el tiempo.
     */
    public static void insertarTiempo(String nombre, double tiempo, String escenario) {
        String sql = "INSERT INTO tiempos (nombre_jugador, tiempo_segundos, escenario) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asigna los valores a los parámetros de la consulta
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, tiempo);
            pstmt.setString(3, escenario);
            pstmt.executeUpdate(); // Ejecuta la inserción

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza de la excepción en caso de error
        }
    }

    /**
     * Obtiene el Top 10 de jugadores con los mejores tiempos para un escenario específico.
     * 
     * @param escenario Escenario para el cual se desea obtener el Top 10.
     * @return Una lista de objetos {@link TiempoJugador} con los mejores tiempos.
     */
    public static List<TiempoJugador> obtenerTop10PorEscenario(String escenario) {
        List<TiempoJugador> lista = new ArrayList<>();
        String sql = """
            SELECT nombre_jugador, tiempo_segundos 
            FROM tiempos
            WHERE escenario = ?
            ORDER BY tiempo_segundos ASC
            LIMIT 10
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asigna el escenario al parámetro de la consulta
            stmt.setString(1, escenario);
            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta

            // Recorre los resultados y los agrega a la lista
            while (rs.next()) {
                String nombre = rs.getString("nombre_jugador");
                double tiempo = rs.getDouble("tiempo_segundos");
                lista.add(new TiempoJugador(nombre, tiempo));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza de la excepción en caso de error
        }

        return lista; // Devuelve la lista con los mejores tiempos
    }
}