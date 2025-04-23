package src.modelos;
import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private String gmail;
    private int posicionX;
    private int posicionY;

    public Jugador(String nombre, String gmail) {
        this.nombre = nombre;
        this.gmail = gmail;
        this.posicionX = 1;
        this.posicionY = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGmail() {
        return gmail;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
