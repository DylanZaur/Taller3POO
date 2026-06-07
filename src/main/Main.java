// Integrante 1: DYLAN NICOLÁS ORDÓÑEZ MIRANDA 21.387.801-8
// Carrera: ICI
// Universidad Catolica del Norte
package main;

import sistema.Sistema;
import modelo.Mago;
import modelo.Hechizo;

public class Main {

    public static void main(String[] args) {
        System.out.println("Programa iniciado");

        Sistema sistema = new Sistema();
        sistema.cargarDatos();

        // Verificacion rapida de la carga de hechizos
        System.out.println("===== HECHIZOS CARGADOS =====");
        for (int i = 0; i < sistema.getHechizos().size(); i++) {
            System.out.println(sistema.getHechizos().get(i));
        }

        // Verificacion rapida de la carga de magos y su puntaje total
        System.out.println("===== MAGOS CARGADOS =====");
        for (int i = 0; i < sistema.getMagos().size(); i++) {
            Mago m = sistema.getMagos().get(i);
            System.out.println(m.getNombre() + " | hechizos: " + m.getHechizos().size() + " | puntaje total: " + m.calcularPuntaje());
        }
    }
}