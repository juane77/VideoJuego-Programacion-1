package controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import modelos.Escenario;
import base_datos.GestorBaseDatos;
import src.modelos.Modelo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JuegoController implements Initializable {

    @FXML
    private Pane panelJuego;

    private long tiempoInicio;
    private long tiempoFin;

    private int TILE_SIZE = 40;

    private Image imgSuelo = new Image(getClass().getResourceAsStream("/imagenes/suelo.png"));
    private Image imgObstaculo = new Image(getClass().getResourceAsStream("/imagenes/obstaculo.png"));
    private Image imgMeta = new Image(getClass().getResourceAsStream("/imagenes/meta.png"));
    private Image imgJugador = new Image(getClass().getResourceAsStream("/imagenes/personaje.png"));
    private Image imgMuro = new Image(getClass().getResourceAsStream("/imagenes/muro.png"));

    private ImageView personajeView;
    private Escenario escenario;
    private int jugadorX;
    private int jugadorY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tiempoInicio = System.currentTimeMillis();

        escenario = new Escenario("/" + Modelo.getRutaEscenario());

        int filas = escenario.getFilas();
        int columnas = escenario.getColumnas();

        ajustarTileSize(filas, columnas);

        int[] posJugador = escenario.getPosicionJugador();
        jugadorY = posJugador[0];
        jugadorX = posJugador[1];

        dibujarEscenario();

        personajeView = new ImageView(imgJugador);
        personajeView.setFitWidth(TILE_SIZE);
        personajeView.setFitHeight(TILE_SIZE);
        personajeView.setLayoutX(jugadorX * TILE_SIZE);
        personajeView.setLayoutY(jugadorY * TILE_SIZE);
        panelJuego.getChildren().add(personajeView);

        panelJuego.setPrefSize(columnas * TILE_SIZE, filas * TILE_SIZE);
        panelJuego.setFocusTraversable(true);
        panelJuego.setOnKeyPressed(this::manejarMovimiento);
        panelJuego.requestFocus();
    }

    private void ajustarTileSize(int filas, int columnas) {
        int maxAncho = 800;
        int maxAlto = 600;
        TILE_SIZE = Math.min(maxAncho / columnas, maxAlto / filas);
    }

    public void jugadorHaGanado() {
        tiempoFin = System.currentTimeMillis();
        long tiempoTotal = (tiempoFin - tiempoInicio) / 1000;

        String nombreJugador = Modelo.getJugador().getNombre();
        GestorBaseDatos.insertarTiempo(nombreJugador, tiempoTotal);
    }

    private void dibujarEscenario() {
        String[][] mapa = escenario.getMatriz();

        for (int fila = 0; fila < escenario.getFilas(); fila++) {
            for (int col = 0; col < escenario.getColumnas(); col++) {
                ImageView tile = new ImageView();
                String tipo = mapa[fila][col];

                switch (tipo) {
                    case "M" -> tile.setImage(imgMuro);
                    case "O" -> tile.setImage(imgObstaculo);
                    case "F" -> tile.setImage(imgMeta);
                    case "J" -> tile.setImage(imgSuelo);
                    default -> tile.setImage(imgSuelo);
                }

                tile.setFitWidth(TILE_SIZE);
                tile.setFitHeight(TILE_SIZE);
                tile.setLayoutX(col * TILE_SIZE);
                tile.setLayoutY(fila * TILE_SIZE);
                panelJuego.getChildren().add(tile);
            }
        }
    }

    private void manejarMovimiento(KeyEvent event) {
        int dx = 0, dy = 0;
        switch (event.getCode()) {
            case UP, W -> dy = -1;
            case DOWN, S -> dy = 1;
            case LEFT, A -> dx = -1;
            case RIGHT, D -> dx = 1;
            default -> { return; }
        }

        int nuevaX = jugadorX + dx;
        int nuevaY = jugadorY + dy;

        if (nuevaX >= 0 && nuevaX < escenario.getColumnas() &&
            nuevaY >= 0 && nuevaY < escenario.getFilas()) {

            String destino = escenario.getMatriz()[nuevaY][nuevaX];

            if (destino.equals("O")) {
                cargarVistaFinMuerte();
                return;
            }

            if (!destino.equals("M")) {
                jugadorX = nuevaX;
                jugadorY = nuevaY;
                personajeView.setLayoutX(jugadorX * TILE_SIZE);
                personajeView.setLayoutY(jugadorY * TILE_SIZE);

                if (destino.equals("F")) {
                    jugadorHaGanado();
                    cargarVistaFin();
                }
            }
        }
    }

    private void cargarVistaFin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Fin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) panelJuego.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVistaFinMuerte() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/FinMuerte.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) panelJuego.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
