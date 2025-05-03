// filepath: c:\Users\ferna\Programacion\Programación 3 Trimestre\VideojuegoProgramacion\VideoJuego-Programacion-1\src\controladores\Top10Controller.java
package controladores;

import base_datos.GestorBaseDatos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import base_datos.TiempoJugador;

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
}