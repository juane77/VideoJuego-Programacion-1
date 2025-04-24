package src.controladores;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.App;
import src.modelos.Escenario;
import src.modelos.Modelo;
import src.modelos.Jugador;

public class JuegoController {

    @FXML
    private GridPane grid;

    private Escenario escenario;
    private final Modelo modelo = new Modelo();
    private final Jugador jugador = new Jugador("demo", "demo@gmail.com");

    @FXML
    public void initialize() {
        try {
            var codigo = modelo.cargarEscenario("escenario1");
            escenario = new Escenario("escenario1", codigo, jugador);
            renderizar();
            grid.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void manejarTecla(KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.W || code == KeyCode.A || code == KeyCode.S || code == KeyCode.D) {
            escenario.moverJugador(code.getName().charAt(0));
            renderizar();

            if (escenario.esJuegoTerminado()) {
                App.mostrarVista("Fin.fxml");
            }

        } else if (code == KeyCode.ESCAPE) {
            App.mostrarVista("Fin.fxml");
        }
    }

    private void renderizar() {
        grid.getChildren().clear();
        var mapa = escenario.obtenerEscenario();

        for (int y = 0; y < mapa.size(); y++) {
            String linea = mapa.get(y);
            for (int x = 0; x < linea.length(); x++) {
                char simbolo = linea.charAt(x);
                Rectangle rect = new Rectangle(18, 18);
                Text texto = new Text();

                switch (simbolo) {
                    case '|': rect.setFill(Color.GRAY); break;
                    case 'O': rect.setFill(Color.CRIMSON); texto.setText("X"); break;
                    case '@': rect.setFill(Color.LIGHTBLUE); texto.setText("@"); break;
                    case 'F': rect.setFill(Color.LIGHTGREEN); texto.setText("F"); break;
                    default: rect.setFill(Color.BEIGE); break;
                }

                texto.setFont(Font.font("Consolas", 14));
                StackPane celda = new StackPane(rect, texto);
                grid.add(celda, x, y);
            }
        }
        grid.requestFocus();
    }
}
