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

    // Carga primero los hechizos y luego los magos (que necesitan los hechizos ya cargados)
    public void cargarDatos() {
        this.hechizos = manejador.leerHechizos();
        this.magos = manejador.leerMagos(this.hechizos);
    }

    // Agrega un hechizo al catalogo y guarda el archivo
    public void agregarHechizo(Hechizo h) {
        hechizos.add(h);
        manejador.guardarHechizos(hechizos);
    }

    public void modificarHechizo(String nombreViejo, Hechizo nuevo) {

        for (int i = 0; i < hechizos.size(); i++) {
            if (hechizos.get(i).getNombre().equalsIgnoreCase(nombreViejo)) {
                hechizos.set(i, nuevo);
            }
        }

        for (int i = 0; i < magos.size(); i++) {
            ArrayList<Hechizo> hs = magos.get(i).getHechizos();
            for (int j = 0; j < hs.size(); j++) {
                if (hs.get(j).getNombre().equalsIgnoreCase(nombreViejo)) {
                    hs.set(j, nuevo);
                }
            }
        }
        manejador.guardarHechizos(hechizos);
        manejador.guardarMagos(magos);
    }

    // Elimina un hechizo del catalogo y de todos los magos que lo tenian
    public void eliminarHechizo(String nombre) {
        for (int i = 0; i < hechizos.size(); i++) {
            if (hechizos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                hechizos.remove(i);
                i--;
            }
        }

        for (int i = 0; i < magos.size(); i++) {
            ArrayList<Hechizo> hs = magos.get(i).getHechizos();
            for (int j = 0; j < hs.size(); j++) {
                if (hs.get(j).getNombre().equalsIgnoreCase(nombre)) {
                    hs.remove(j);
                    j--;
                }
            }
        }
        manejador.guardarHechizos(hechizos);
        manejador.guardarMagos(magos);
    }

    // Agrega un mago a la lista y guarda el archivo
    public void agregarMago(Mago m) {
        magos.add(m);
        manejador.guardarMagos(magos);
    }

    // Reemplaza un mago (por nombre) por uno nuevo y guarda el archivo
    public void modificarMago(String nombreViejo, Mago nuevo) {
        for (int i = 0; i < magos.size(); i++) {
            if (magos.get(i).getNombre().equalsIgnoreCase(nombreViejo)) {
                magos.set(i, nuevo);
            }
        }
        manejador.guardarMagos(magos);
    }

    // Elimina un mago por su nombre y guarda el archivo
    public void eliminarMago(String nombre) {
        for (int i = 0; i < magos.size(); i++) {
            if (magos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                magos.remove(i);
                i--;
            }
        }
        manejador.guardarMagos(magos);
    }

    // Busca un hechizo del catalogo por su nombre; sirve para armar magos nuevos
    public Hechizo buscarHechizo(String nombre) {
        for (int i = 0; i < hechizos.size(); i++) {
            if (hechizos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return hechizos.get(i);
            }
        }
        return null;
    }

    public ArrayList<Hechizo> getHechizos() {
        return hechizos;
    }

    public ArrayList<Mago> getMagos() {
        return magos;
    }
}