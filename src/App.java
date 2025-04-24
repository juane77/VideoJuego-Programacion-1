package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.vistas.VistaSplash;
import src.vistas.VistaInicio;
import src.vistas.VistaJuego;
import src.vistas.VistaFin;
import src.modelos.Modelo;
import src.modelos.Jugador;
import src.modelos.Escenario;

public class App extends Application {

    private Stage ventana;
    private Modelo modelo;
    private Jugador jugador;

    @Override
    public void start(Stage stage) {
        this.ventana = stage;
        this.modelo = new Modelo();
        mostrarSplash();
    }

    private void mostrarSplash() {
        VistaSplash splash = new VistaSplash(() -> mostrarInicio());
        Scene escena = new Scene(splash, 800, 600);
        ventana.setScene(escena);
        ventana.setTitle("Juego JavaFX sin FXML");
        ventana.show();
        splash.requestFocus();
    }

    private void mostrarInicio() {
        VistaInicio inicio = new VistaInicio(modelo, jugador -> {
            this.jugador = jugador;
            mostrarJuego();
        });
        Scene escena = new Scene(inicio, 800, 600);
        ventana.setScene(escena);
    }

    private void mostrarJuego() {
        try {
            var codigo = modelo.cargarEscenario("escenario1");
            Escenario escenario = new Escenario("escenario1", codigo, jugador);
            VistaJuego juego = new VistaJuego(escenario, this::mostrarFin);
            Scene escena = new Scene(juego, 800, 600);
            ventana.setScene(escena);
            juego.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarFin() {
        VistaFin fin = new VistaFin(this::mostrarInicio);
        Scene escena = new Scene(fin, 800, 600);
        ventana.setScene(escena);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
