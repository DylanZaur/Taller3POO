package modelo;

import java.util.ArrayList;

public class Mago implements Puntuable {

    private String nombre;
    private ArrayList<Hechizo> hechizos;

    public Mago(String nombre) {
        this.nombre = nombre;
        this.hechizos = new ArrayList<>();
    }

    public void agregarHechizo(Hechizo hechizo) {
        this.hechizos.add(hechizo);
    }

    @Override
    public double calcularPuntaje() {
        double total = 0;
        for (int i = 0; i < hechizos.size(); i++) {
            total += hechizos.get(i).calcularPuntaje();
        }
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Hechizo> getHechizos() {
        return hechizos;
    }

    public void setHechizos(ArrayList<Hechizo> hechizos) {
        this.hechizos = hechizos;
    }

    @Override
    public String toString() {
        return nombre + " - Puntaje total: " + calcularPuntaje();
    }
}
