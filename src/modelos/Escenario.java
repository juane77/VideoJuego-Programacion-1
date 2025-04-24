package src.modelos;

import java.util.ArrayList;
import java.util.List;

public class Escenario {
    private String nombre;
    private int filas = 10;
    private int columnas = 40;
    private String[][] matriz;
    private Jugador jugador;
    private boolean juegoTerminado = false;

    public Escenario(String nombre, List<String> codigoEscenario, Jugador jugador) {
        this.nombre = nombre;
        this.jugador = jugador;
        this.matriz = new String[filas + 2][columnas + 2];
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
                while (indice < lineaCodigo.length() && Character.isDigit(lineaCodigo.charAt(indice))) {
                    cantidad = cantidad * 10 + (lineaCodigo.charAt(indice) - '0');
                    indice++;
                }
                char tipo = lineaCodigo.charAt(indice++);
                for (int k = 0; k < cantidad && posicion < columnas + 1; k++) {
                    switch (tipo) {
                        case 'E': matriz[i + 1][posicion++] = "-"; break;
                        case 'O': matriz[i + 1][posicion++] = "O"; break;
                        case 'F': matriz[i + 1][posicion++] = "F"; break;
                        default: matriz[i + 1][posicion++] = "-"; break;
                    }
                }
            }
            while (posicion < columnas + 1) {
                matriz[i + 1][posicion++] = "-";
            }
        }
        matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "@";
    }

    public List<String> obtenerEscenario() {
        List<String> resultado = new ArrayList<>();
        for (String[] fila : matriz) {
            resultado.add(String.join("", fila));
        }
        return resultado;
    }

    public void moverJugador(char direccion) {
        if (juegoTerminado) return;

        int nuevaX = jugador.getPosicionX();
        int nuevaY = jugador.getPosicionY();

        switch (direccion) {
            case 'W': case 'w': nuevaY--; break;
            case 'S': case 's': nuevaY++; break;
            case 'A': case 'a': nuevaX--; break;
            case 'D': case 'd': nuevaX++; break;
        }

        String celdaDestino = matriz[nuevaY + 1][nuevaX + 1];
        if (nuevaX > 0 && nuevaX < columnas && nuevaY > 0 && nuevaY < filas && !"O".equals(celdaDestino)) {
            matriz[jugador.getPosicionY() + 1][jugador.getPosicionX() + 1] = "-";
            jugador.setPosicionX(nuevaX);
            jugador.setPosicionY(nuevaY);
            if ("F".equals(celdaDestino)) {
                juegoTerminado = true;
            }
            matriz[nuevaY + 1][nuevaX + 1] = "@";
        }
    }

    public boolean esJuegoTerminado() {
        return juegoTerminado;
    }
}
