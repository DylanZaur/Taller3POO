package sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Agua;
import modelo.Fuego;
import modelo.Hechizo;
import modelo.Mago;
import modelo.Planta;
import modelo.Tierra;


public class ManejadorArchivos {

    private static final String ARCHIVO_HECHIZOS = "Hechizos.txt";
    private static final String ARCHIVO_MAGOS = "Magos.txt";

    public ArrayList<Hechizo> leerHechizos() {
        ArrayList<Hechizo> hechizos = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(ARCHIVO_HECHIZOS));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(";");
                String nombre = partes[0].trim();
                String tipo = partes[1].trim();
                int dano = Integer.parseInt(partes[2].trim());

                // Segun el tipo se arma la subclase correspondiente
                if (tipo.equalsIgnoreCase("Fuego")) {
                    int duracionQuemadura = Integer.parseInt(partes[3].trim());
                    hechizos.add(new Fuego(nombre, dano, duracionQuemadura));

                } else if (tipo.equalsIgnoreCase("Tierra")) {
                    int mejoraDefensa = Integer.parseInt(partes[3].trim());
                    hechizos.add(new Tierra(nombre, dano, mejoraDefensa));

                } else if (tipo.equalsIgnoreCase("Planta")) {
                    String[] datos = partes[3].split(",");
                    int duracionStun = Integer.parseInt(datos[0].trim());
                    int cantPlantas = Integer.parseInt(datos[1].trim());
                    hechizos.add(new Planta(nombre, dano, duracionStun, cantPlantas));

                } else if (tipo.equalsIgnoreCase("Agua")) {
                    String[] datos = partes[3].split(",");
                    int cantidadHeal = Integer.parseInt(datos[0].trim());
                    int presionAgua = Integer.parseInt(datos[1].trim());
                    hechizos.add(new Agua(nombre, dano, cantidadHeal, presionAgua));
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo " + ARCHIVO_HECHIZOS);
        }

        return hechizos;
    }

    public ArrayList<Mago> leerMagos(ArrayList<Hechizo> hechizos) {
        ArrayList<Mago> magos = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(ARCHIVO_MAGOS), "UTF-8");

            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(";");
                String nombreMago = partes[0].trim();
                Mago mago = new Mago(nombreMago);

                // Los hechizos van separados por "|"
                String[] nombresHechizos = partes[1].split("\\|");
                for (String nombreHechizo : nombresHechizos) {
                    Hechizo encontrado = buscarHechizo(hechizos, nombreHechizo);
                    if (encontrado != null) {
                        mago.agregarHechizo(encontrado);
                    } else {
                        System.out.println("Aviso: el hechizo '" + nombreHechizo + "' del mago " + nombreMago + " no esta en Hechizos.txt");
                    }
                }
                magos.add(mago);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo " + ARCHIVO_MAGOS);
        }

        return magos;
    }

    private Hechizo buscarHechizo(ArrayList<Hechizo> hechizos, String nombre) {
        for (int i = 0; i < hechizos.size(); i++) {
            Hechizo h = hechizos.get(i);
            if (h.getNombre().equalsIgnoreCase(nombre)) {
                return h;
            }
        }
        return null;
    }
}
