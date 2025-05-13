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
import util.ReproductorMusica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para gestionar la lógica del juego.
 * Se encarga de inicializar el escenario, manejar los movimientos del jugador,
 * y gestionar las transiciones entre vistas al ganar o perder.
 * 
 * @version 1.0
 * @author Fernando y Juane
 */
public class JuegoController implements Initializable {

    /**
     * Panel principal donde se dibuja el escenario y el jugador.
     */
    @FXML
    private Pane panelJuego;

    /**
     * Tiempo de inicio del juego (en milisegundos).
     */
    private long tiempoInicio;

    /**
     * Tiempo de finalización del juego (en milisegundos).
     */
    private long tiempoFin;

    /**
     * Tamaño de cada celda del escenario (en píxeles).
     */
    private int TILE_SIZE = 40;

    // Imágenes para los diferentes elementos del escenario
    private Image imgSuelo = new Image(getClass().getResourceAsStream("/imagenes/suelo.png"));
    private Image imgObstaculo = new Image(getClass().getResourceAsStream("/imagenes/obstaculo.png"));
    private Image imgMeta = new Image(getClass().getResourceAsStream("/imagenes/meta.png"));
    private Image imgJugador = new Image(getClass().getResourceAsStream("/imagenes/personaje.png"));
    private Image imgMuro = new Image(getClass().getResourceAsStream("/imagenes/muro.png"));

    /**
     * Vista del jugador en el escenario.
     */
    private ImageView personajeView;

    /**
     * Escenario actual del juego.
     */
    private Escenario escenario;

    /**
     * Coordenada X del jugador en el escenario.
     */
    private int jugadorX;

    /**
     * Coordenada Y del jugador en el escenario.
     */
    private int jugadorY;

    /**
     * Inicializa el controlador del juego.
     * Configura el escenario, dibuja los elementos y establece los manejadores de eventos.
     * 
     * @param location URL de localización del recurso.
     * @param resources Recursos internacionales asociados al controlador.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 🎵 Reproducir música de fondo del juego
        ReproductorMusica.reproducir("/sonidos/musica_juego.mp3");

        // Registrar el tiempo de inicio del juego
        tiempoInicio = System.currentTimeMillis();

        // Cargar el escenario desde el archivo
        escenario = new Escenario("/" + Modelo.getRutaEscenario());

        // Ajustar el tamaño de las celdas según las dimensiones del escenario
        int filas = escenario.getFilas();
        int columnas = escenario.getColumnas();
        ajustarTileSize(filas, columnas);

        // Obtener la posición inicial del jugador
        int[] posJugador = escenario.getPosicionJugador();
        jugadorY = posJugador[0];
        jugadorX = posJugador[1];

        // Dibujar el escenario y configurar el jugador
        dibujarEscenario();

        personajeView = new ImageView(imgJugador);
        personajeView.setFitWidth(TILE_SIZE);
        personajeView.setFitHeight(TILE_SIZE);
        personajeView.setLayoutX(jugadorX * TILE_SIZE);
        personajeView.setLayoutY(jugadorY * TILE_SIZE);
        panelJuego.getChildren().add(personajeView);

        // Configurar el panel de juego
        panelJuego.setPrefSize(columnas * TILE_SIZE, filas * TILE_SIZE);
        panelJuego.setFocusTraversable(true);
        panelJuego.setOnKeyPressed(this::manejarMovimiento);
        panelJuego.requestFocus();
    }

    /**
     * Ajusta el tamaño de las celdas del escenario según las dimensiones máximas permitidas.
     * 
     * @param filas Número de filas del escenario.
     * @param columnas Número de columnas del escenario.
     */
    private void ajustarTileSize(int filas, int columnas) {
        int maxAncho = 800;
        int maxAlto = 600;
        TILE_SIZE = Math.min(maxAncho / columnas, maxAlto / filas);
    }

    /**
     * Registra que el jugador ha ganado el juego.
     * Calcula el tiempo total y guarda el resultado en la base de datos.
     */
    public void jugadorHaGanado() {
        tiempoFin = System.currentTimeMillis();
        double tiempoTotal = (tiempoFin - tiempoInicio) / 1000.0;

        String nombreJugador = Modelo.getJugador().getNombre();
        String escenarioActual = Modelo.getRutaEscenario();

        // Guardar el tiempo en la base de datos
        GestorBaseDatos.insertarTiempo(nombreJugador, tiempoTotal, escenarioActual);
    }

    /**
     * Dibuja el escenario en el panel de juego.
     * Representa cada celda del escenario con su imagen correspondiente.
     */
    private void dibujarEscenario() {
        String[][] mapa = escenario.getMatriz();

        for (int fila = 0; fila < escenario.getFilas(); fila++) {
            for (int col = 0; col < escenario.getColumnas(); col++) {
                ImageView tile = new ImageView();
                String tipo = mapa[fila][col];

                // Asignar la imagen correspondiente según el tipo de celda
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

    /**
     * Maneja el movimiento del jugador en el escenario.
     * Detecta colisiones y transiciones entre vistas al ganar o perder.
     * 
     * @param event Evento de teclado que indica la dirección del movimiento.
     */
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

        // Verificar límites del escenario
        if (nuevaX >= 0 && nuevaX < escenario.getColumnas() &&
            nuevaY >= 0 && nuevaY < escenario.getFilas()) {

            String destino = escenario.getMatriz()[nuevaY][nuevaX];

            // Si el jugador colisiona con un obstáculo, cargar la vista de muerte
            if (destino.equals("O")) {
                cargarVistaFinMuerte();
                return;
            }

            // Si el destino no es un muro, mover al jugador
            if (!destino.equals("M")) {
                jugadorX = nuevaX;
                jugadorY = nuevaY;
                personajeView.setLayoutX(jugadorX * TILE_SIZE);
                personajeView.setLayoutY(jugadorY * TILE_SIZE);

                // Si el jugador alcanza la meta, cargar la vista de victoria
                if (destino.equals("F")) {
                    jugadorHaGanado();
                    cargarVistaFin();
                }
            }
        }
    }

    /**
     * Carga la vista de finalización del juego (victoria).
     */
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

    /**
     * Carga la vista de finalización del juego (derrota).
     */
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