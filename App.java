/**
 * Este programa verifica si existe un archivo de configuración. Si existe, se sale del programa;
 * si no, lo crea junto con tres directorios.
 * @author Fernando Romero y Juan Eloy Ortiz
 * @version 1.0
 */
public class App {
    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.ejecutar();
    }
}
