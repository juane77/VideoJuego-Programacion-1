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
}

