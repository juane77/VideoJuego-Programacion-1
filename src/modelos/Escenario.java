package modelos;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Escenario {

    private final int filas = 15;
    private final int columnas = 20;
    private String[][] matriz;

    public Escenario(String rutaEscenario) {
        matriz = new String[filas][columnas];
        List<String> lineas = cargarLineas(rutaEscenario);
        generarEscenario(lineas);
    }

    private List<String> cargarLineas(String ruta) {
        List<String> lineas = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream(ruta);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineas;
    }

    private void generarEscenario(List<String> codigoEscenario) {
        for (int i = 0; i < filas; i++) {
            String linea = codigoEscenario.get(i);
            int col = 0;
            int j = 0;
            while (j < linea.length()) {
                int cantidad = 0;
                while (j < linea.length() && Character.isDigit(linea.charAt(j))) {
                    cantidad = cantidad * 10 + (linea.charAt(j) - '0');
                    j++;
                }
                if (j >= linea.length()) break;
                char tipo = linea.charAt(j++);

                for (int k = 0; k < cantidad && col < columnas; k++) {
                    switch (tipo) {
                        case 'E' -> matriz[i][col++] = "E"; // suelo
                        case 'O' -> matriz[i][col++] = "O"; // obstáculo
                        case 'F' -> matriz[i][col++] = "F"; // meta
                        case 'M' -> matriz[i][col++] = "M"; // muro
                        case 'J' -> matriz[i][col++] = "E"; // el jugador se dibuja aparte
                        default -> matriz[i][col++] = "E"; // por defecto, suelo
                    }
                }
            }
            while (col < columnas) {
                matriz[i][col++] = "E";
            }
        }
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int[] getPosicionJugador() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if ("J".equals(matriz[i][j])) {
                    matriz[i][j] = "E"; // LIMPIAR para que no pinte un tile suelto
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{1, 1}; // por defecto
    }
    
    
}
