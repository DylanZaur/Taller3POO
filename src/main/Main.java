// DYLAN NICOLÁS ORDÓÑEZ MIRANDA 21.387.801-8 ICI
package main;
 
import sistema.Sistema;
import modelo.Mago;
import modelo.Hechizo;
 
public class Main {
 
    public static void main(String[] args) {
        System.out.println("Programa iniciado");
 
        // Crea el sistema y carga magos y hechizos desde los .txt
        Sistema sistema = new Sistema();
        sistema.cargarDatos();
 
        // Muestra todos los hechizos cargados
        System.out.println("===== HECHIZOS CARGADOS =====");
        for (int i = 0; i < sistema.getHechizos().size(); i++) {
            System.out.println(sistema.getHechizos().get(i));
        }
 
        // Muestra cada mago con su cantidad de hechizos y su puntaje total
        System.out.println("===== MAGOS CARGADOS =====");
        for (int i = 0; i < sistema.getMagos().size(); i++) {
            Mago m = sistema.getMagos().get(i);
            System.out.println(m.getNombre() + " | hechizos: " + m.getHechizos().size() + " | puntaje total: " + m.calcularPuntaje());
        }
    }
}
