import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;

public class VistaInicio extends VBox {
    private Consumer<String> onJugar;

    public VistaInicio() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nombre de Jugador");
        dialog.setHeaderText(null);
        dialog.setContentText("Introduce tu nombre de usuario:");
        dialog.showAndWait().ifPresent(nombre -> {
            if (onJugar != null) {
                onJugar.accept(nombre);
            }
        });
    }

    public void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public String pedirGmail() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Gmail");
        dialog.setHeaderText(null);
        dialog.setContentText("Introduce tu Gmail:");
        return dialog.showAndWait().orElse("");
    }

    public void setOnJugar(Consumer<String> onJugar) {
        this.onJugar = onJugar;
    }
}
