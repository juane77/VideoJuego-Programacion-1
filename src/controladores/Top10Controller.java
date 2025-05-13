package controladores;

import base_datos.GestorBaseDatos;
import base_datos.TiempoJugador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

/**
 * Controlador para la vista del Top 10 de jugadores.
 * Muestra los mejores tiempos de los jugadores por escenario y permite al usuario
 * navegar entre diferentes escenarios o volver al menú principal.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class Top10Controller {

    /**
     * Tabla que muestra los tiempos de los jugadores.
     */
    @FXML private TableView<TiempoJugador> tablaTiempos;

    /**
     * Columna de la tabla que muestra los nombres de los jugadores.
     */
    @FXML private TableColumn<TiempoJugador, String> columnaNombre;

    /**
     * Columna de la tabla que muestra los tiempos de los jugadores.
     */
    @FXML private TableColumn<TiempoJugador, String> columnaTiempo;

    /**
     * Método de inicialización del controlador.
     * Configura las columnas de la tabla y carga el Top 10 del escenario 1 por defecto.
     */
    @FXML
    public void initialize() {
        // Configurar la columna de nombres con los datos del jugador
        columnaNombre.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNombreJugador())
        );

        // Configurar la columna de tiempos con los datos del tiempo formateados
        columnaTiempo.setCellValueFactory(cellData -> {
            double tiempo = cellData.getValue().getTiempoSegundos();
            return new SimpleStringProperty(String.format("%.3f s", tiempo));
        });

        // Cargar el Top 10 del escenario 1 por defecto
        mostrarTop("escenarios/escenario1.txt");
    }

    /**
     * Muestra el Top 10 de jugadores para un escenario específico.
     * 
     * @param escenario Ruta del archivo del escenario.
     */
    private void mostrarTop(String escenario) {
        // Obtener la lista de los 10 mejores tiempos desde la base de datos
        List<TiempoJugador> lista = GestorBaseDatos.obtenerTop10PorEscenario(escenario);

        // Convertir la lista en un ObservableList para la tabla
        ObservableList<TiempoJugador> datos = FXCollections.observableArrayList(lista);

        // Establecer los datos en la tabla
        tablaTiempos.setItems(datos);
    }

    /**
     * Muestra el Top 10 del escenario 1.
     */
    @FXML
    public void mostrarTopEscenario1() {
        mostrarTop("escenarios/escenario1.txt");
    }

    /**
     * Muestra el Top 10 del escenario 2.
     */
    @FXML
    public void mostrarTopEscenario2() {
        mostrarTop("escenarios/escenario2.txt");
    }

    /**
     * Muestra el Top 10 del escenario 3.
     */
    @FXML
    public void mostrarTopEscenario3() {
        mostrarTop("escenarios/escenario3.txt");
    }

    /**
     * Muestra el Top 10 del escenario 4.
     */
    @FXML
    public void mostrarTopEscenario4() {
        mostrarTop("escenarios/escenario4.txt");
    }

    /**
     * Vuelve a la vista del Splash (pantalla inicial).
     * 
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    public void volverAlSplash() {
        try {
            // Cargar la vista del Splash
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Splash.fxml"));
            Parent root = loader.load();

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) tablaTiempos.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}