package modelo;

public class Planta extends Hechizo {

    private int duracionStun;
    private int cantPlantas;

    public Planta(String nombre, int dano, int duracionStun, int cantPlantas) {
        super(nombre, "Planta", dano);
        this.duracionStun = duracionStun;
        this.cantPlantas = cantPlantas;
    }

    @Override
    public double calcularPuntaje() {
        return getDano() + (duracionStun * cantPlantas);
    }

    public int getDuracionStun() {
        return duracionStun;
    }

    public void setDuracionStun(int duracionStun) {
        this.duracionStun = duracionStun;
    }

    public int getCantPlantas() {
        return cantPlantas;
    }

    public void setCantPlantas(int cantPlantas) {
        this.cantPlantas = cantPlantas;
    }
}
