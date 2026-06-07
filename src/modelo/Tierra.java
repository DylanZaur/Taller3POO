package modelo;

public class Tierra extends Hechizo {

    private int mejoraDefensa;

    public Tierra(String nombre, int dano, int mejoraDefensa) {
        super(nombre, "Tierra", dano);
        this.mejoraDefensa = mejoraDefensa;
    }

    @Override
    public double calcularPuntaje() {
        return (getDano() * mejoraDefensa) / 2.0;
    }

    public int getMejoraDefensa() {
        return mejoraDefensa;
    }

    public void setMejoraDefensa(int mejoraDefensa) {
        this.mejoraDefensa = mejoraDefensa;
    }
}