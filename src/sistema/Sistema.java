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

    // Carga primero los hechizos y luego los magos
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
        // Reemplazo en el catalogo
        for (int i = 0; i < hechizos.size(); i++) {
            if (hechizos.get(i).getNombre().equalsIgnoreCase(nombreViejo)) {
                hechizos.set(i, nuevo);
            }
        }
        // Reemplazo en cada mago que tenia ese hechizo
        for (int i = 0; i < magos.size(); i++) {
            ArrayList<Hechizo> hs = magos.get(i).getHechizos();
            for (int j = 0; j < hs.size(); j++) {
                if (hs.get(j).getNombre().equalsIgnoreCase(nombreViejo)) {
                    hs.set(j, nuevo);
                }
            }
        }
        // Se guardan ambos archivos porque pudo cambiar el nombre
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

    // Busca un hechizo del catalogo por su nombre
    public Hechizo buscarHechizo(String nombre) {
        for (int i = 0; i < hechizos.size(); i++) {
            if (hechizos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return hechizos.get(i);
            }
        }
        return null;
    }

    // Devuelve una copia de los hechizos ordenada de mayor a menor puntaje.
    public ArrayList<Hechizo> ordenarHechizosPorPuntaje() {
        ArrayList<Hechizo> copia = new ArrayList<>(hechizos);
        for (int i = 0; i < copia.size() - 1; i++) {
            int mayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).calcularPuntaje() > copia.get(mayor).calcularPuntaje()) {
                    mayor = j;
                }
            }
            // Intercambia el actual con el de mayor puntaje encontrado
            Hechizo temp = copia.get(i);
            copia.set(i, copia.get(mayor));
            copia.set(mayor, temp);
        }
        return copia;
    }

    // Devuelve una copia de los magos ordenada de mayor a menor puntaje total
    public ArrayList<Mago> ordenarMagosPorPuntaje() {
        ArrayList<Mago> copia = new ArrayList<>(magos);
        for (int i = 0; i < copia.size() - 1; i++) {
            int mayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).calcularPuntaje() > copia.get(mayor).calcularPuntaje()) {
                    mayor = j;
                }
            }
            Mago temp = copia.get(i);
            copia.set(i, copia.get(mayor));
            copia.set(mayor, temp);
        }
        return copia;
    }

    public ArrayList<Hechizo> getHechizos() {
        return hechizos;
    }

    public ArrayList<Mago> getMagos() {
        return magos;
    }
}