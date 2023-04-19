package dao;

public class Bebida {
    String id_bebida, nombre, precio;

    public Bebida() {
    }

    public Bebida(String id_bebida, String nombre, String precio) {
        this.id_bebida = id_bebida;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(String id_bebida) {
        this.id_bebida = id_bebida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
