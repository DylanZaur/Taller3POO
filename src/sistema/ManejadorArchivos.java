package sistema;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Agua;
import modelo.Fuego;
import modelo.Hechizo;
import modelo.Mago;
import modelo.Planta;
import modelo.Tierra;

// Se encarga de leer y escribir los archivos .txt.
public class ManejadorArchivos {

    private static final String ARCHIVO_HECHIZOS = "Hechizos.txt";
    private static final String ARCHIVO_MAGOS = "Magos.txt";

    // Lee Hechizos.txt y crea la subclase correcta segun el tipo.
    public ArrayList<Hechizo> leerHechizos() {
        ArrayList<Hechizo> hechizos = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(ARCHIVO_HECHIZOS), "UTF-8");

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(";");
                String nombre = partes[0];
                String tipo = partes[1];
                int dano = Integer.parseInt(partes[2]);

                // Segun el tipo se arma la subclase correspondiente
                if (tipo.equalsIgnoreCase("Fuego")) {
                    int duracionQuemadura = Integer.parseInt(partes[3]);
                    hechizos.add(new Fuego(nombre, dano, duracionQuemadura));

                } else if (tipo.equalsIgnoreCase("Tierra")) {
                    int mejoraDefensa = Integer.parseInt(partes[3]);
                    hechizos.add(new Tierra(nombre, dano, mejoraDefensa));

                } else if (tipo.equalsIgnoreCase("Planta")) {
                    String[] datos = partes[3].split(",");
                    int duracionStun = Integer.parseInt(datos[0]);
                    int cantPlantas = Integer.parseInt(datos[1]);
                    hechizos.add(new Planta(nombre, dano, duracionStun, cantPlantas));

                } else if (tipo.equalsIgnoreCase("Agua")) {
                    String[] datos = partes[3].split(",");
                    int cantidadHeal = Integer.parseInt(datos[0]);
                    int presionAgua = Integer.parseInt(datos[1]);
                    hechizos.add(new Agua(nombre, dano, cantidadHeal, presionAgua));
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo " + ARCHIVO_HECHIZOS);
        }

        return hechizos;
    }

    // Lee Magos.txt, crea cada mago y le asocia los objetos hechizo
    // buscandolos en la lista ya cargada.
    public ArrayList<Mago> leerMagos(ArrayList<Hechizo> hechizos) {
        ArrayList<Mago> magos = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(ARCHIVO_MAGOS), "UTF-8");

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(";");
                String nombreMago = partes[0];
                Mago mago = new Mago(nombreMago);

                String[] nombresHechizos = partes[1].split("\\|");
                for (int i = 0; i < nombresHechizos.length; i++) {
                    Hechizo encontrado = buscarHechizo(hechizos, nombresHechizos[i]);
                    if (encontrado != null) {
                        mago.agregarHechizo(encontrado);
                    } else {
                        System.out.println("Aviso: el hechizo '" + nombresHechizos[i] + "' del mago " + nombreMago + " no esta en Hechizos.txt");
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

    // Sobrescribe Hechizos.txt respetando el formato original de cada tipo
    public void guardarHechizos(ArrayList<Hechizo> hechizos) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HECHIZOS));

            for (int i = 0; i < hechizos.size(); i++) {
                Hechizo h = hechizos.get(i);
                String linea = "";

                // Segun la subclase se arma la linea con sus parametros propios
                if (h instanceof Fuego) {
                    Fuego f = (Fuego) h;
                    linea = f.getNombre() + ";Fuego;" + f.getDano() + ";" + f.getDuracionQuemadura();

                } else if (h instanceof Tierra) {
                    Tierra t = (Tierra) h;
                    linea = t.getNombre() + ";Tierra;" + t.getDano() + ";" + t.getMejoraDefensa();

                } else if (h instanceof Planta) {
                    Planta p = (Planta) h;
                    linea = p.getNombre() + ";Planta;" + p.getDano() + ";" + p.getDuracionStun() + "," + p.getCantPlantas();

                } else if (h instanceof Agua) {
                    Agua a = (Agua) h;
                    linea = a.getNombre() + ";Agua;" + a.getDano() + ";" + a.getCantidadHeal() + "," + a.getPresionAgua();
                }

                bw.write(linea);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error al guardar " + ARCHIVO_HECHIZOS);
        }
    }

    // Sobrescribe Magos.txt
    public void guardarMagos(ArrayList<Mago> magos) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_MAGOS));

            for (int i = 0; i < magos.size(); i++) {
                Mago m = magos.get(i);
                String linea = m.getNombre() + ";";

                ArrayList<Hechizo> hs = m.getHechizos();
                for (int j = 0; j < hs.size(); j++) {
                    linea += hs.get(j).getNombre();
                    if (j < hs.size() - 1) {
                        linea += "|";
                    }
                }

                bw.write(linea);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error al guardar " + ARCHIVO_MAGOS);
        }
    }

    // Busca un hechizo por su nombre dentro de la lista; devuelve null si no esta
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