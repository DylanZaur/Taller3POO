package sistema;

import java.util.ArrayList;

import modelo.Hechizo;
import modelo.Mago;

public class Sistema {

    private ArrayList<Hechizo> hechizos;
    private ArrayList<Mago> magos;
    private ManejadorArchivos manejador;

    public Sistema() {
        this.hechizos = new ArrayList<>();
        this.magos = new ArrayList<>();
        this.manejador = new ManejadorArchivos();
    }

    public void cargarDatos() {
        this.hechizos = manejador.leerHechizos();
        this.magos = manejador.leerMagos(this.hechizos);
    }

    public ArrayList<Hechizo> getHechizos() {
        return hechizos;
    }

    public ArrayList<Mago> getMagos() {
        return magos;
    }
}
