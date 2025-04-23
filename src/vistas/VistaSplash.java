import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VistaSplash extends VBox {
    public VistaSplash() {
        Text titulo = new Text("Bienvenido al Juego");
        Button inicioButton = new Button("Comenzar");

        inicioButton.setOnAction(e -> {
            if (onInicio != null) {
                onInicio.run();
            }
        });

        getChildren().addAll(titulo, inicioButton);
    }

    private Runnable onInicio;

    public void setOnInicio(Runnable onInicio) {
        this.onInicio = onInicio;
    }
}
