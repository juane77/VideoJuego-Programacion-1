package src.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import src.modelos.Escenario;

public class VistaJuego extends StackPane {

    private Escenario escenario;
    private GridPane grid;
    private Runnable alFinalizar;

    public VistaJuego(Escenario escenario, Runnable alFinalizar) {
        this.escenario = escenario;
        this.alFinalizar = alFinalizar;
        this.grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        this.getChildren().add(grid);
        renderizar();

        // Captura de teclas
        this.setOnKeyPressed(this::moverJugador);
    }

    private void renderizar() {
        grid.getChildren().clear();
        var mapa = escenario.obtenerEscenario();

        for (int y = 0; y < mapa.size(); y++) {
            String linea = mapa.get(y);
            for (int x = 0; x < linea.length(); x++) {
                char simbolo = linea.charAt(x);
                Rectangle rect = new Rectangle(15, 15);
                Text texto = new Text(String.valueOf(simbolo));
                switch (simbolo) {
                    case '|':
                        rect.setFill(Color.GRAY);
                        break;
                    case 'O':
                        rect.setFill(Color.DARKRED);
                        break;
                    case '@':
                        rect.setFill(Color.LIGHTBLUE);
                        break;
                    default:
                        rect.setFill(Color.BEIGE);
                }
                StackPane celda = new StackPane(rect, texto);
                grid.add(celda, x, y);
            }
        }
    }

    private void moverJugador(KeyEvent e) {
        KeyCode code = e.getCode();

        switch (code) {
            case W, A, S, D -> {
                escenario.moverJugador(code.getName().charAt(0));
                renderizar();
            }
            case ESCAPE -> alFinalizar.run();
        }
    }
}
