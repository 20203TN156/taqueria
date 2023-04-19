package dao;

public class Alimento {
    String id_alimento;
    String nombre;
    String ingredientes;
    String precio;

    public Alimento() {
    }

    public Alimento(String nombre, String ingredientes, String precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }
    public Alimento(String id_alimento, String nombre, String ingredientes, String precio) {
        this.id_alimento = id_alimento;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

    public String getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(String id_alimento) {
        this.id_alimento = id_alimento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
