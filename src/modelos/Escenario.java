package modelos;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un escenario del juego.
 * Se encarga de cargar un escenario desde un archivo, procesarlo y generar una matriz
 * que representa los elementos del escenario.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class Escenario {

    /**
     * Número de filas del escenario.
     */
    private int filas;

    /**
     * Número de columnas del escenario.
     */
    private int columnas;

    /**
     * Matriz que representa el escenario.
     * Cada celda contiene un tipo de elemento del escenario.
     */
    private String[][] matriz;

    /**
     * Constructor de la clase Escenario.
     * Carga un escenario desde un archivo y lo procesa para generar la matriz.
     * 
     * @param rutaEscenario Ruta del archivo que contiene el código del escenario.
     */
    public Escenario(String rutaEscenario) {
        List<String> lineas = cargarLineas(rutaEscenario);
        this.filas = lineas.size();
        this.columnas = calcularColumnas(lineas);
        this.matriz = new String[filas][columnas];
        generarEscenario(lineas);
    }

    /**
     * Carga las líneas del archivo que contiene el escenario.
     * 
     * @param ruta Ruta del archivo del escenario (relativa al classpath).
     * @return Lista de líneas leídas del archivo.
     */
    private List<String> cargarLineas(String ruta) {
        List<String> lineas = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream(ruta);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza en caso de error
        }
        return lineas;
    }

    /**
     * Calcula el número máximo de columnas en el escenario.
     * 
     * @param lineas Lista de líneas que representan el escenario.
     * @return Número máximo de columnas.
     */
    private int calcularColumnas(List<String> lineas) {
        int maxCols = 0;
        for (String linea : lineas) {
            int count = 0;
            int j = 0;
            while (j < linea.length()) {
                int cantidad = 0;
                // Procesa los números que indican la cantidad de elementos
                while (j < linea.length() && Character.isDigit(linea.charAt(j))) {
                    cantidad = cantidad * 10 + (linea.charAt(j) - '0');
                    j++;
                }
                j++; // Salta el tipo de elemento
                count += cantidad;
            }
            maxCols = Math.max(maxCols, count); // Actualiza el máximo
        }
        return maxCols;
    }

    /**
     * Genera la matriz del escenario a partir del código del escenario.
     * 
     * @param codigoEscenario Lista de líneas que representan el código del escenario.
     */
    private void generarEscenario(List<String> codigoEscenario) {
        for (int i = 0; i < filas; i++) {
            String linea = codigoEscenario.get(i);
            int col = 0;
            int j = 0;
            while (j < linea.length()) {
                int cantidad = 0;
                // Procesa los números que indican la cantidad de elementos
                while (j < linea.length() && Character.isDigit(linea.charAt(j))) {
                    cantidad = cantidad * 10 + (linea.charAt(j) - '0');
                    j++;
                }
                if (j >= linea.length()) break;
                char tipo = linea.charAt(j++); // Obtiene el tipo de elemento

                // Llena la matriz con el tipo de elemento
                for (int k = 0; k < cantidad && col < columnas; k++) {
                    switch (tipo) {
                        case 'E' -> matriz[i][col++] = "E"; // Espacio vacío
                        case 'O' -> matriz[i][col++] = "O"; // Obstáculo
                        case 'F' -> matriz[i][col++] = "F"; // Final
                        case 'M' -> matriz[i][col++] = "M"; // Moneda
                        case 'J' -> matriz[i][col++] = "J"; // Jugador
                        default -> matriz[i][col++] = "E"; // Por defecto, espacio vacío
                    }
                }
            }
            // Llena el resto de la fila con espacios vacíos
            while (col < columnas) {
                matriz[i][col++] = "E";
            }
        }
    }

    /**
     * Obtiene la matriz que representa el escenario.
     * 
     * @return Matriz del escenario.
     */
    public String[][] getMatriz() {
        return matriz;
    }

    /**
     * Obtiene el número de filas del escenario.
     * 
     * @return Número de filas.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Obtiene el número de columnas del escenario.
     * 
     * @return Número de columnas.
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Obtiene la posición inicial del jugador en el escenario.
     * Si no se encuentra al jugador, devuelve la posición por defecto (1, 1).
     * 
     * @return Arreglo con las coordenadas [fila, columna] del jugador.
     */
    public int[] getPosicionJugador() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if ("J".equals(matriz[i][j])) {
                    matriz[i][j] = "E"; // Reemplaza la posición del jugador con espacio vacío
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{1, 1}; // Posición por defecto
    }
}