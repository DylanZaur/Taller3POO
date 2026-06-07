// DYLAN NICOLÁS ORDÓÑEZ MIRANDA
// 21.387.801-8
// ICI
package main;

import java.util.ArrayList;
import java.util.Scanner;

import sistema.Sistema;
import modelo.Agua;
import modelo.Fuego;
import modelo.Hechizo;
import modelo.Mago;
import modelo.Planta;
import modelo.Tierra;

public class Main {

    public static void main(String[] args) {
        // Crea el sistema y carga magos y hechizos desde los .txt
        Sistema sistema = new Sistema();
        sistema.cargarDatos();

        Scanner sc = new Scanner(System.in);

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("===== MENU PRINCIPAL =====");
            System.out.println("1. Panel Administrador");
            System.out.println("2. Panel Analista");
            System.out.println("0. Salir");
            System.out.print("> ");
            opcion = leerEntero(sc);

            if (opcion == 1) {
                menuAdministrador(sc, sistema);
            } else if (opcion == 2) {
                menuAnalista(sc, sistema);
            } else if (opcion == 0) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opcion no valida.");
            }
        }

        sc.close();
    }

    private static void menuAdministrador(Scanner sc, Sistema sistema) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("----- PANEL ADMINISTRADOR -----");
            System.out.println("1. Agregar Mago");
            System.out.println("2. Modificar Mago");
            System.out.println("3. Eliminar Mago");
            System.out.println("4. Agregar Hechizo");
            System.out.println("5. Modificar Hechizo");
            System.out.println("6. Eliminar Hechizo");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = leerEntero(sc);

            if (opcion == 1) {
                Mago nuevo = crearMagoDesdeConsola(sc, sistema);
                sistema.agregarMago(nuevo);
                System.out.println("Mago agregado.");

            } else if (opcion == 2) {
                System.out.print("Nombre del mago a modificar: ");
                String viejo = sc.nextLine();
                System.out.println("Ingresa los datos nuevos del mago:");
                Mago nuevo = crearMagoDesdeConsola(sc, sistema);
                sistema.modificarMago(viejo, nuevo);
                System.out.println("Mago modificado.");

            } else if (opcion == 3) {
                System.out.print("Nombre del mago a eliminar: ");
                String nombre = sc.nextLine();
                sistema.eliminarMago(nombre);
                System.out.println("Mago eliminado.");

            } else if (opcion == 4) {
                Hechizo nuevo = crearHechizoDesdeConsola(sc);
                if (nuevo != null) {
                    sistema.agregarHechizo(nuevo);
                    System.out.println("Hechizo agregado.");
                }

            } else if (opcion == 5) {
                System.out.print("Nombre del hechizo a modificar: ");
                String viejo = sc.nextLine().trim();
                System.out.println("Ingresa los datos nuevos del hechizo:");
                Hechizo nuevo = crearHechizoDesdeConsola(sc);
                if (nuevo != null) {
                    sistema.modificarHechizo(viejo, nuevo);
                    System.out.println("Hechizo modificado.");
                }

            } else if (opcion == 6) {
                System.out.print("Nombre del hechizo a eliminar: ");
                String nombre = sc.nextLine();
                sistema.eliminarHechizo(nombre);
                System.out.println("Hechizo eliminado.");

            } else if (opcion == 0) {
                // vuelve al menu principal

            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }

    // Submenu del analista con los reportes y rankings
    private static void menuAnalista(Scanner sc, Sistema sistema) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("----- PANEL ANALISTA -----");
            System.out.println("1. Top 10 Mejores Hechizos");
            System.out.println("2. Top 3 Mejores Magos");
            System.out.println("3. Mostrar todos los Hechizos");
            System.out.println("4. Mostrar todos los Magos");
            System.out.println("5. Mostrar todos los Hechizos con su puntuacion");
            System.out.println("6. Mostrar todos los Magos con su puntuacion");
            System.out.println("0. Volver");
            System.out.print("> ");
            opcion = leerEntero(sc);

            if (opcion == 1) {
                // Ordena los hechizos y muestra hasta los 10 primeros
                ArrayList<Hechizo> ordenados = sistema.ordenarHechizosPorPuntaje();
                System.out.println("TOP 10 HECHIZOS:");
                for (int i = 0; i < ordenados.size() && i < 10; i++) {
                    System.out.println((i + 1) + ". " + ordenados.get(i));
                }

            } else if (opcion == 2) {
                // Ordena los magos y muestra hasta los 3 primeros
                ArrayList<Mago> ordenados = sistema.ordenarMagosPorPuntaje();
                System.out.println("TOP 3 MAGOS:");
                for (int i = 0; i < ordenados.size() && i < 3; i++) {
                    System.out.println((i + 1) + ". " + ordenados.get(i));
                }

            } else if (opcion == 3) {
                System.out.println("HECHIZOS:");
                for (int i = 0; i < sistema.getHechizos().size(); i++) {
                    Hechizo h = sistema.getHechizos().get(i);
                    System.out.println(h.getNombre() + " (" + h.getTipo() + ")");
                }

            } else if (opcion == 4) {
                System.out.println("MAGOS:");
                for (int i = 0; i < sistema.getMagos().size(); i++) {
                    System.out.println(sistema.getMagos().get(i).getNombre());
                }

            } else if (opcion == 5) {
                System.out.println("HECHIZOS CON PUNTUACION:");
                for (int i = 0; i < sistema.getHechizos().size(); i++) {
                    System.out.println(sistema.getHechizos().get(i));
                }

            } else if (opcion == 6) {
                System.out.println("MAGOS CON PUNTUACION:");
                for (int i = 0; i < sistema.getMagos().size(); i++) {
                    System.out.println(sistema.getMagos().get(i));
                }

            } else if (opcion == 0) {
                // vuelve al menu principal

            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }

    // Pide al usuario los datos y crea el hechizo de la subclase correcta
    private static Hechizo crearHechizoDesdeConsola(Scanner sc) {
        System.out.print("Nombre del hechizo: ");
        String nombre = sc.nextLine();
        System.out.print("Tipo (Fuego/Tierra/Planta/Agua): ");
        String tipo = sc.nextLine();
        System.out.print("Dano: ");
        int dano = leerEntero(sc);

        if (tipo.equalsIgnoreCase("Fuego")) {
            System.out.print("Duracion de la quemadura: ");
            int dur = leerEntero(sc);
            return new Fuego(nombre, dano, dur);

        } else if (tipo.equalsIgnoreCase("Tierra")) {
            System.out.print("Mejora de defensa: ");
            int def = leerEntero(sc);
            return new Tierra(nombre, dano, def);

        } else if (tipo.equalsIgnoreCase("Planta")) {
            System.out.print("Duracion del stun: ");
            int stun = leerEntero(sc);
            System.out.print("Cantidad de plantas: ");
            int cant = leerEntero(sc);
            return new Planta(nombre, dano, stun, cant);

        } else if (tipo.equalsIgnoreCase("Agua")) {
            System.out.print("Cantidad de heal: ");
            int heal = leerEntero(sc);
            System.out.print("Presion del agua: ");
            int presion = leerEntero(sc);
            return new Agua(nombre, dano, heal, presion);

        } else {
            System.out.println("Tipo no valido, no se creo el hechizo.");
            return null;
        }
    }

    // Pide el nombre del mago y sus hechizos
    private static Mago crearMagoDesdeConsola(Scanner sc, Sistema sistema) {
        System.out.print("Nombre del mago: ");
        String nombre = sc.nextLine();
        Mago mago = new Mago(nombre);

        System.out.println("Escribe los hechizos uno por uno (escribe 'fin' para terminar):");
        boolean seguir = true;
        while (seguir) {
            System.out.print("Hechizo: ");
            String nombreHechizo = sc.nextLine();
            if (nombreHechizo.equalsIgnoreCase("fin")) {
                seguir = false;
            } else {
                Hechizo h = sistema.buscarHechizo(nombreHechizo);
                if (h != null) {
                    mago.agregarHechizo(h);
                } else {
                    System.out.println("Ese hechizo no existe en el catalogo, intenta con otro.");
                }
            }
        }
        return mago;
    }

    // Lee una linea y la convierte a entero; si el usuario escribe algo
    // que no es numero, avisa y vuelve a pedir, asi el programa no se cae.
    private static int leerEntero(Scanner sc) {
        boolean valido = false;
        int numero = 0;
        while (!valido) {
            try {
                numero = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Eso no es un numero, intenta de nuevo: ");
            }
        }
        return numero;
    }
}
