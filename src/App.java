package src;

import base_datos.GestorBaseDatos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación que extiende {@link Application}.
 * Se encarga de inicializar la base de datos y gestionar las vistas principales de la aplicación.
 * 
 * @version 1.0
 * @author Fernando y Juane
 * 
 */
public class App extends Application {

    /**
     * Ventana principal de la aplicación.
     */
    private static Stage ventanaPrincipal;

    /**
     * Método principal de inicio de la aplicación.
     * Este método se ejecuta al lanzar la aplicación.
     *
     * @param primaryStage La ventana principal proporcionada por JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        // Inicializa la base de datos al iniciar la aplicación.
        GestorBaseDatos.inicializarBaseDatos();

        // Asigna la ventana principal y muestra la vista inicial (Splash.fxml).
        ventanaPrincipal = primaryStage;
        mostrarVista("Splash.fxml");
    }

    /**
     * Muestra una vista específica en la ventana principal.
     *
     * @param nombreFXML El nombre del archivo FXML que se desea cargar (por ejemplo, "Splash.fxml").
     */
    public static void mostrarVista(String nombreFXML) {
        try {
            // Carga el archivo FXML especificado.
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/" + nombreFXML));
            Parent root = loader.load();

            // Crea una nueva escena con dimensiones predeterminadas.
            Scene escena = new Scene(root, 800, 600);

            // Configura la escena en la ventana principal.
            ventanaPrincipal.setScene(escena);
            ventanaPrincipal.setTitle("Juego JavaFX con FXML");
            ventanaPrincipal.show();

            // Si se carga la vista del juego, solicita el foco para el nodo raíz.
            if (nombreFXML.equals("Juego.fxml")) {
                root.requestFocus();
            }

        } catch (Exception e) {
            // Imprime la traza de la excepción en caso de error al cargar la vista.
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la ventana principal de la aplicación.
     *
     * @return La ventana principal de tipo {@link Stage}.
     */
    public static Stage getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    /**
     * Método principal de ejecución de la aplicación.
     * Este método lanza la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}