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
                    tiempo_segundos INTEGER NOT NULL
                );
            """;
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarTiempo(String nombre, long tiempo) {
        String sql = "INSERT INTO tiempos (nombre, tiempo) VALUES (?, ?)";
    
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:tiempos.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            pstmt.setLong(2, tiempo);
            pstmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<TiempoJugador> obtenerTop10() {
        List<TiempoJugador> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL)) {
            ResultSet rs = conn.createStatement().executeQuery("""
                SELECT nombre_jugador, tiempo_segundos FROM tiempos
                ORDER BY tiempo_segundos ASC LIMIT 10
            """);

            while (rs.next()) {
                lista.add(new TiempoJugador(rs.getString("nombre_jugador"), rs.getLong("tiempo_segundos")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
} 
    

