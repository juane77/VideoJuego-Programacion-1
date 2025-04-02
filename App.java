/**
 * Este programa verifica si existe un archivo de configuracion, si existe se sale del programa, si no existe 
 * te lo crea ademas tambien te crea tres directorios.
 * @author Fernando Romero y Juan Eloy Ortiz
 * @version 1.0
 */
public class App {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
    }
}
