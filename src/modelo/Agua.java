package modelo;

public class Agua extends Hechizo {

    private int cantidadHeal;
    private int presionAgua;

    public Agua(String nombre, int dano, int cantidadHeal, int presionAgua) {
        super(nombre, "Agua", dano);
        this.cantidadHeal = cantidadHeal;
        this.presionAgua = presionAgua;
    }

    @Override
    public double calcularPuntaje() {
        return (getDano() + cantidadHeal + presionAgua) * 2;
    }

    public int getCantidadHeal() {
        return cantidadHeal;
    }

    public void setCantidadHeal(int cantidadHeal) {
        this.cantidadHeal = cantidadHeal;
    }

    public int getPresionAgua() {
        return presionAgua;
    }

    public void setPresionAgua(int presionAgua) {
        this.presionAgua = presionAgua;
    }
}