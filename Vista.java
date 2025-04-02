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
     * Muestra un escenario en la consola.
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
}
