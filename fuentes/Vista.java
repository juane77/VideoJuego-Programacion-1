import java.util.List;
import java.util.Scanner;

/**
 * La clase Vista maneja la interacción con el usuario.
 */
public class Vista {
    private Scanner scanner;

    /**
     * Constructor que inicializa el objeto Scanner para leer la entrada del usuario.
     */
    public Vista() {
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el escenario en la consola.
     *
     * @param escenario Una lista de líneas que representan el escenario.
     */
    public void mostrarEscenario(List<String> escenario) {
        for (String linea : escenario) {
            System.out.println(linea);
        }
    }

    /**
     * Pide al usuario que ingrese su nombre de usuario.
     *
     * @return El nombre de usuario ingresado por el usuario.
     */
    public String pedirNombreJugador() {
        System.out.print("Introduce tu nombre de usuario: ");
        return scanner.nextLine();
    }

    /**
     * Pide al usuario que ingrese su Gmail.
     *
     * @return El Gmail ingresado por el usuario.
     */
    public String pedirGmail() {
        System.out.print("Introduce tu Gmail: ");
        return scanner.nextLine();
    }

    /**
     * Muestra un mensaje en la consola.
     *
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Pide al usuario que elija una dirección para mover al jugador.
     *
     * @return La dirección elegida por el usuario ('W', 'A', 'S', 'D').
     */
    public char pedirDireccion() {
        System.out.print("Elige una dirección (W/A/S/D) y presiona Enter: ");
        String input = scanner.nextLine();
        if (input.length() > 0) {
            return input.charAt(0);
        }
        return ' ';
    }
}
