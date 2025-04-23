import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class VistaFin extends VBox {
    public void mostrarResultados() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Resultados");
        alert.setHeaderText(null);
        alert.setContentText("¡Juego terminado! Aquí están tus resultados.");
        alert.showAndWait();
    }
}
