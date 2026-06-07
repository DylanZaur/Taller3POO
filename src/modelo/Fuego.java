package modelo;

public class Fuego extends Hechizo {

    private int duracionQuemadura;

    public Fuego(String nombre, int dano, int duracionQuemadura) {
        super(nombre, "Fuego", dano);
        this.duracionQuemadura = duracionQuemadura;
    }

    @Override
    public double calcularPuntaje() {
        return getDano() * duracionQuemadura;
    }

    public int getDuracionQuemadura() {
        return duracionQuemadura;
    }

    public void setDuracionQuemadura(int duracionQuemadura) {
        this.duracionQuemadura = duracionQuemadura;
    }
}
