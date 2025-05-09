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

public class Top10Controller {

    @FXML private TableView<TiempoJugador> tablaTiempos;
    @FXML private TableColumn<TiempoJugador, String> columnaNombre;
    @FXML private TableColumn<TiempoJugador, String> columnaTiempo;

    @FXML
    public void initialize() {
        columnaNombre.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNombreJugador())
        );

        columnaTiempo.setCellValueFactory(cellData -> {
            double tiempo = cellData.getValue().getTiempoSegundos();
            return new SimpleStringProperty(String.format("%.3f s", tiempo));
        });

        // Cargar escenario 1 por defecto
        mostrarTop("escenarios/escenario1.txt");
    }

    private void mostrarTop(String escenario) {
        List<TiempoJugador> lista = GestorBaseDatos.obtenerTop10PorEscenario(escenario);
        ObservableList<TiempoJugador> datos = FXCollections.observableArrayList(lista);
        tablaTiempos.setItems(datos);
    }

    @FXML public void mostrarTopEscenario1() {
        mostrarTop("escenarios/escenario1.txt");
    }

    @FXML public void mostrarTopEscenario2() {
        mostrarTop("escenarios/escenario2.txt");
    }

    @FXML public void mostrarTopEscenario3() {
        mostrarTop("escenarios/escenario3.txt");
    }

    @FXML public void mostrarTopEscenario4() {
        mostrarTop("escenarios/escenario4.txt");
    }

    @FXML
    public void volverAlSplash() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Splash.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tablaTiempos.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
