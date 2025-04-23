import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import java.util.List;
import java.lang.Runnable; // Corrección aquí

public class VistaJuego extends VBox {
    private Runnable onFin;

    public VistaJuego() {
        // Inicialización si es necesario
    }

    public void mostrarEscenario(List<String> escenario) {
        StringBuilder sb = new StringBuilder();
        for (String linea : escenario) {
            sb.append(linea).append("\n");
        }
        mostrarMensaje(sb.toString());
    }

    public char pedirDireccion() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Movimiento");
        dialog.setHeaderText(null);
        dialog.setContentText("Elige una dirección (W/A/S/D) y presiona Enter:");
        String input = dialog.showAndWait().orElse("");
        return input.isEmpty() ? ' ' : input.charAt(0);
    }

    public void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setOnFin(Runnable onFin) {
        this.onFin = onFin;
    }

    public void terminarJuego() {
        if (onFin != null) {
            onFin.run();
        }
    }
}
