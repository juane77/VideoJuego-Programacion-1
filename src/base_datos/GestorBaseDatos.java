package base_datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorBaseDatos {
    private static final String URL = "jdbc:sqlite:tiempos.db";

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
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarTiempo(String nombre, double tiempo, String escenario) {
        String sql = "INSERT INTO tiempos (nombre_jugador, tiempo_segundos, escenario) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setDouble(2, tiempo);
            pstmt.setString(3, escenario);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

            stmt.setString(1, escenario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre_jugador");
                double tiempo = rs.getDouble("tiempo_segundos");
                lista.add(new TiempoJugador(nombre, tiempo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
