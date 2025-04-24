package src.modelos;

import java.util.ArrayList;
import java.util.List;

public class Escenario {
    private String nombre;
    private int filas;
    private int columnas;
    private String[][] matriz;
    private Jugador jugador;

    public Escenario(String nombre, List<String> codigoEscenario, Jugador jugador) {
        this.nombre = nombre;
        this.filas = 10;
        this.columnas = 40;
        this.matriz = new String[filas + 2][columnas + 2];
        this.jugador = jugador;
        generarEscenario(codigoEscenario);
    }

    private void generarEscenario(List<String> codigoEscenario) {
        for (int i = 0; i < columnas + 2; i++) {
            matriz[0][i] = "|";
            matriz[filas + 1][i] = "|";
        }

        for (int i = 0; i < filas + 2; i++) {
            matriz[i][0] = "|";
            matriz[i][columnas + 1] = "|";
        }

        for (int i = 0; i < filas; i++) {
            String lineaCodigo = codigoEscenario.get(i);
            int posicion = 1;
            int indice = 0;
            while (indice < lineaCodigo.length()) {
                int cantidad = 0;
                while (indice < lineaCodigo.length() && lineaCodigo.charAt(indice) >= '0' && lineaCodigo.charAt(indice) <= '9') {
                    cantidad = cantidad * 10 + (lineaCodigo.charAt(indice) - '0');
                    indice++;
                }

                char tipo = lineaCodigo.charAt(indice);
                indice++;

                for (int k = 0; k < cantidad && posicion < columnas + 1; k++) {
                    matriz[i + 1][posicion++] = (tipo == 'E') ? "-" : "O";
                }
            }

            while (posicion < columnas + 1) {
                matriz[i + 1][posicion++] = "-";
            }
        }

        matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "@";
    }

    public List<String> obtenerEscenario() {
        List<String> escenario = new ArrayList<>();
        for (String[] fila : matriz) {
            escenario.add(String.join("", fila));
        }
        return escenario;
    }

    public void moverJugador(char direccion) {
        int nuevaPosicionX = jugador.getPosicionX();
        int nuevaPosicionY = jugador.getPosicionY();

        switch (direccion) {
            case 'W':
            case 'w':
                nuevaPosicionY--;
                break;
            case 'S':
            case 's':
                nuevaPosicionY++;
                break;
            case 'A':
            case 'a':
                nuevaPosicionX--;
                break;
            case 'D':
            case 'd':
                nuevaPosicionX++;
                break;
        }

        if (nuevaPosicionX > 0 && nuevaPosicionX < columnas && nuevaPosicionY > 0 && nuevaPosicionY < filas) {
            if (!matriz[nuevaPosicionY + 1][nuevaPosicionX + 1].equals("O")) {
                matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "-";
                jugador.setPosicionX(nuevaPosicionX);
                jugador.setPosicionY(nuevaPosicionY);
                matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "@";
            }
        }
    }

    public boolean jugadorHaGanado() {
    return obtenerEscenario().stream().anyMatch(linea -> linea.contains("@O"));
    }
    
    public boolean jugadorHaPerdido() {
   
    return false; 
    }

    public boolean jugadorEnSalida() {
        return matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1].equals("O");
    }
}
