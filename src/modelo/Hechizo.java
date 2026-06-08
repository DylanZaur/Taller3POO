package modelo;

public abstract class Hechizo implements Puntuable {

    private String nombre;
    private String tipo;
    private int dano;

    public Hechizo(String nombre, String tipo, int dano) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dano = dano;
    }

    // Cada subclase implementa su propia formula de puntaje
    @Override
    public abstract double calcularPuntaje();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - Daño: " + dano + " - Puntaje: " + calcularPuntaje();
    }
}