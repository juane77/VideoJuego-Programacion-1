package controladores;

import base_datos.GestorBaseDatos;
import base_datos.TiempoJugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;

public class Top10Controller {

    @FXML
    private TableView<TiempoJugador> tablaTiempos;

    @FXML
    private TableColumn<TiempoJugador, String> columnaNombre;

    @FXML
    private TableColumn<TiempoJugador, Long> columnaTiempo;

    @FXML
    public void initialize() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreJugador"));
        columnaTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoSegundos"));

        ObservableList<TiempoJugador> datos = FXCollections.observableArrayList(GestorBaseDatos.obtenerTop10());
        tablaTiempos.setItems(datos);
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
