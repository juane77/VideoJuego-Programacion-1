package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VistaFinController {

    @FXML
    private Button salirButton;

    @FXML
    public void initialize() {
        salirButton.setOnAction(event -> {
            Stage stage = (Stage) salirButton.getScene().getWindow();
            stage.close();
        });
    }
}
