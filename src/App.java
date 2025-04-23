import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Modelo modelo = new Modelo();
        VistaInicio inicio = new VistaInicio();
        VistaJuego juego = new VistaJuego();
        VistaFin fin = new VistaFin();
        Controlador controlador = new Controlador(modelo, inicio, juego, fin);

        inicio.setOnJugar((nombreJugador) -> {
            controlador.ejecutar(nombreJugador);

            juego.setOnFin(() -> {
                fin.mostrarResultados();
            });
        });

        Scene scene = new Scene(inicio, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Juego con JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
