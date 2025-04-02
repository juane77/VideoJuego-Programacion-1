/**
 * La clase Controlador coordina las operaciones entre el Modelo y la Vista.
 */
public class Controlador {
    private Modelo modelo;
    private Vista vista;

    /**
     * Constructor que inicializa el Modelo y la Vista.
     *
     * @param modelo El objeto Modelo.
     * @param vista  El objeto Vista.
     */
    public Controlador(Modelo modelo,Vista vista){
        this.modelo = modelo;
        this.vista = vista;
    }
}
