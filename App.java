import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App{
    public static void main(String[] args) {
        Path rutaArchivoConfiguracion = Paths.get("configuracion/configuracion.txt");
        if(Files.exists(rutaArchivoConfiguracion)){
            System.out.println("El archivo de configuracion ya existe");
        }else{
            try {
                Path rutaDirectorioEscenarios = Paths.get("configuracion/escenarios");
                Path rutaDirectorioJugadores = Paths.get("configuracion/jugadores");
                Path rutaDirectorioPartidas = Paths.get("configuracion/partidas");

                Files.createDirectories(rutaDirectorioEscenarios);
                Files.createDirectories(rutaDirectorioJugadores);
                Files.createDirectories(rutaDirectorioPartidas);

                Files.createFile(rutaArchivoConfiguracion);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        
    }
}